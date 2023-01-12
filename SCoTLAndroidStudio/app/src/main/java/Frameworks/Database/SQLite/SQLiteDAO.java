package Frameworks.Database.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import java.util.ArrayList;

import Policy.BusinessRules.Adapters.*;
import Policy.Entity.Batch;
import Policy.Entity.CoffeeBag;
import Policy.Entity.Employee;
import Policy.Entity.Property;
import Policy.Entity.Servant;
import Policy.Entity.Warehouse;
import Policy.Entity.WarehouseManager;
import Policy.Entity.Person;
import kotlin.Triple;

public class SQLiteDAO extends SQLiteOpenHelper
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
        SQLiteDatabase database = getReadableDatabase();
        if(BatchTableQueryHelper.Exists(database, batch.GetId()))
        {
            database.close();
            return false;
        }

        database.close();
        database = getWritableDatabase();
        database.insert(BatchTableQueryHelper.BATCH_TABLE, null, BatchTableQueryHelper.GetContentValues(batch));
        database.close();
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
        Cursor cursor = database.rawQuery(WorksOnTableQueryHelper.GetSelectAllCommand(), null);
        // Person cpf, property id, begin date
        ArrayList<Triple<String, String, String>> tripleList = new ArrayList<Triple<String, String, String>>();
        if(cursor.moveToFirst())
        {
            do
            {
                tripleList.add
                (
                    new Triple<String, String, String>
                    (
                        cursor.getString(WorksOnTableQueryHelper.GetPersonCpfIndex()),
                        cursor.getString(WorksOnTableQueryHelper.GetPropertyIdIndex()),
                        cursor.getString(WorksOnTableQueryHelper.GetBeginDateIndex())
                    )
                );
            } while(cursor.moveToNext());
        }

        if(tripleList.isEmpty())
        {
            database.close();
            return null;
        }

        ArrayList<Servant> servantArrayList = new ArrayList<Servant>();
        for(Triple<String, String, String> triple : tripleList)
        {
            if(triple == null)
            {
                continue;
            }

            Cursor personCursor = database.rawQuery(PersonTableQueryHelper.GetSelectQuery(triple.component1()), null);
            Cursor propertyCursor = database.rawQuery(PropertyTableQueryHelper.GetSelectQuery(triple.component2()), null);

            servantArrayList.add
             (
                new Servant
                (
                    personCursor.getString(PersonTableQueryHelper.GetCpfIndex()),
                    personCursor.getString(PersonTableQueryHelper.GetNameIndex()),
                    personCursor.getString(PersonTableQueryHelper.GetTelephoneIndex()),
                    personCursor.getString(PersonTableQueryHelper.GteBirthDateIndex()),
                    triple.component3(),
                    new Property
                    (
                        propertyCursor.getString(PropertyTableQueryHelper.GetIdIndex()),
                        propertyCursor.getString(PropertyTableQueryHelper.GetPropertyNameIndex()),
                        propertyCursor.getString(PropertyTableQueryHelper.GetStateNameIndex()),
                        propertyCursor.getString(PropertyTableQueryHelper.GetCityNameIndex()),
                        propertyCursor.getString(PropertyTableQueryHelper.GetStreetNameIndex()),
                        propertyCursor.getInt(PropertyTableQueryHelper.GetResidentialNumberIndex())
                    )
                )
             );
        }

        if(servantArrayList.isEmpty())
        {
            database.close();
            return null;
        }

        Servant[] toReturn = new Servant[servantArrayList.size()];
        int index = -1;
        for(Servant s : servantArrayList)
        {
            ++index;
            if(s == null)
            {
                continue;
            }

            toReturn[index] = servantArrayList.get(index);
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

        if
        (
            !PropertyTableQueryHelper.Exists(database, property.GetId()) ||
            WorksOnTableQueryHelper.PersonExists(database, servantCpf) ||
            IsWarehouseOwnerTableQueryHelper.PersonExists(database, servantCpf) ||
            ManageWarehouseTableQueryHelper.PersonExists(database, servantCpf)
        )
        {
            database.close();
            return null;
        }

        if(!PersonTableQueryHelper.Exists(database, servantCpf))
        {
            database.close();
            database = getWritableDatabase();
            database.insert(PersonTableQueryHelper.PERSON_TABLE, null, PersonTableQueryHelper.GetContentValues(servant));
        }

        database.close();
        database = getWritableDatabase();
        database.insert(WorksOnTableQueryHelper.WORKS_ON_TABLE, null, WorksOnTableQueryHelper.GetContentValue(servantCpf, servant.GetHiringDate(), property.GetId()));

        return true;
    }

    @Override
    public Boolean TryRegisterWarehouseManager(WarehouseManager warehouseManager, String beginDate) {
        return null;
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
        Property toReturn = PropertyTableQueryHelper.GetPropertyFromCursor(cursor);
        database.close();
        return toReturn;
    }

    @Override
    public Boolean TryRegisterProperty(Property property)
    {
        SQLiteDatabase database = getReadableDatabase();
        if(PropertyTableQueryHelper.Exists(database, property.GetId()))
        {
            database.close();
            return false;
        }

        database.close();
        database = getWritableDatabase();
        database.insert(PropertyTableQueryHelper.PROPERTY_TABLE, null, PropertyTableQueryHelper.GetContentValues(property));
        database.close();
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
    public Warehouse GetWarehouse(String id) {
        return null;
    }

    @Override
    public Warehouse GetWarehouse(String stateName, String streetName, int number) {
        return null;
    }

    @Override
    public Boolean TryRegisterWarehouse(Warehouse warehouse, String beginDate) {
        return null;
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
