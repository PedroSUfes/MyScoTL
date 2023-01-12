package Frameworks.Database.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Policy.Adapters.MyLog;
import Policy.BusinessRules.Adapters.*;
import Policy.Entity.Batch;
import Policy.Entity.CoffeeBag;
import Policy.Entity.Employee;
import Policy.Entity.Person;
import Policy.Entity.Property;
import Policy.Entity.Servant;
import Policy.Entity.Warehouse;
import Policy.Entity.WarehouseManager;

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

    // Queries
    @Override
    public Boolean TryLogin(String login, String password)
    {
        return null;
    }

    @Override
    public Batch[] GetBatches() {
        return new Batch[0];
    }

    @Override
    public Batch GetBatch(String batchId) {




        return null;
    }

    //Teoricamente feita e.e
    @Override
    public Boolean TryRegisterBatch(Batch batch)
    {
        SQLiteDatabase database = getWritableDatabase();
        long result = database.insert(BatchTableQueryHelper.BATCH_TABLE, null, BatchTableQueryHelper.GetContentValues(batch));
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
    public Boolean TryRegisterCoffeeBag(CoffeeBag coffeeBag) {
        return null;
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
//        SQLiteDatabase database = getReadableDatabase();
//        Cursor manageWarehouseCursor = database.rawQuery(ManageWarehouseTableQueryHelper.)
        return null;
    }

    @Override
    public Boolean TryRegisterServant(Servant servant)
    {
        SQLiteDatabase database = getReadableDatabase();
        Property property = servant.GetProperty();
        String servantCpf = servant.GetCpf();

        // Caso já estejacadastrado
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
            database.insert(PersonTableQueryHelper.PERSON_TABLE, null, PersonTableQueryHelper.GetContentValue(servant));
        }
        database.insert
                (
                        WorksOnTableQueryHelper.WORKS_ON_TABLE,
                        null,
                        WorksOnTableQueryHelper.GetContentValue(servantCpf, property.GetId(), servant.GetHiringDate(), servant.GetEndDate()));
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
            database.insert(PersonTableQueryHelper.PERSON_TABLE, null, PersonTableQueryHelper.GetContentValue(warehouseManager));
        }

        database.insert(ManageWarehouseTableQueryHelper.MANAGE_WAREHOUSE_TABLE, null, ManageWarehouseTableQueryHelper.GetContentValue(warehouseId, warehouseManagerCpf, warehouseManager.GetHiringDate()));
        database.close();
        return true;
    }

    @Override
    public Boolean TryUpdateServant(Servant servant, String date) {
        return null;
    }

    @Override
    public Boolean TryUpdateWarehouseManager(WarehouseManager warehouseManager, String date) {
        return null;
    }

    @Override
    public Boolean TryRemoveEmployee(String cpf) {
        return null;
    }

    @Override
    public Property[] GetProperties() {
        return new Property[0];
    }

    @Override
    public Property GetPropertyById(String id)
    {
        SQLiteDatabase database = getReadableDatabase();
        if(!PropertyTableQueryHelper.Exists(database, id))
        {
            database.close();
            return null;
        }

        Cursor cursor = database.rawQuery(PropertyTableQueryHelper.GetSelectQuery(id), null);
        if(!cursor.moveToFirst())
        {
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
        long result = database.insert(PropertyTableQueryHelper.PROPERTY_TABLE, null, PropertyTableQueryHelper.GetContentValues(property));
        database.close();
        if(result < 0)
        {
            return false;
        }
        return true;
    }

    @Override
    public Boolean TryUpdateProperty(Property property) {
        return null;
    }

    @Override
    public Boolean TryRemoveProperty(String id) {
        return null;
    }

    @Override
    public Warehouse[] GetWarehouses()
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor isWarehouseOwnerCursor = database.rawQuery(IsWarehouseOwnerTableQueryHelper.GetSelectAllQuery(), null);
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
    public Warehouse[] GetWarehouses(String stateName)
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor iSWarehouseOwnerCursor = database.rawQuery(IsWarehouseOwnerTableQueryHelper.GetSelectAllQuery(), null);
        if(!iSWarehouseOwnerCursor.moveToFirst())
        {
            MyLog.LogMessage("No warehouses in database");
            database.close();
            return null;
        }
        return null;
    }

    @Override
    public Warehouse[] GetWarehouses(String stateName, String streetName) {
        return new Warehouse[0];
    }

    @Override
    public Warehouse[] GetWarehousesByOwnerCpf(String ownerCpf) {
        return new Warehouse[0];
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
    public Warehouse GetWarehouse(String stateName, String streetName, int number) {
        return null;
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
        database.insert(PersonTableQueryHelper.PERSON_TABLE, null, PersonTableQueryHelper.GetContentValue(warehouse.GetOwner()));
        database.insert(WarehouseTableQueryHelper.WAREHOUSE_TABLE, null, WarehouseTableQueryHelper.GetContentValue(warehouse));
        database.insert
                (
                        IsWarehouseOwnerTableQueryHelper.IS_WAREHOUSE_OWNER_TABLE,
                        null,
                        IsWarehouseOwnerTableQueryHelper.GetContentValues(warehouseId, ownerCpf, warehouse.GetBeginDate(), warehouse.GetEndDate())
                );
        database.close();
        return true;
    }

    @Override
    public Boolean TryUpdateWarehouse(Warehouse warehouse, String date) {
        return null;
    }

    @Override
    public Boolean TryRemoveWarehouse(String id) {
        return null;
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

        for(EmployeeContainer e : employeeContainerList)
        {
            if(e == null)
            {
                continue;
            }

            // Construir Warehouse a partir de seu id. Deve-se achar o cpf de seu dono atual
            String ownerCpf = IsWarehouseOwnerTableQueryHelper.GetOwnerCpfOf(database, e.m_workLocalId);
            Cursor personCursor = database.rawQuery(PersonTableQueryHelper.GetSelectQuery(ownerCpf), null);
            Person owner = PersonTableQueryHelper.GetPersonFromCursor(personCursor);
//            Warehouse warehouse = GetWarehouse()
        }

        return null;
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
}
