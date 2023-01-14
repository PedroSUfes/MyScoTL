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

    public static String GetSelectByWarehouseIdEndDateNullQuery(String warehouseId)
    {
        return "SELECT * FROM "+IS_WAREHOUSE_OWNER_TABLE+" WHERE "+WAREHOUSE_ID+"='"+warehouseId+"' AND "+END_DATE+" IS NULL";
    }

    public static String GetSelectByWarehouseIdQuery(String id)
    {
        return "SELECT * FROM "+IS_WAREHOUSE_OWNER_TABLE+" WHERE "+WAREHOUSE_ID+"='"+id+"'";
    }
    
    public static String GetSelectAllQuery()
    {
        return "SELECT * FROM "+IS_WAREHOUSE_OWNER_TABLE;
    }

    public static String GetSelectAllFromStateJoinedWithWarehouseTable(String stateName)
    {
        final String warehouseTable = WarehouseTableQueryHelper.WAREHOUSE_TABLE;
        final String warehouseTableId = WarehouseTableQueryHelper.ID;
        final String warehouseTableStateName = WarehouseTableQueryHelper.STATE_NAME;

        return "SELECT "+WAREHOUSE_ID+","+OWNER_CPF+","+BEGIN_DATE+","+END_DATE+
                " FROM "+warehouseTable+
                " JOIN "+IS_WAREHOUSE_OWNER_TABLE+
                " ON "+warehouseTable+"."+warehouseTableId+"="+IS_WAREHOUSE_OWNER_TABLE+"."+WAREHOUSE_ID+
                " WHERE "+warehouseTable+"."+warehouseTableStateName+"='"+stateName+"'";
    }

    public static String GetSelectAllFromStateJoinedWithWarehouseTableNoPastRegister(String stateName)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GetSelectAllFromStateJoinedWithWarehouseTable(stateName));
        stringBuilder.append(" AND "+END_DATE+" IS NULL");
        return stringBuilder.toString();
    }

    public static boolean PersonExists(SQLiteDatabase database, String personCpf)
    {
        Cursor isWarehouseOwnerCursor = database.rawQuery(GetSelectByOwnerCpfQuery(personCpf), null);
        return isWarehouseOwnerCursor.moveToFirst();
    }

    public static ContentValues GetContentValues(String warehouseId, String ownerCpf, String beginDate, String endDate)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(WAREHOUSE_ID, warehouseId);
        contentValues.put(OWNER_CPF, ownerCpf);
        contentValues.put(BEGIN_DATE, beginDate);
        contentValues.put(END_DATE, endDate);
        return contentValues;
    }

    public static String GetOwnerCpfOf(SQLiteDatabase database, String warehouseId)
    {
        Cursor cursor = database.rawQuery("SELECT * FROM "+IS_WAREHOUSE_OWNER_TABLE+" WHERE "+WAREHOUSE_ID+"='"+warehouseId+" AND "+END_DATE+" IS NULL", null);
        if(!cursor.moveToFirst())
        {
            return null;
        }

        return new String(cursor.getString(GetOwnerCpfIndex()));
    }

    public static DBStatamentHelper GetStatementHelperEndDateNull(String warehouseId)
    {
        return new DBStatamentHelper
                (
                        WAREHOUSE_ID+"=? AND "+END_DATE+" IS NULL",
                        new String[]{warehouseId}
                );
    }

    public static int GetWarehouseIdIndex()
    {
        return 0;
    }

    public static int GetOwnerCpfIndex()
    {
        return 1;
    }

    public static int GetBeginDateIndex()
    {
        return 2;
    }

    public static int GetEndDateIndex()
    {
        return 3;
    }
}
