package Frameworks.Database.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ManageWarehouseTableQueryHelper
{
    public static final String MANAGE_WAREHOUSE_TABLE = "manageWarehouse";
    public static final String WAREHOUSE_ID = "warehouseId";
    public static final String BEGIN_DATE = "beginDate";
    public static final String END_DATE = "endDate";
    public static final String PERSON_CPF = "personCpf";

    public static String GetCreateCommand()
    {
        return "CREATE TABLE "+MANAGE_WAREHOUSE_TABLE+"("
                +WAREHOUSE_ID+" TEXT,"
                +BEGIN_DATE+" TEXT,"
                +PERSON_CPF+" TEXT,"
                +END_DATE+" TEXT,"
                +"PRIMARY KEY("+WAREHOUSE_ID+","+PERSON_CPF+","+BEGIN_DATE+"),"
                +"FOREIGN KEY("+WAREHOUSE_ID+") REFERENCES "+WarehouseTableQueryHelper.WAREHOUSE_TABLE+"("+WarehouseTableQueryHelper.ID+"),"
                +"FOREIGN KEY("+PERSON_CPF+") REFERENCES "+PersonTableQueryHelper.PERSON_TABLE+"("+PersonTableQueryHelper.CPF+"),"
                +")";
    }

    public static String GetSelectByPersonCpfQuery(String personCpf)
    {
        return "SELECT * FROM "+MANAGE_WAREHOUSE_TABLE+" WHERE "+PERSON_CPF+"='"+personCpf+"'";
    }

    public static String GetSelectByPersonCpfNoPastRegister(String personCpf)
    {
        return "SELECT * FROM "+MANAGE_WAREHOUSE_TABLE+" WHERE "+PERSON_CPF+"='"+personCpf+"' AND "+END_DATE+" IS NULL";
    }

    public static String GetSelectByWarehouseIdQuery(String warehouseId)
    {
        return "SELECT * FROM "+MANAGE_WAREHOUSE_TABLE+" WHERE "+WAREHOUSE_ID+"='"+warehouseId+"'";
    }

    public static String GetSelectAllQuery()
    {
        return "SELECT * FROM "+MANAGE_WAREHOUSE_TABLE;
    }

    public static boolean PersonExists(SQLiteDatabase database, String personCpf)
    {
        Cursor cursor = database.rawQuery(GetSelectByPersonCpfQuery(personCpf), null);
        return  cursor.moveToFirst();
    }

    public static boolean HaveWarehouseManager(SQLiteDatabase database, String warehouseId)
    {
        Cursor cursor = database.rawQuery("SELECT * FROM "+MANAGE_WAREHOUSE_TABLE+" WHERE "+WAREHOUSE_ID+"='"+warehouseId+"' AND "+END_DATE+" IS NULL", null);
        return cursor.moveToFirst();
    }

    public static ContentValues GetContentValue(String warehouseId, String managerCpf, String beginDate)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(WAREHOUSE_ID, warehouseId);
        contentValues.put(BEGIN_DATE, beginDate);
        contentValues.put(PERSON_CPF, managerCpf);
        return contentValues;
    }

    public static DBStatementHelper GetStatementHelper(String workerCpf)
    {
        return new DBStatementHelper
                (
                        PERSON_CPF+"=?",
                        new String[] {workerCpf}
                );
    }

    public static DBStatementHelper GetStatementHelper(String workerCpf, String warehouseId, String beginDate)
    {
        return new DBStatementHelper
                (
                        PERSON_CPF+"=? AND "+WAREHOUSE_ID+"=? AND "+BEGIN_DATE+"=?",
                        new String[]{workerCpf, warehouseId, beginDate}
                );
    }

    public static int GetWarehouseIdIndex()
    {
        return 0;
    }

    public static int GetBeginDateIndex()
    {
        return 1;
    }

    public static int GetManagerCpfIndex()
    {
        return 2;
    }

    public static int GetEndDateIndex()
    {
        return 3;
    }
}
