package Frameworks.Database.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Policy.BusinessRules.Adapters.*;
import Policy.Entity.Batch;
import Policy.Entity.CoffeeBag;
import Policy.Entity.Employee;
import Policy.Entity.Property;
import Policy.Entity.Servant;
import Policy.Entity.Warehouse;
import Policy.Entity.WarehouseManager;
<<<<<<< Updated upstream
import Policy.Entity.Person;
=======
import kotlin.Pair;
import kotlin.Triple;
>>>>>>> Stashed changes

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
        sqLiteDatabase.execSQL(BatchTableQueryHelper.GetCreateCommand());
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

    //
    @Override
    public Batch[] GetBatches() {

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
    public Batch GetBatch(String batchId) {

        return null;
    }

    //Teoricamente feita e.e
    @Override
    public Boolean TryRegisterBatch(Batch batch) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(BatchTableQueryHelper.ID, batch.GetId());
        cv.put(BatchTableQueryHelper.CREATION_DATE, batch.GetCreationDate());

        long insert = db.insert(BatchTableQueryHelper.BATCH_TABLE, null, cv);

        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
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
