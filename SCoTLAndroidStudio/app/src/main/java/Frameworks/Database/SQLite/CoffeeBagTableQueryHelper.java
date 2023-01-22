package Frameworks.Database.SQLite;


import android.database.Cursor;

import Policy.Entity.Batch;
import Policy.Entity.CoffeeBag;
import Policy.Entity.Warehouse;
import android.content.ContentValues;


public class CoffeeBagTableQueryHelper
{
    public static final String COFFEE_BAG_TABLE = "coffeeBag";
    public static final String ID = "id";
    public static final String BATCH_ID = "batchId";
    public static final String WAREHOUSE_ID = "warehouse_Id";
    public static final String STORAGE_DATE = "storageDate";

    public static String GetCreateCommand()
    {
        return "CREATE TABLE "+COFFEE_BAG_TABLE+"("
                +ID+" TEXT,"
                +BATCH_ID+" TEXT,"
                +WAREHOUSE_ID+" TEXT,"
                +STORAGE_DATE+" TEXT,"
                +"PRIMARY KEY("+ID+", "+BATCH_ID+"),"
                +"FOREIGN KEY("+BATCH_ID+") REFERENCES "+BatchTableQueryHelper.BATCH_TABLE+"("+BatchTableQueryHelper.ID+"),"
                +"FOREIGN KEY("+WAREHOUSE_ID+") REFERENCES "+WarehouseTableQueryHelper.WAREHOUSE_TABLE+"("+WarehouseTableQueryHelper.ID+")"
                +")";
    }

    public static String GetSelectAllQuery()
    {
        return "SELECT * FROM "+COFFEE_BAG_TABLE;
    }

    public static String GetSelectQuery(String batchId, String coffeeBagId)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GetSelectAllQuery());
        stringBuilder.append(" WHERE "+BATCH_ID+"='"+batchId+"' AND "+ID+"='"+coffeeBagId+"'");
        return stringBuilder.toString();
    }

    public static DBStatementHelper GetStatementHelper(String batchId, String coffeeBagId)
    {
        return new DBStatementHelper
                (
                    ID+"=? AND "+BATCH_ID+"=?",
                        new String[]{coffeeBagId, batchId}
                );
    }

    public static int GetCoffeeBagIdIndex()
    {
        return 0;
    }

    public static int GetBatchIndex()
    {
        return 1;
    }

    public static int GetWarehouseIndex()
    {
        return 2;
    }

    public static int GetStorageDateIndex()
    {
        return 3;
    }

    public static CoffeeBag GetCoffeeBagFromCoffeeBagCursor(Cursor cursor, Batch batch, Warehouse warehouse) {
        return new CoffeeBag
                (
                        cursor.getString(GetCoffeeBagIdIndex()),
                        batch,
                        warehouse,
                        cursor.getString(GetStorageDateIndex())
                );
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

    public static String GetSelectCoffeeBagsWithBatchId(String batchId)
    {
        return "SELECT * FROM "+COFFEE_BAG_TABLE+" WHERE "+BATCH_ID+"='"+batchId+"'";
    }
}
