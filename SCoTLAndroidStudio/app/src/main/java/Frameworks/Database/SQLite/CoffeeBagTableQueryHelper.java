package Frameworks.Database.SQLite;

import android.content.ContentValues;

import Policy.Entity.CoffeeBag;

public class CoffeeBagTableQueryHelper
{
    public static final String COFFEE_BAG_TABLE = "coffeeBag";
    public static final String ID = "id";
    public static final String BATCH_ID = "bachId";
    public static final String WAREHOUSE_ID = "warehouse_Id";
    public static final String STORAGE_DATE = "storageDate";

    public static String GetCreateCommand()
    {
        return "CREATE TABLE "+COFFEE_BAG_TABLE+"("
                +ID+" TEXT,"
                +BATCH_ID+" TEXT,"
                +WAREHOUSE_ID+" TEXT,"
                +STORAGE_DATE+" TEXT,"
                +"Primary KEY("+ID+", "+BATCH_ID+"),"
                +"FOREIGN KEY("+BATCH_ID+") REFERENCES "+BatchTableQueryHelper.BATCH_TABLE+"("+BatchTableQueryHelper.ID+"),"
                +"FOREIGN KEY("+WAREHOUSE_ID+") REFERENCES "+WarehouseTableQueryHelper.WAREHOUSE_TABLE+"("+WarehouseTableQueryHelper.ID+")"
                +")";
    }

    public static ContentValues GetContentValues(CoffeeBag coffeeBag)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, coffeeBag.GetId());
        contentValues.put(BATCH_ID, coffeeBag.GetBatch().GetId());
        contentValues.put(WAREHOUSE_ID, coffeeBag.GetWarehouse().GetId());
        contentValues.put(STORAGE_DATE, coffeeBag.GetStorageDate());
        return contentValues;
    }
}
