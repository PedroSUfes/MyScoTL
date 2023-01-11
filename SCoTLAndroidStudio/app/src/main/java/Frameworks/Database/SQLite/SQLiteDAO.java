package Frameworks.Database.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        context.deleteDatabase(DB_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(PersonTableQueryHelper.GetCreationalCommand());
//        sqLiteDatabase.execSQL(PersonTableQueryHelper.GetCreationalCommand());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
    }

    public void Test()
    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PersonTableQueryHelper.PRIMARY_KEY, "1234");
        contentValues.put(PersonTableQueryHelper.NAME, "Pedro");
        contentValues.put(PersonTableQueryHelper.BIRTH_DATE, "20/06/2001");
        contentValues.put(PersonTableQueryHelper.TELEPHONE, "0800");

        long result = database.insert(PersonTableQueryHelper.PERSON_TABLE, null, contentValues);
        database.close();
    }

    public Person[] GetAllPersons()
    {
        final SQLiteDatabase database = getWritableDatabase();
        Person[] toReturn = null;

        Cursor personCursor = database.rawQuery(PersonTableQueryHelper.GetSelectAllQuery(), null);

        ArrayList<Person> personList = new ArrayList<Person>();
        if(personCursor.moveToFirst())
        {
            do
            {
                personList.add
                (
                   new Person
                   (
                       personCursor.getString(0),
                       personCursor.getString(1),
                       personCursor.getString(2),
                       personCursor.getString(3)
                   )
                );
            } while(personCursor.moveToNext());
        }

        if(personList.isEmpty())
        {
            return null;
        }

        toReturn = new Person[personList.size()];
        int index = -1;
        for(Person person : personList)
        {
            ++index;
            if(person == null)
            {
                continue;
            }
            toReturn[index] = person;
        }

        return toReturn;
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
    public Boolean TryRegisterBatch(Batch batch) {
        return null;
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
    public Servant[] GetServants() {
        return new Servant[0];
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
    public Boolean TryRegisterServant(Servant servant, String beginDate) {
        return null;
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
    public Property GetPropertyById(String id) {
        return null;
    }

    @Override
    public Boolean TryRegisterProperty(Property property) {
        return null;
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
