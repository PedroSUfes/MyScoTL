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
import Policy.Entity.Property;
import Policy.Entity.Servant;
import Policy.Entity.Warehouse;
import Policy.Entity.WarehouseManager;
import kotlin.Triple;

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
    public Servant[] GetServants()
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor worksOnCursor = database.rawQuery(WorksOnTableQueryHelper.GetSelectAllThatEndDateIsNullQuery(), null);

        // Cpf do servente, id da propriedade, data contratação
        ArrayList<Triple<String, String, String>> tripleList = new ArrayList<Triple<String, String, String>>();
        if(worksOnCursor.moveToFirst())
        {
            do
            {
                tripleList.add
                (
                    new Triple<String, String, String>
                    (
                        worksOnCursor.getString(WorksOnTableQueryHelper.GetPersonCpfIndex()),
                        worksOnCursor.getString(WorksOnTableQueryHelper.GetPropertyIdIndex()),
                        worksOnCursor.getString(WorksOnTableQueryHelper.GetBeginDateIndex())
                    )
                );
            } while(worksOnCursor.moveToNext());
        }

        if(tripleList.isEmpty())
        {
            MyLog.LogMessage("No servants in database");
            database.close();
            return null;
        }

        ArrayList<Servant> servantList = new ArrayList<Servant>();
        for(Triple<String, String, String> triple : tripleList)
        {
            if(triple == null)
            {
                continue;
            }

            Cursor propertyCursor = database.rawQuery(PropertyTableQueryHelper.GetSelectQuery(triple.component2()), null);
            if(!propertyCursor.moveToFirst())
            {
                continue;
            }

            Property property = PropertyTableQueryHelper.GetPropertyFromCursor(propertyCursor);
            Cursor personCursor = database.rawQuery(PersonTableQueryHelper.GetSelectQuery(triple.component1()), null);
            if(!personCursor.moveToFirst())
            {
                continue;
            }

            servantList.add(PersonTableQueryHelper.GetServantFromCursor(personCursor, property, triple.component3()));
        }

        if(servantList.isEmpty())
        {
            database.close();
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
        database.close();
        return toReturn;
    }

    @Override
    public Servant GetServant(String cpf) {
        return null;
    }

    @Override
    public WarehouseManager[] GetWarehouseManagers() {
        return new WarehouseManager[0];
    }

    @Override
    public WarehouseManager GetWarehouseManager(String cpf) {
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
            MyLog.LogMessage("There's a employee with cpf "+ servantCpf+" already in database");
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
        database.insert(WorksOnTableQueryHelper.WORKS_ON_TABLE, null, WorksOnTableQueryHelper.GetContentValue(servantCpf, servant.GetHiringDate(), property.GetId()));
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
    public Warehouse[] GetWarehouses() {
        return new Warehouse[0];
    }

    @Override
    public Warehouse[] GetWarehouses(String stateName) {
        return new Warehouse[0];
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
    public Warehouse GetWarehouse(String id)
    {
//        SQLiteDatabase database = getReadableDatabase();
//        Cursor cursor = database.rawQuery(WarehouseTableQueryHelper.GetSelectQuery(id), null);
//        if(!cursor.moveToFirst())
//        {
//            return null;
//        }

        return null;
    }

    @Override
    public Warehouse GetWarehouse(String stateName, String streetName, int number) {
        return null;
    }

    @Override
    public Boolean TryRegisterWarehouse(Warehouse warehouse, String beginDate)
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
        database.insert(IsWarehouseOwnerTableQueryHelper.IS_WAREHOUSE_OWNER_TABLE, null, IsWarehouseOwnerTableQueryHelper.GetContentValues(warehouseId, ownerCpf, beginDate));
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
}
