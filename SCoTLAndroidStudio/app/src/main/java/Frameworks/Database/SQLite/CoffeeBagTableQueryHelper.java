package Frameworks.Database.SQLite;

import android.database.Cursor;

import Policy.Entity.Batch;
import Policy.Entity.CoffeeBag;
import Policy.Entity.Warehouse;

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
                +"PRIMARY KEY("+ID+", "+BATCH_ID+"),"
                +"FOREIGN KEY("+WAREHOUSE_ID+") REFERENCES "+WarehouseTableQueryHelper.WAREHOUSE_TABLE+"("+WarehouseTableQueryHelper.ID+")"
                +")";
    }

    public static String GetSelectAllQuery()
    {
        return "SELECT * FROM "+COFFEE_BAG_TABLE;
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

    public static CoffeeBag GetCoffeeBagFromCoffeeBagCursor(Cursor cursor, Batch batch, Warehouse warehouse)
    {
        return new CoffeeBag
                (
                        cursor.getString(GetCoffeeBagIdIndex()),
                        batch,
                        warehouse,
                        cursor.getString(GetStorageDateIndex())
                );
    }
}
