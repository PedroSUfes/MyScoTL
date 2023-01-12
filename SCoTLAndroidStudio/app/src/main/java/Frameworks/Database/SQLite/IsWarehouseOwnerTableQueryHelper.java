package Frameworks.Database.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Policy.Entity.Warehouse;

public class IsWarehouseOwnerTableQueryHelper
{
    public static final String IS_WAREHOUSE_OWNER_TABLE = "isWarehouseOwner";
    public static final String WAREHOUSE_ID = "warehouseId";
    public static final String OWNER_CPF = "ownerCpf";
    public static final String BEGIN_DATE = "beginDate";
    public static final String END_DATE = "endDate";

    public static String GetCreateCommand()
    {
        return "CREATE TABLE "+IS_WAREHOUSE_OWNER_TABLE+"("
                +WAREHOUSE_ID+" TEXT,"
                +OWNER_CPF+" TEXT,"
                +BEGIN_DATE+" TEXT,"
                +END_DATE+" TEXT,"
                +"PRIMARY KEY("+WAREHOUSE_ID+","+OWNER_CPF+","+BEGIN_DATE+")"
                +")";
    }

    public static String GetSelectByOwnerCpfQuery(String cpf)
    {
        return "SELECT * FROM "+IS_WAREHOUSE_OWNER_TABLE+" WHERE "+OWNER_CPF+"='"+cpf+"'";
    }

    public static boolean PersonExists(SQLiteDatabase database, String personCpf)
    {
        Cursor isWarehouseOwnerCursor = database.rawQuery(GetSelectByOwnerCpfQuery(personCpf), null);
        return isWarehouseOwnerCursor.moveToFirst();
    }

    public static ContentValues GetContentValues(String warehouseId, String ownerCpf, String beginbDate)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(WAREHOUSE_ID, warehouseId);
        contentValues.put(OWNER_CPF, ownerCpf);
        contentValues.put(BEGIN_DATE, beginbDate);
        return contentValues;
    }

    public static String GetOwnerCpfOf(SQLiteDatabase database, String warehouseId)
    {
        Cursor cursor = database.rawQuery("SELECT * FROM "+IS_WAREHOUSE_OWNER_TABLE+" WHERE "+WAREHOUSE_ID+"='"+warehouseId+" AND "+END_DATE+" IS NULL", null);
        if(!cursor.moveToFirst())
        {
            return null;
        }

        return new String(cursor.getString(1));
    }
}
