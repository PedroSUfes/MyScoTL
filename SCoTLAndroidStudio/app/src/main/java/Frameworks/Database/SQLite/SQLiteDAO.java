package Frameworks.Database.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Policy.Adapters.MyLog;
import Policy.BusinessRules.Adapters.*;
import Policy.BusinessRules.LoginManager;
import Policy.BusinessRules.UserType;
import Policy.Entity.Batch;
import Policy.Entity.CoffeeBag;
import Policy.Entity.Employee;
import Policy.Entity.Person;
import Policy.Entity.Property;
import Policy.Entity.Servant;
import Policy.Entity.User;
import Policy.Entity.Warehouse;
import Policy.Entity.WarehouseManager;
import Utility.Func;
import Utility.Func1;
import Utility.Func2;
import Utility.Func3;
import Utility.Func4;
import Utility.Func5;
import kotlin.jvm.functions.Function5;


public class SQLiteDAO
    extends
        SQLiteOpenHelper
    implements
        LoginInterface,
        BatchOperationsInterface,
        CoffeeBagOperationsInterface,
        EmployeeOperationsInterface,
        PropertyOperationsInterface,
        WarehouseOperationsInterface
{
    private class EmployeeContainer
    {
        public String m_workerCpf = null;
        public String m_workLocalId = null;
        public String m_hireDate = null;
        public String m_endDate = null;

        public EmployeeContainer
        (
            String workerCpf,
            String workLocalId,
            String hireDate,
            String endDate
        )
        {
            m_workerCpf = workerCpf;
            m_workLocalId = workLocalId;
            m_hireDate = hireDate;
            m_endDate = endDate;
        }
    }

    private class IsWarehouseOwnerContainer
    {
        public String m_warehouseId = null;
        public String m_ownerCpf = null;
        public String m_beginDate = null;
        public String m_endDate = null;

        public IsWarehouseOwnerContainer
        (
            String warehouseId,
            String ownerCpf,
            String beginDate,
            String endDate
        )
        {
            m_warehouseId = warehouseId;
            m_ownerCpf = ownerCpf;
            m_beginDate = beginDate;
            m_endDate = endDate;
        }
    }

    private static final String DB_NAME = "SCoTLLocalDb";

    public SQLiteDAO(Context context)
    {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(PersonTableQueryHelper.GetCreateCommand());
        sqLiteDatabase.execSQL(UserTableQueryHelper.GetCreateCommand());
        sqLiteDatabase.execSQL(WarehouseTableQueryHelper.GetCreateCommand());
        sqLiteDatabase.execSQL(PropertyTableQueryHelper.GetCreateCommand());
        sqLiteDatabase.execSQL(BatchTableQueryHelper.GetCreateCommand());
        sqLiteDatabase.execSQL(CoffeeBagTableQueryHelper.GetCreateCommand());
        sqLiteDatabase.execSQL(BuyCoffeeBagTableQueryHelper.GetCreateCommand());
        sqLiteDatabase.execSQL(IsWarehouseOwnerTableQueryHelper.GetCreateCommand());
        sqLiteDatabase.execSQL(ManageWarehouseTableQueryHelper.GetCreateCommand());
        sqLiteDatabase.execSQL(WorksOnTableQueryHelper.GetCreateCommand());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }

    @Override
    public void onConfigure(SQLiteDatabase db)
    {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    public Boolean TryRegisterUser(String login, String password)
    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues newUserValues = UserTableQueryHelper.GetContentValues(login, password);

        long result = -1;
        try
        {
            result = database.insert(UserTableQueryHelper.USER_TABLE, null, newUserValues);
            if(result == -1)
            {
                MyLog.LogMessage("Fail to add user with login "+login+" and password "+password+" in database");
                return false;
            }

            MyLog.LogMessage("User registration with login "+login+" and password "+password+" made with success");
            return true;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        } finally
        {
            database.close();
        }

        MyLog.LogMessage("Problem in user registration");
        return false;
    }

    @Override
    public Boolean TryLogin(String login, String password)
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(UserTableQueryHelper.GetSelectQuery(login, password), null);
        if(!cursor.moveToFirst())
        {
            MyLog.LogMessage("Fail to login. Check user name and password");
            database.close();
            return false;
        }

        LoginManager.SetUserType(UserType.values()[cursor.getInt(UserTableQueryHelper.GetUserTypeIndex())]);
        database.close();
        return true;
    }

    @Override
    public Batch[] GetBatches()
    {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(BatchTableQueryHelper.GetSelectAllQuery(), null);

        //BatchID and BatchCreationDate
        ArrayList<Batch> batchList = new ArrayList<Batch>();

		if(cursor.moveToFirst()){
			do{
				batchList.add(
					new Batch(
						cursor.getString(BatchTableQueryHelper.GetBatchIdIndex()),
						cursor.getString(BatchTableQueryHelper.GetCreationDateIndex())
					)
				);
			}while(cursor.moveToNext());
		}

		if(batchList.isEmpty()){
			MyLog.LogMessage("No batches in database");
			db.close();
			return null;
		}

	  Batch[] batchesReturn = new Batch[batchList.size()];
		  int index = -1;
		  for(Batch b : batchList){
			  ++index;
			  if(b == null){
          continue;
			  }
        batchesReturn[index] = batchList.get(index);
		  }
      db.close();

      return batchesReturn;
    }

    @Override
    public Batch GetBatch(String batchId)
    {
        Cursor c = null;
        String newBatchId = null;
        String newBatchCreationDate = null;

        try {
            SQLiteDatabase db = getReadableDatabase();
             c = db.rawQuery(BatchTableQueryHelper.GetSelectQuery(batchId), null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                newBatchId = c.getString(BatchTableQueryHelper.GetBatchIdIndex());
                newBatchCreationDate = c.getString(BatchTableQueryHelper.GetCreationDateIndex());
            }

            if (newBatchId != null) {
                return new Batch(newBatchId, newBatchCreationDate);
            } else {
                MyLog.LogMessage("Id não encontrado");
                return null;
            }
        } finally {
            c.close();
        }
    }

    @Override
    public Boolean TryRegisterBatch(Batch batch)
    {
        SQLiteDatabase database = getWritableDatabase();
        long result = -1;
        try
        {
            result = database.insert(BatchTableQueryHelper.BATCH_TABLE, null, BatchTableQueryHelper.GetContentValues(batch));
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        } finally
        {
            database.close();
        }
        if(result < 0)
        {
            MyLog.LogMessage("There's a batch with id "+batch.GetId()+" already in database");
            MyLog.LogMessage("Fail to register batch");
            return false;
        }
        return true;
    }

    @Override
    public Boolean TryRemoveBatch(String batchId) {
        return null;
    }

    @Override
    public CoffeeBag[] GetCoffeeBags() {
        return new CoffeeBag[0];
    }

    @Override
    public CoffeeBag[] GetCoffeeBags(String batchId) {
        return new CoffeeBag[0];
    }

    @Override
    public CoffeeBag GetCoffeeBag(String batchId, String coffeeBagId) {
        return null;
    }

    @Override
    public Boolean TryRegisterCoffeeBag(CoffeeBag coffeeBag)
    {
        String coffeeBagId = coffeeBag.GetId();
        String batchId = coffeeBag.GetBatch().GetId();
        String warehouseId = coffeeBag.GetBatch().GetId();

        SQLiteDatabase database = getReadableDatabase();

//        Func4<SQLiteDatabase, String, Func2<SQLiteDatabase, String, Boolean>, String, Boolean> checkIfExistsFunc =
//                (db, id, existsFunc, entityName) ->
//                {
//                    if(!existsFunc.Invoke(db, id))
//                    {
//                        MyLog.LogMessage("There's no "+entityName+" with id "+id+" in database");
//                        MyLog.LogMessage("Fail to add coffee bag with id "+coffeeBagId);
//                        return false;
//                    }
//
//                    return true;
//                };
//
//        if(!checkIfExistsFunc.Invoke(database, batchId, BatchTableQueryHelper::Exists, "batch"))
//        {
//            database.close();
//            return false;
//        }
//        if(!checkIfExistsFunc.Invoke(database, warehouseId, WarehouseTableQueryHelper::Exists, "warehouse"))
//        {
//            database.close();
//            return false;
//        }

        database.close();
        database = getWritableDatabase();
        try
        {
            ContentValues newValues = CoffeeBagTableQueryHelper.GetContentValues(coffeeBag);
            long result = database.insert(CoffeeBagTableQueryHelper.COFFEE_BAG_TABLE, null, newValues);
            if(result == -1)
            {
                MyLog.LogMessage("Fail to add coffee bag with id "+coffeeBagId);
                return false;
            }

            MyLog.LogMessage("Coffee bag with id "+coffeeBagId+" added with success");
            return true;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        } finally
        {
            database.close();
        }

        MyLog.LogMessage("Problem to add coffee bag with id "+coffeeBagId);
        return false;
    }

    @Override
    public Boolean TryRemoveCoffeeBag(String batchId, String coffeeBagId) {
        return null;
    }

    @Override
    public Employee[] GetEmployees() {
        return new Employee[0];
    }

    @Override
    public Employee GetEmployee(String cpf) {
        return null;
    }

    @Override
    public Servant[] GetServants(boolean withPastRegister)
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor worksOnCursor = database.rawQuery
        (
            withPastRegister ? WorksOnTableQueryHelper.GetSelectAllQuery() :
            WorksOnTableQueryHelper.GetSelectAllThatEndDateIsNullQuery(), null
        );

        if(!worksOnCursor.moveToFirst())
        {
            MyLog.LogMessage("No servants in database");
            database.close();
            return null;
        }

        Servant[] result = GetServantsFromWorksOnCursor(worksOnCursor, database);
        database.close();
        return result;
    }

    @Override
    public Servant[] GetServant(String cpf, boolean withPastRegister)
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor worksOnCursor = database.rawQuery
        (
            withPastRegister ?  WorksOnTableQueryHelper.GetSelectQuery(cpf) :
            WorksOnTableQueryHelper.GetSelectQueryNoPastRegisters(cpf),
            null
        );
        if(!worksOnCursor.moveToFirst())
        {
            MyLog.LogMessage("No servant with cpf "+cpf);
            MyLog.LogMessage("Fail to read servant");
            database.close();
            return null;
        }

        Servant[] result = GetServantsFromWorksOnCursor(worksOnCursor, database);
        database.close();
        return result;
    }

    @Override
    public WarehouseManager[] GetWarehouseManagers()
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor manageWarehouseCursor = database.rawQuery(ManageWarehouseTableQueryHelper.GetSelectAllQuery(), null);
        if(!manageWarehouseCursor.moveToFirst())
        {
            MyLog.LogMessage("No warehouse managers in database");
            database.close();
            return null;
        }

        WarehouseManager[] result = GetWarehouseManagersFromManageWarehouseCursor(manageWarehouseCursor, database);
        database.close();
        return result;
    }

    @Override
    public WarehouseManager[] GetWarehouseManager(String cpf, boolean withPastRegister)
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor manageWarehouseCursor = database.rawQuery
                (
                        withPastRegister ? ManageWarehouseTableQueryHelper.GetSelectByPersonCpfQuery(cpf) :
                                ManageWarehouseTableQueryHelper.GetSelectByPersonCpfNoPastRegister(cpf),
                        null
                );

        if(!manageWarehouseCursor.moveToFirst())
        {
            MyLog.LogMessage("Fail to get warehouse manager with cpf "+cpf);
            database.close();
            return null;
        }

        WarehouseManager[] toReturn = GetWarehouseManagersFromManageWarehouseCursor(manageWarehouseCursor, database);
        database.close();
        return toReturn;
    }

    @Override
    public Boolean TryRegisterServant(Servant servant)
    {
        SQLiteDatabase database = getReadableDatabase();
        Property property = servant.GetProperty();
        String servantCpf = servant.GetCpf();

        // Caso já esteja cadastrado
        if(WorksOnTableQueryHelper.PersonExists(database, servantCpf))
        {
            MyLog.LogMessage("There's a servant with cpf "+servantCpf+" already in database");
            MyLog.LogMessage("Fail to register servant");
            database.close();
            return false;
        }

        // Caso a propriedade não exista
        if (!PropertyTableQueryHelper.Exists(database, property.GetId()))
        {
            MyLog.LogMessage("There's no property with id "+ property.GetId()+" in database");
            MyLog.LogMessage("Fail to register servant with cpf "+servantCpf);
            database.close();
            return false;
        }

        boolean personExists = PersonTableQueryHelper.Exists(database, servantCpf);
        if
        (
            personExists &&
            (IsWarehouseOwnerTableQueryHelper.PersonExists(database, servantCpf) || WorksOnTableQueryHelper.PersonExists(database, servantCpf)))
        {
            MyLog.LogMessage("There's a owner or a manager with cpf "+ servantCpf+" already in database");
            MyLog.LogMessage("Fail to register servant");
            database.close();
            return false;
        }

        database.close();
        database = getWritableDatabase();

        if(!personExists)
        {
            try
            {
                database.insert(PersonTableQueryHelper.PERSON_TABLE, null, PersonTableQueryHelper.GetContentValue(servant));
            }catch (Exception e)
            {
                MyLog.LogMessage(e.getMessage());
            }
        }
        try
        {
            database.insert
                    (
                            WorksOnTableQueryHelper.WORKS_ON_TABLE,
                            null,
                            WorksOnTableQueryHelper.GetContentValue(servantCpf, property.GetId(), servant.GetHiringDate(), servant.GetEndDate())
                    );
        }catch (Exception e)
        {
            MyLog.LogMessage(e.getMessage());
        }

        database.close();
        return true;
    }

    @Override
    public Boolean TryRegisterWarehouseManager(WarehouseManager warehouseManager)
    {
        SQLiteDatabase database = getReadableDatabase();
        String warehouseManagerCpf = warehouseManager.GetCpf();

        // Caso em que já está registrado
        if(ManageWarehouseTableQueryHelper.PersonExists(database, warehouseManagerCpf))
        {
            MyLog.LogMessage("The warehouse manager with cpf "+warehouseManagerCpf+" is already in database");
            MyLog.LogMessage("Fail to register warehouse manager");
            database.close();
            return false;
        }

        // Caso em que o galpão não existe
        String warehouseId = warehouseManager.GetWarehouse().GetId();
        if(!WarehouseTableQueryHelper.Exists(database, warehouseId))
        {
            MyLog.LogMessage("There's no warehouse with id "+warehouseId+" in database");
            MyLog.LogMessage("Fail to register warehouse manager");
            database.close();
            return false;
        }

        // Caso em que o galpão já é administrado
        if(ManageWarehouseTableQueryHelper.HaveWarehouseManager(database, warehouseId))
        {
            MyLog.LogMessage("The warehouse with id "+warehouseId+" already have a manager");
            MyLog.LogMessage("Fail to register warehouse manager");
            database.close();
            return false;
        }

        // Caso o gerente de galpão a ser cadastrado já é dono ou servente
        boolean personExists = PersonTableQueryHelper.Exists(database, warehouseManagerCpf);
        if
        (
            personExists &&
            (IsWarehouseOwnerTableQueryHelper.PersonExists(database, warehouseManagerCpf) ||
            WorksOnTableQueryHelper.PersonExists(database, warehouseManagerCpf))
        )
        {
            MyLog.LogMessage("The person with cpf "+warehouseManagerCpf+" is already in warehouse owner or servant");
            MyLog.LogMessage("Fail to register warehouse manager");
            database.close();
            return false;
        }

        database.close();
        database = getWritableDatabase();
        if(!personExists)
        {
            try
            {
                database.insert(PersonTableQueryHelper.PERSON_TABLE, null, PersonTableQueryHelper.GetContentValue(warehouseManager));
            }catch (Exception e)
            {
                MyLog.LogMessage(e.getMessage());
            }
        }

        try
        {
            database.insert
                    (
                            ManageWarehouseTableQueryHelper.MANAGE_WAREHOUSE_TABLE,
                            null,
                            ManageWarehouseTableQueryHelper.GetContentValue
                                    (
                                            warehouseId,
                                            warehouseManagerCpf,
                                            warehouseManager.GetHiringDate()
                                    )
                    );

        }catch (Exception e)
        {
            MyLog.LogMessage(e.getMessage());
        }

        database.close();
        return true;
    }

    @Override
    public Boolean TryUpdateServant(Servant servant, String date)
    {
        SQLiteDatabase database = getReadableDatabase();
        String servantCpf = servant.GetCpf();
        if(!WorksOnTableQueryHelper.PersonExists(database, servantCpf))
        {
            MyLog.LogMessage("There's servant with cpf "+servantCpf+" in database");
            MyLog.LogMessage("Fail to update servant");
            database.close();
            return false;
        }

        String newPropertyId = servant.GetProperty().GetId();
        Cursor worksOnCursor = database.rawQuery(WorksOnTableQueryHelper.GetSelectQueryNoPastRegisters(servantCpf), null);
        if(!worksOnCursor.moveToFirst())
        {
            database.close();
            return false;
        }

        database.close();
        database = getWritableDatabase();
        String currentPropertyId = worksOnCursor.getString(WorksOnTableQueryHelper.GetPropertyIdIndex());
        if(!currentPropertyId.equals(newPropertyId))
        {
            // Proprieadade está cadastrada?
            if(!PropertyTableQueryHelper.Exists(database, newPropertyId))
            {
                MyLog.LogMessage("Property with id "+newPropertyId+" is not in database");
                MyLog.LogMessage("Fail to update servant");
                database.close();
                return false;
            }

            // Sertar a data de fim na tupla em worksOnCursor
            ContentValues contentValues = new ContentValues();
            contentValues.put(WorksOnTableQueryHelper.END_DATE, date);
            DBStatamentHelper updateHelper = WorksOnTableQueryHelper.GetStatementHelper
                    (
                            worksOnCursor.getString(WorksOnTableQueryHelper.GetPersonCpfIndex()),
                            worksOnCursor.getString(WorksOnTableQueryHelper.GetPropertyIdIndex()),
                            worksOnCursor.getString(WorksOnTableQueryHelper.GetBeginDateIndex())
                    );

            database.update(WorksOnTableQueryHelper.WORKS_ON_TABLE, contentValues, updateHelper.m_whereClause, updateHelper.m_args);

            ContentValues newWorksOnValues = WorksOnTableQueryHelper.GetContentValue(servantCpf, newPropertyId, date, null);
            try
            {
                database.insert(WorksOnTableQueryHelper.WORKS_ON_TABLE, null, newWorksOnValues);
            }catch (Exception e)
            {
                MyLog.LogMessage(e.getMessage());
            }
        }

        boolean result = UpdatePerson(database, servant);
        database.close();
        return result;
    }

    @Override
    public Boolean TryUpdateWarehouseManager(WarehouseManager warehouseManager, String date)
    {
        SQLiteDatabase database = getReadableDatabase();
        String warehouseManagerCpf = warehouseManager.GetCpf();
        if(!ManageWarehouseTableQueryHelper.PersonExists(database, warehouseManagerCpf))
        {
            MyLog.LogMessage("There's no warehouse manager with cpf "+warehouseManagerCpf+" in database");
            MyLog.LogMessage("Fail to remove warehouse manager");
            database.close();
            return false;
        }

        Cursor manageWarehouseCursor = database.rawQuery(ManageWarehouseTableQueryHelper.GetSelectByPersonCpfNoPastRegister(warehouseManagerCpf), null);
        if(!manageWarehouseCursor.moveToFirst())
        {
            database.close();
            return false;
        }

        String currentWarehouseId = manageWarehouseCursor.getString(ManageWarehouseTableQueryHelper.GetWarehouseIdIndex());
        String newWarehouseId = warehouseManager.GetWarehouse().GetId();
        database.close();
        database = getWritableDatabase();
        if(!currentWarehouseId.equals(newWarehouseId))
        {
            // O novo galpão está cadastrado?
            if(!WarehouseTableQueryHelper.Exists(database, newWarehouseId))
            {
                MyLog.LogMessage("The warehouse with id "+newWarehouseId+" is not in database");
                MyLog.LogMessage("Fail to update warehouse manager");
                database.close();
                return false;
            }

            ContentValues contentValues = new ContentValues();
            contentValues.put(ManageWarehouseTableQueryHelper.END_DATE, date);
            DBStatamentHelper updateHelper = ManageWarehouseTableQueryHelper.GetStatementHelper
                    (
                            manageWarehouseCursor.getString(ManageWarehouseTableQueryHelper.GetManagerCpfIndex()),
                            manageWarehouseCursor.getString(ManageWarehouseTableQueryHelper.GetWarehouseIdIndex()),
                            manageWarehouseCursor.getString(ManageWarehouseTableQueryHelper.GetBeginDateIndex())
                    );

            database.update(ManageWarehouseTableQueryHelper.MANAGE_WAREHOUSE_TABLE, contentValues, updateHelper.m_whereClause, updateHelper.m_args);

            ContentValues newManageWarehouseValue = ManageWarehouseTableQueryHelper.GetContentValue(newWarehouseId, warehouseManagerCpf, date);
            try
            {
                database.insert(ManageWarehouseTableQueryHelper.MANAGE_WAREHOUSE_TABLE, null, newManageWarehouseValue);
            } catch (Exception e)
            {
                MyLog.LogMessage(e.getMessage());
            }
        }

        boolean result = UpdatePerson(database, warehouseManager);
        database.close();
        return result;
    }

    @Override
    public Boolean TryRemoveEmployee(String cpf)
    {
        SQLiteDatabase database = getReadableDatabase();
        boolean isServant = WorksOnTableQueryHelper.PersonExists(database, cpf);
        if
        (
                !PersonTableQueryHelper.Exists(database, cpf) &&
                !isServant &&
                !ManageWarehouseTableQueryHelper.PersonExists(database, cpf)
        )
        {
            MyLog.LogMessage("There's no employee with cpf "+cpf+" in database");
            MyLog.LogMessage("Fail to remove servant");
            database.close();
            return false;
        }

        if(isServant)
        {
            DBStatamentHelper deleteHelper = WorksOnTableQueryHelper.GetStatementHelper(cpf);
            database.delete(WorksOnTableQueryHelper.WORKS_ON_TABLE, deleteHelper.m_whereClause, deleteHelper.m_args);
        }
        else
        {
            DBStatamentHelper deleteHelper = ManageWarehouseTableQueryHelper.GetStatementHelper(cpf);
            database.delete(ManageWarehouseTableQueryHelper.MANAGE_WAREHOUSE_TABLE, deleteHelper.m_whereClause, deleteHelper.m_args);
        }

        if(!BuyCoffeeBagTableQueryHelper.Exists(database, cpf))
        {

            DBStatamentHelper deleteHelper = PersonTableQueryHelper.GetStatementHelper(cpf);
            database.delete(PersonTableQueryHelper.PERSON_TABLE, deleteHelper.m_whereClause, deleteHelper.m_args);
        }
        database.close();
        return true;
    }

    @Override
    public Property[] GetProperties()
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor propertyCursor = database.rawQuery(PropertyTableQueryHelper.GetSelectAllQuery(), null);
        if(!propertyCursor.moveToFirst())
        {
            MyLog.LogMessage("No properties in database");
            database.close();
            return null;
        }

        ArrayList<Property> propertyList = new ArrayList<Property>();
        do
        {
            propertyList.add(PropertyTableQueryHelper.GetPropertyFromCursor(propertyCursor));
        } while(propertyCursor.moveToNext());

        if(propertyList.isEmpty())
        {
            MyLog.LogMessage("Fail to get properties");
            database.close();
            return null;
        }

        Property[] toReturn = new Property[propertyList.size()];
        int index = -1;
        for (Property p : propertyList)
        {
            ++index;
            if(p == null)
            {
                continue;
            }

            toReturn[index] = propertyList.get(index);
        }

        return toReturn;
    }

    @Override
    public Property GetPropertyById(String id)
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(PropertyTableQueryHelper.GetSelectQuery(id), null);
        if(!cursor.moveToFirst())
        {
            MyLog.LogMessage("There's no property with id "+id+" in database");
            database.close();
            return null;
        }

        Property toReturn = PropertyTableQueryHelper.GetPropertyFromCursor(cursor);
        database.close();
        return toReturn;
    }

    @Override
    public Boolean TryRegisterProperty(Property property)
    {
        SQLiteDatabase database = getWritableDatabase();
        long result = 0;
        try
        {
            result = database.insert(PropertyTableQueryHelper.PROPERTY_TABLE, null, PropertyTableQueryHelper.GetContentValues(property));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        database.close();
        if(result < 0)
        {
            MyLog.LogMessage("Fail to add property with id "+property.GetId());
            database.close();
            return false;
        }
        return true;
    }

    @Override
    public Boolean TryUpdateProperty(Property property)
    {
        SQLiteDatabase database = getReadableDatabase();
        String propertyId = property.GetId();
        if(!PropertyTableQueryHelper.Exists(database, propertyId))
        {
            MyLog.LogMessage("There's no property with id "+propertyId+" in database");
            MyLog.LogMessage("Fail to update property");
            database.close();
            return false;
        }
        database.close();
        database = getWritableDatabase();

        ContentValues newValues = PropertyTableQueryHelper.GetContentValues(property);
        DBStatamentHelper updateHelper = PropertyTableQueryHelper.GetStatementHelper(propertyId);

        int rows = database.update(PropertyTableQueryHelper.PROPERTY_TABLE, newValues, updateHelper.m_whereClause, updateHelper.m_args);
        if(rows == 0)
        {
            MyLog.LogMessage("Fail to update property with id "+propertyId);
            database.close();
            return false;
        }
        return true;
    }

    @Override
    public Boolean TryRemoveProperty(String id)
    {
        SQLiteDatabase database = getWritableDatabase();

        DBStatamentHelper deleteHelper = PropertyTableQueryHelper.GetStatementHelper(id);
        int rows = database.delete(PropertyTableQueryHelper.PROPERTY_TABLE, deleteHelper.m_whereClause, deleteHelper.m_args);
        database.close();
        if(rows == 0)
        {
            MyLog.LogMessage("Fail to delete property with id "+id);
            return false;
        }

        MyLog.LogMessage("Property with id "+id+" was deleted with success");
        return true;
    }

    @Override
    public Warehouse[] GetWarehouses(boolean withPastRegister)
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor isWarehouseOwnerCursor = database.rawQuery
                (
                        withPastRegister ? IsWarehouseOwnerTableQueryHelper.GetSelectAllQuery() :
                        IsWarehouseOwnerTableQueryHelper.GetSelectAllNoPastRegister(),
                        null
                );
        if(!isWarehouseOwnerCursor.moveToFirst())
        {
            MyLog.LogMessage("No warehouses in database");
            database.close();
            return null;
        }

        Warehouse[] result = GetWarehousesFromIsWarehouseOwnerCursor(isWarehouseOwnerCursor, database);
        database.close();
        return result;
    }

    @Override
    public Warehouse[] GetWarehouses(String stateName, boolean withPastRegister)
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery
                (
                        withPastRegister ? IsWarehouseOwnerTableQueryHelper.GetSelectAllFromStateJoinedWithWarehouseTableQuery(stateName) :
                                IsWarehouseOwnerTableQueryHelper.GetSelectAllFromStateJoinedWithWarehouseTableNoPastRegisterQuery(stateName),
                        null
                );
        if(!cursor.moveToFirst())
        {
            MyLog.LogMessage("There's no warehouses in state "+stateName+" that are in the database register");
            database.close();
            return null;
        }

        Warehouse[] toReturn = GetWarehousesFromIsWarehouseOwnerCursor(cursor, database);
        database.close();
        return toReturn;
    }

    @Override
    public Warehouse[] GetWarehouses(String stateName, String cityName, boolean withPastRegister)
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor isWarehouseOwnerCursor = database.rawQuery
                (
                    withPastRegister ? IsWarehouseOwnerTableQueryHelper.GetSelectAllFromStateAndCityJoinedWithWarehouseTableQuery(stateName, cityName) :
                            IsWarehouseOwnerTableQueryHelper.GetSelectAllFromStateAndCityJoinedWithWarehouseTableNoPastRegisterQuery(stateName, cityName),
                        null
                );

        if(!isWarehouseOwnerCursor.moveToFirst())
        {
            MyLog.LogMessage("Fail to get warehouses from state "+stateName+" and city "+cityName);
            database.close();
            return null;
        }

        Warehouse[] toReturn = GetWarehousesFromIsWarehouseOwnerCursor(isWarehouseOwnerCursor, database);
        database.close();
        return toReturn;
    }

    @Override
    public Warehouse[] GetWarehouses(String stateName, String cityName, String streetName, boolean withPastRegister)
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor isWarehouseOwnerCursor = database.rawQuery
                (
                        withPastRegister ? IsWarehouseOwnerTableQueryHelper.GetSelectAllFromStateCityAndStreetJoinedWithWarehouseTableQuery(stateName, cityName, streetName) :
                                IsWarehouseOwnerTableQueryHelper.GetSelectAllFromStateCityAndStreetJoinedWithWarehouseTableNoPastRegisterQuery(stateName, cityName, streetName),
                        null
                );

        if(!isWarehouseOwnerCursor.moveToFirst())
        {
            MyLog.LogMessage("Fail to get warehouses from state "+stateName+", city "+cityName+", and street "+streetName);
            database.close();
            return null;
        }

        Warehouse[] toReturn = GetWarehousesFromIsWarehouseOwnerCursor(isWarehouseOwnerCursor, database);
        database.close();
        return toReturn;
    }

    @Override
    public Warehouse[] GetWarehouses(String stateName, String cityName, String streetName, int residentialNumber, boolean withPastRegister)
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor isWarehouseOwnerCursor = database.rawQuery
                (
                        withPastRegister ? IsWarehouseOwnerTableQueryHelper.GetSelectWarehouseFromAddressQuery(stateName, cityName, streetName, residentialNumber) :
                                IsWarehouseOwnerTableQueryHelper.GetSelectWarehouseFromAddressNoPastRegisterQuery(stateName, cityName, streetName, residentialNumber),
                        null
                );

        if(!isWarehouseOwnerCursor.moveToFirst())
        {
            MyLog.LogMessage("Fail to get warehouses from state "+stateName+", city "+cityName+", street "+streetName+" and number "+residentialNumber);
            database.close();
            return null;
        }

        Warehouse[] toReturn = GetWarehousesFromIsWarehouseOwnerCursor(isWarehouseOwnerCursor, database);
        database.close();
        return toReturn;
    }

    @Override
    public Warehouse[] GetWarehousesByOwnerCpf(String ownerCpf, boolean withPastRegister)
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor isWarehouseOwnerCursor = database.rawQuery
                (
                        withPastRegister ? IsWarehouseOwnerTableQueryHelper.GetSelectWithOwnerCpfQuery(ownerCpf) :
                            IsWarehouseOwnerTableQueryHelper.GetSelectWithOwnerCpfNoPastRegisterQuery(ownerCpf),
                        null
                );

        if(!isWarehouseOwnerCursor.moveToFirst())
        {
            MyLog.LogMessage("Fail to get warehouses with owner with cpf "+ownerCpf);
            database.close();
            return null;
        }

        Warehouse[] toReturn = GetWarehousesFromIsWarehouseOwnerCursor(isWarehouseOwnerCursor, database);
        database.close();
        return toReturn;
    }

    @Override
    public Warehouse[] GetWarehouse(String id, boolean withPastRegister)
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor isWarehouseOwnerCursor = database.rawQuery
                (
                        withPastRegister ? IsWarehouseOwnerTableQueryHelper.GetSelectByWarehouseIdQuery(id) :
                                IsWarehouseOwnerTableQueryHelper.GetSelectByWarehouseIdEndDateNullQuery(id),
                        null
                );

        if(!isWarehouseOwnerCursor.moveToFirst())
        {
            MyLog.LogMessage("No warehouse with id "+id+" in database");
            MyLog.LogMessage("Fail to read warehouse");
            return null;
        }

        Warehouse[] result = GetWarehousesFromIsWarehouseOwnerCursor(isWarehouseOwnerCursor, database);
        database.close();
        return result;
    }

    @Override
    public Boolean TryRegisterWarehouse(Warehouse warehouse)
    {
        SQLiteDatabase database = getReadableDatabase();
        String warehouseId = warehouse.GetId();
        if(WarehouseTableQueryHelper.Exists(database, warehouseId))
        {
            MyLog.LogMessage("The warehouse with id "+warehouseId+" already exists in database");
            MyLog.LogMessage("Fail to register warehouse");
            database.close();
            return false;
        }

        String ownerCpf = warehouse.GetOwner().GetCpf();
        if
        (
            PersonTableQueryHelper.Exists(database, ownerCpf) &&
            (WorksOnTableQueryHelper.PersonExists(database, ownerCpf) ||
            ManageWarehouseTableQueryHelper.PersonExists(database, ownerCpf))
        )
        {
            MyLog.LogMessage("There's a employee with cpf "+ownerCpf+" already in the database");
            MyLog.LogMessage("Fail to register warehouse with id "+warehouseId);
            database.close();
            return false;
        }

        database.close();
        database = getWritableDatabase();
        try
        {
            database.insert(PersonTableQueryHelper.PERSON_TABLE, null, PersonTableQueryHelper.GetContentValue(warehouse.GetOwner()));
            database.insert(WarehouseTableQueryHelper.WAREHOUSE_TABLE, null, WarehouseTableQueryHelper.GetContentValue(warehouse));
            database.insert
                    (
                            IsWarehouseOwnerTableQueryHelper.IS_WAREHOUSE_OWNER_TABLE,
                            null,
                            IsWarehouseOwnerTableQueryHelper.GetContentValues(warehouseId, ownerCpf, warehouse.GetBeginDate(), warehouse.GetEndDate())
                    );
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        database.close();
        return true;
    }

    @Override
    public Boolean TryUpdateWarehouse(Warehouse warehouse)
    {
        SQLiteDatabase database = getReadableDatabase();
        String warehouseId = warehouse.GetId();
        if(!WarehouseTableQueryHelper.Exists(database, warehouseId))
        {
            MyLog.LogMessage("There's no warehouse with id "+warehouseId+" in database");
            MyLog.LogMessage("Fail to update warehouse");
            database.close();
            return false;
        }

        Cursor isWarehouseOwnerCursor = database.rawQuery(IsWarehouseOwnerTableQueryHelper.GetSelectByWarehouseIdEndDateNullQuery(warehouseId), null);
        if(!isWarehouseOwnerCursor.moveToFirst())
        {
            MyLog.LogMessage("Fail to update warehouse with id "+warehouseId);
            database.close();
            return false;
        }

        String currentWarehouseOwnerCpf = isWarehouseOwnerCursor.getString(IsWarehouseOwnerTableQueryHelper.GetOwnerCpfIndex());
        String newWarehouseOwnerCpf = warehouse.GetOwner().GetCpf();
        database.close();
        database = getWritableDatabase();
        if(!currentWarehouseOwnerCpf.equals(newWarehouseOwnerCpf))
        {
            ContentValues newPersonValue = PersonTableQueryHelper.GetContentValue(warehouse.GetOwner());
            try
            {
                database.insert(PersonTableQueryHelper.PERSON_TABLE, null, newPersonValue);
            }catch (Exception e)
            {
                MyLog.LogMessage(e.getMessage());
            }

            // Setar a data de fim na tabela de relacionamento de donos
            // Incerir o novo relacionamento

            DBStatamentHelper updateHelper = IsWarehouseOwnerTableQueryHelper.GetStatementHelperEndDateNull(warehouseId);
            ContentValues newIsWarehouseOwnerValues = new ContentValues();
            newIsWarehouseOwnerValues.put(IsWarehouseOwnerTableQueryHelper.END_DATE, warehouse.GetBeginDate());
            database.update
                    (
                            IsWarehouseOwnerTableQueryHelper.IS_WAREHOUSE_OWNER_TABLE,
                            newIsWarehouseOwnerValues,
                            updateHelper.m_whereClause,
                            updateHelper.m_args
                    );

            ContentValues toInsertValues = IsWarehouseOwnerTableQueryHelper.GetContentValues
                    (
                            warehouseId,
                            newWarehouseOwnerCpf,
                            warehouse.GetBeginDate(),
                            null
                    );
            try
            {
                database.insert(IsWarehouseOwnerTableQueryHelper.IS_WAREHOUSE_OWNER_TABLE, null, toInsertValues);
            }catch (Exception e)
            {
                MyLog.LogMessage(e.getMessage());
            }

        }

        ContentValues newWarehouseValues = new ContentValues();
        newWarehouseValues.put(WarehouseTableQueryHelper.STATE_NAME, warehouse.GetStateName());
        newWarehouseValues.put(WarehouseTableQueryHelper.CITY_NAME, warehouse.GetCityName());
        newWarehouseValues.put(WarehouseTableQueryHelper.STREET_NAME, warehouse.GetStreetName());
        newWarehouseValues.put(WarehouseTableQueryHelper.RESIDENTIAL_NUMBER, warehouse.GetNumber());
        DBStatamentHelper warehouseUpdateHelper = WarehouseTableQueryHelper.GetStatementHelper(warehouseId);
        int rows = database.update
                (
                        WarehouseTableQueryHelper.WAREHOUSE_TABLE,
                        newWarehouseValues,
                        warehouseUpdateHelper.m_whereClause,
                        warehouseUpdateHelper.m_args
                );

        database.close();
        if(rows == 0)
        {
            MyLog.LogMessage("Fail to update warehouse with id "+warehouseId);
            return false;
        }

        MyLog.LogMessage("The warehouse with id "+warehouseId+" was updated with success");
        return true;
    }

    @Override
    public Boolean TryRemoveWarehouse(String id)
    {
        // Todas as tuplas na tabela de relacionamento entre donos e galpões e entre gerentes e galpões devem ser removidas,
        // mas se precisa avaliar se as pessoas também serão removidas.
        // No caso dos donos de galpão, se o dono do galpão removido for dono de outro ou se for comprador, deve permanecer no banco
        // No caso do gerente do galpão removido, só ficará no banco se for comprador
        // Pode-se usar a TryRemoveEmployee para remover os gerentes

        Func4<Func1<String, String>, Func<Integer>, String, SQLiteDatabase, ArrayList<String>> getCpfAction =
                (databaseSearchQueryMethod, getIndexFunc, warehouseId, db) ->
        {
            Cursor cursor = null;
            try
            {
                cursor = db.rawQuery(databaseSearchQueryMethod.Invoke(warehouseId), null);
                if(!cursor.moveToFirst())
                {
                    return null;
                }
            } catch (Exception e)
            {
                MyLog.LogMessage(e.getMessage());
            }

            if(cursor == null)
            {
                return null;
            }

            ArrayList<String> toReturn = new ArrayList<String>();
            do
            {
                String cpf = cursor.getString(getIndexFunc.Invoke());
                if(!toReturn.contains(cpf))
                {
                    toReturn.add(cpf);
                }
            } while(cursor.moveToNext());

            return toReturn;
        };

        SQLiteDatabase database = getWritableDatabase();
        // Tratamento dos donos
        ArrayList<String> ownerCpfList = getCpfAction.Invoke
                (
                        IsWarehouseOwnerTableQueryHelper::GetSelectByWarehouseIdQuery,
                        IsWarehouseOwnerTableQueryHelper::GetOwnerCpfIndex,
                        id,
                        database
                );

        DBStatamentHelper isWarehouseOwnerDeleteHelper = IsWarehouseOwnerTableQueryHelper.GetStatementHelper(id);
        database.delete
                (
                        IsWarehouseOwnerTableQueryHelper.IS_WAREHOUSE_OWNER_TABLE,
                        isWarehouseOwnerDeleteHelper.m_whereClause,
                        isWarehouseOwnerDeleteHelper.m_args
                );

        if(ownerCpfList != null)
        {
            for (String cpf : ownerCpfList)
            {
                if(cpf == null || IsWarehouseOwnerTableQueryHelper.PersonExists(database, cpf) || BuyCoffeeBagTableQueryHelper.Exists(database, cpf) )
                {
                    continue;
                }

                DBStatamentHelper personDeleteHelper = PersonTableQueryHelper.GetStatementHelper(cpf);
                database.delete(PersonTableQueryHelper.PERSON_TABLE, personDeleteHelper.m_whereClause, personDeleteHelper.m_args);
            }
        }
        //

        // Tratamento dos gerentes
        ArrayList<String> managerCpfList = getCpfAction.Invoke
                (
                        ManageWarehouseTableQueryHelper::GetSelectByWarehouseIdQuery,
                        ManageWarehouseTableQueryHelper::GetManagerCpfIndex,
                        id,
                        database
                );

        if(managerCpfList != null)
        {
            for(String cpf : managerCpfList)
            {
                if(cpf == null)
                {
                    continue;
                }

                TryRemoveEmployee(cpf);
            }
        }
        //

        // Lembrar que o banco está fechado
        database = getWritableDatabase();
        DBStatamentHelper warehouseDeleteHelper = WarehouseTableQueryHelper.GetStatementHelper(id);
        try
        {
            int rows = database.delete
                    (
                            WarehouseTableQueryHelper.WAREHOUSE_TABLE,
                            warehouseDeleteHelper.m_whereClause,
                            warehouseDeleteHelper.m_args
                    );

            if(rows == 0)
            {
                MyLog.LogMessage("Fail to remove warehouse with id "+id+" from database");
                return false;
            }

            MyLog.LogMessage("Warehouse with id "+id+" was removed with success");
            return true;
        } catch (Exception e)
        {
            MyLog.LogMessage(e.getMessage());
        } finally
        {
            database.close();
        }

        MyLog.LogMessage("Problem trying to remove warehouse with id "+id);
        return false;
    }

    private Servant[] GetServantsFromWorksOnCursor(Cursor worksOnCursor, SQLiteDatabase database)
    {
        ArrayList<EmployeeContainer> employeeContainerList = new ArrayList<EmployeeContainer>();
        if(worksOnCursor.moveToFirst())
        {
            do
            {
                employeeContainerList.add
                (
                    new EmployeeContainer
                    (
                        worksOnCursor.getString(WorksOnTableQueryHelper.GetPersonCpfIndex()),
                        worksOnCursor.getString(WorksOnTableQueryHelper.GetPropertyIdIndex()),
                        worksOnCursor.getString(WorksOnTableQueryHelper.GetBeginDateIndex()),
                        worksOnCursor.getString(WorksOnTableQueryHelper.GetEndDateIndex())
                    )
                );
            } while(worksOnCursor.moveToNext());
        }

        if(employeeContainerList.isEmpty())
        {
            return null;
        }

        ArrayList<Servant> servantList = new ArrayList<Servant>();
        for(EmployeeContainer e : employeeContainerList)
        {
            if(e == null)
            {
                continue;
            }

            Property property = GetPropertyById(e.m_workLocalId); // Lembrar que fecha o banco
            database = getWritableDatabase();
            if(property == null)
            {
                continue;
            }
            Cursor personCursor = database.rawQuery(PersonTableQueryHelper.GetSelectQuery(e.m_workerCpf), null);
            if(!personCursor.moveToFirst())
            {
                continue;
            }

            servantList.add(PersonTableQueryHelper.GetServantFromCursor(personCursor, property, e.m_hireDate, e.m_endDate));
        }

        if(servantList.isEmpty())
        {
            return null;
        }

        Servant[] toReturn = new Servant[servantList.size()];
        int index = -1;
        for(Servant s : servantList)
        {
            ++index;
            if(s == null)
            {
                continue;
            }

            toReturn[index] = servantList.get(index);
        }

        return toReturn;
    }

    private WarehouseManager[] GetWarehouseManagersFromManageWarehouseCursor(Cursor manageWarehouseCursor, SQLiteDatabase database)
    {
        ArrayList<EmployeeContainer> employeeContainerList = new ArrayList<EmployeeContainer>();
        if(manageWarehouseCursor.moveToFirst())
        {
            do
            {
                employeeContainerList.add
                (
                    new EmployeeContainer
                    (
                        manageWarehouseCursor.getString(ManageWarehouseTableQueryHelper.GetManagerCpfIndex()),
                        manageWarehouseCursor.getString(ManageWarehouseTableQueryHelper.GetWarehouseIdIndex()),
                        manageWarehouseCursor.getString(ManageWarehouseTableQueryHelper.GetBeginDateIndex()),
                        manageWarehouseCursor.getString(ManageWarehouseTableQueryHelper.GetEndDateIndex())
                    )
                );
            } while(manageWarehouseCursor.moveToNext());
        }

        if(employeeContainerList.isEmpty())
        {
            return null;
        }

        ArrayList<WarehouseManager> warehouseManagerList = new ArrayList<WarehouseManager>();
        for(EmployeeContainer e : employeeContainerList)
        {
            if(e == null)
            {
                continue;
            }

            Warehouse[] warehouseArray = GetWarehouse(e.m_workLocalId, false); // Lembrar que fecha o banco de dados
            database = getReadableDatabase(); // !!
            if(warehouseArray == null || warehouseArray.length != 1)
            {
                continue;
            }

            Cursor personCursor = database.rawQuery(PersonTableQueryHelper.GetSelectQuery(e.m_workerCpf), null);
            if(!personCursor.moveToFirst())
            {
                continue;
            }

            Person person = PersonTableQueryHelper.GetPersonFromCursor(personCursor);
            WarehouseManager warehouseManager = new WarehouseManager
                    (
                            person,
                            e.m_hireDate,
                            e.m_endDate,
                            warehouseArray[0]
                    );

            warehouseManagerList.add(warehouseManager);
        }

        if(warehouseManagerList.isEmpty())
        {
            return null;
        }

        WarehouseManager[] toReturn = new WarehouseManager[warehouseManagerList.size()];
        int index = -1;
        for(WarehouseManager w : warehouseManagerList)
        {
            ++index;
            if(w == null)
            {
                continue;
            }

            toReturn[index] = warehouseManagerList.get(index);
        }

        return toReturn;
    }

    private Warehouse[] GetWarehousesFromIsWarehouseOwnerCursor(Cursor isWarehouseOwnerCursor, SQLiteDatabase database)
    {
        ArrayList<IsWarehouseOwnerContainer> containerList = new ArrayList<IsWarehouseOwnerContainer>();
        do
        {
            containerList.add
                    (
                            new IsWarehouseOwnerContainer
                                    (
                                            isWarehouseOwnerCursor.getString(IsWarehouseOwnerTableQueryHelper.GetWarehouseIdIndex()),
                                            isWarehouseOwnerCursor.getString(IsWarehouseOwnerTableQueryHelper.GetOwnerCpfIndex()),
                                            isWarehouseOwnerCursor.getString(IsWarehouseOwnerTableQueryHelper.GetBeginDateIndex()),
                                            isWarehouseOwnerCursor.getString(IsWarehouseOwnerTableQueryHelper.GetEndDateIndex())
                                    )
                    );
        } while(isWarehouseOwnerCursor.moveToNext());

        if(containerList.isEmpty())
        {
            return null;
        }

        ArrayList<Warehouse> warehouseList = new ArrayList<Warehouse>();
        for(IsWarehouseOwnerContainer e : containerList)
        {
            if(e == null)
            {
                continue;
            }

            Cursor personCursor = database.rawQuery(PersonTableQueryHelper.GetSelectQuery(e.m_ownerCpf), null);
            if(!personCursor.moveToFirst())
            {
                continue;
            }

            Person owner = new Person
                    (
                            personCursor.getString(PersonTableQueryHelper.GetCpfIndex()),
                            personCursor.getString(PersonTableQueryHelper.GetNameIndex()),
                            personCursor.getString(PersonTableQueryHelper.GetCellphoneIndex()),
                            personCursor.getString(PersonTableQueryHelper.GetBirthDateIndex())
                    );

            Cursor warehouseCursor = database.rawQuery(WarehouseTableQueryHelper.GetSelectQuery(e.m_warehouseId), null);
            if(!warehouseCursor.moveToFirst())
            {
                continue;
            }

            Warehouse warehouse = new Warehouse
                    (
                            warehouseCursor.getString(WarehouseTableQueryHelper.GetIdIndex()),
                            warehouseCursor.getString(WarehouseTableQueryHelper.GetStateNameIndex()),
                            warehouseCursor.getString(WarehouseTableQueryHelper.GetCityNameIndex()),
                            warehouseCursor.getString(WarehouseTableQueryHelper.GetStreetNameIndex()),
                            warehouseCursor.getInt(WarehouseTableQueryHelper.GetResidentialNumberIndex()),
                            e.m_beginDate,
                            e.m_endDate,
                            owner
                    );

            warehouseList.add(warehouse);
        }

        if(warehouseList.isEmpty())
        {
            return null;
        }

        Warehouse[] toReturn = new Warehouse[warehouseList.size()];
        int index = -1;
        for(Warehouse w: warehouseList)
        {
            ++index;
            if(w == null)
            {
                continue;
            }

            toReturn[index] = warehouseList.get(index);
        }

        return toReturn;
    }

    private boolean UpdatePerson(SQLiteDatabase database, Person person)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PersonTableQueryHelper.NAME, person.GetName());
        contentValues.put(PersonTableQueryHelper.CELLPHONE, person.GetCellphone());
        contentValues.put(PersonTableQueryHelper.BIRTH_DATE, person.GetBirthDate());
        DBStatamentHelper updateHelper = PersonTableQueryHelper.GetStatementHelper(person.GetCpf());
        int rows = database.update(PersonTableQueryHelper.PERSON_TABLE, contentValues, updateHelper.m_whereClause, updateHelper.m_args);
        return rows > 0;
    }
}
