package Frameworks.Database.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
                +"PRIMARY KEY("+WAREHOUSE_ID+","+OWNER_CPF+","+BEGIN_DATE+"),"
                +"FOREIGN KEY("+WAREHOUSE_ID+") REFERENCES "+WarehouseTableQueryHelper.WAREHOUSE_TABLE+"("+WarehouseTableQueryHelper.ID+"),"
                +"FOREIGN KEY("+OWNER_CPF+") REFERENCES "+PersonTableQueryHelper.PERSON_TABLE+"("+PersonTableQueryHelper.CPF+")"
                +")";
    }

    public static String GetSelectWithOwnerCpfQuery(String cpf)
    {
        return "SELECT * FROM "+IS_WAREHOUSE_OWNER_TABLE+" WHERE "+OWNER_CPF+"='"+cpf+"'";
    }

    public static String GetSelectWithOwnerCpfNoPastRegisterQuery(String cpf)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GetSelectWithOwnerCpfQuery(cpf));
        stringBuilder.append(" AND "+END_DATE+" IS NULL");
        return stringBuilder.toString();
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

    public static String GetSelectAllNoPastRegister()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GetSelectAllQuery());
        stringBuilder.append(" WHERE "+END_DATE+" IS NULL");
        return stringBuilder.toString();
    }

    public static String GetSelectAllFromStateJoinedWithWarehouseTableQuery(String stateName)
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

    public static String GetSelectAllFromStateCityAndStreetJoinedWithWarehouseTableQuery(String stateName, String cityName, String streetName)
    {
        final String warehouseTable = WarehouseTableQueryHelper.WAREHOUSE_TABLE;
        final String warehouseStreetName = WarehouseTableQueryHelper.STREET_NAME;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GetSelectAllFromStateAndCityJoinedWithWarehouseTableQuery(stateName, cityName));
        stringBuilder.append(" AND "+warehouseTable+"."+warehouseStreetName+"='"+streetName+"'");
        return  stringBuilder.toString();
    }

    public static String GetSelectAllFromStateCityAndStreetJoinedWithWarehouseTableNoPastRegisterQuery(String stateName, String cityName, String streetName)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GetSelectAllFromStateCityAndStreetJoinedWithWarehouseTableQuery(stateName, cityName, streetName));
        stringBuilder.append(" AND "+END_DATE+" IS NULL");
        return stringBuilder.toString();
    }

    public static String GetSelectWarehouseFromAddressQuery(String stateName, String cityName, String streetName, int number)
    {
        final String warehouseTable = WarehouseTableQueryHelper.WAREHOUSE_TABLE;
        final String warehouseNumber = WarehouseTableQueryHelper.RESIDENTIAL_NUMBER;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GetSelectAllFromStateCityAndStreetJoinedWithWarehouseTableQuery(stateName, cityName, streetName));
        stringBuilder.append(" AND "+warehouseTable+"."+warehouseNumber+"="+number);
        return stringBuilder.toString();
    }

    public static String GetSelectWarehouseFromAddressNoPastRegisterQuery(String stateName, String cityName, String streetName, int number)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GetSelectWarehouseFromAddressQuery(stateName, cityName, streetName, number));
        stringBuilder.append(" AND "+END_DATE+" IS NULL");
        return stringBuilder.toString();
    }

    public static String GetSelectAllFromStateAndCityJoinedWithWarehouseTableQuery(String stateName, String cityName)
    {
        final String warehouseTable = WarehouseTableQueryHelper.WAREHOUSE_TABLE;
        final String warehouseCityName = WarehouseTableQueryHelper.CITY_NAME;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GetSelectAllFromStateJoinedWithWarehouseTableQuery(stateName));
        stringBuilder.append(" AND "+warehouseTable+"."+warehouseCityName+"='"+cityName+"'");
        return stringBuilder.toString();
    }

    public static String GetSelectAllFromStateAndCityJoinedWithWarehouseTableNoPastRegisterQuery(String stateName, String cityName)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GetSelectAllFromStateAndCityJoinedWithWarehouseTableQuery(stateName, cityName));
        stringBuilder.append(" AND "+END_DATE+" IS NULL");
        return stringBuilder.toString();
    }

    public static String GetSelectAllFromStateJoinedWithWarehouseTableNoPastRegisterQuery(String stateName)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GetSelectAllFromStateJoinedWithWarehouseTableQuery(stateName));
        stringBuilder.append(" AND "+END_DATE+" IS NULL");
        return stringBuilder.toString();
    }

    public static boolean PersonExists(SQLiteDatabase database, String personCpf)
    {
        Cursor isWarehouseOwnerCursor = database.rawQuery(GetSelectWithOwnerCpfQuery(personCpf), null);
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

    public static DBStatementHelper GetStatementHelper(String warehouseId)
    {
        return new DBStatementHelper
                (
                        WAREHOUSE_ID+"=?",
                        new String[]{warehouseId}
                );
    }

    public static DBStatementHelper GetStatementHelperEndDateNull(String warehouseId)
    {
        return new DBStatementHelper
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
