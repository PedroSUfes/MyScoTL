package Frameworks.Database.SQLite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BuyCoffeeBagTableQueryHelper
{
    public static final String BUY_COFFEE_BAG_TABLE = "buyCoffeeBagTable";
    public static final String BATCH_ID = "batchId";
    public static final String COFFEE_BAG_ID = "coffeeBagId";
    public static final String BUYER_CPF = "buyerCpf";

    public static String GetCreateCommand()
    {
        return "CREATE TABLE "+BUY_COFFEE_BAG_TABLE+"("
                +BATCH_ID+" TEXT,"
                +COFFEE_BAG_ID+" TEXT,"
                +BUYER_CPF+" TEXT,"
                +"Primary KEY("+BATCH_ID+", "+COFFEE_BAG_ID+"),"
                +"FOREIGN KEY("+BATCH_ID+") REFERENCES "+BatchTableQueryHelper.BATCH_TABLE+"("+BatchTableQueryHelper.ID+")"
                +"FOREIGN KEY("+COFFEE_BAG_ID+") REFERENCES "+CoffeeBagTableQueryHelper.COFFEE_BAG_TABLE+"("+CoffeeBagTableQueryHelper.ID+")"
                +"FOREIGN KEY("+BUYER_CPF+") REFERENCES "+PersonTableQueryHelper.PERSON_TABLE+"("+PersonTableQueryHelper.CPF +")"
                +")";
    }

    public static String GetSelectPersonQuery(String cpf)
    {
        return "SELECT * FROM "+BUY_COFFEE_BAG_TABLE+" WHERE "+BUYER_CPF+"='"+cpf+"'";
    }

    public static boolean Exists(SQLiteDatabase database, String cpf)
    {
        Cursor cursor = database.rawQuery(GetSelectPersonQuery(cpf), null);
        return cursor.moveToFirst();
    }
}
