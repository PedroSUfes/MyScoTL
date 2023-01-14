package Frameworks.Database.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Policy.Entity.Person;
import Policy.Entity.Warehouse;

public class WarehouseTableQueryHelper
{
    public static final String WAREHOUSE_TABLE = "warehouse";
    public static final String ID = "id";
    public static final String STATE_NAME = "stateName";
    public static final String CITY_NAME = "cityName";
    public static final String STREET_NAME = "streetName";
    public static final String RESIDENTIAL_NUMBER = "residentialNumber";

    public static String GetCreateCommand()
    {
        return "CREATE TABLE "+WAREHOUSE_TABLE+" ("
                +ID +" TEXT PRIMARY KEY,"
                +STATE_NAME + " TEXT,"
                +CITY_NAME + " TEXT,"
                +STREET_NAME + " TEXT,"
                +RESIDENTIAL_NUMBER + " INTEGER"
                +")";
    }

    public static String GetSelectQuery(String id)
    {
        return "SELECT * FROM "+WAREHOUSE_TABLE+" WHERE "+ID+"='"+id+"'";
    }

    public static String GetSelectAllFromState(String stateName)
    {
        return "SELECT * FROM "+WAREHOUSE_TABLE+" WHERE "+STATE_NAME+"='"+stateName+"'";
    }

    public static boolean Exists(SQLiteDatabase database, String id)
    {
        Cursor cursor = database.rawQuery(GetSelectQuery(id), null);
        return cursor.moveToFirst();
    }

    public static ContentValues GetContentValue(Warehouse warehouse)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, warehouse.GetId());
        contentValues.put(STATE_NAME, warehouse.GetStateName());
        contentValues.put(CITY_NAME, warehouse.GetCityName());
        contentValues.put(STREET_NAME, warehouse.GetStreetName());
        contentValues.put(RESIDENTIAL_NUMBER, warehouse.GetNumber());
        return contentValues;
    }

    public static Warehouse GetWarehouseFromCursor(Cursor cursor, Person owner, String beginDate, String endDate)
    {
        return new Warehouse
        (
            cursor.getString(GetIdIndex()),
            cursor.getString(GetStateNameIndex()),
            cursor.getString(GetCityNameIndex()),
            cursor.getString(GetStateNameIndex()),
            cursor.getInt(GetResidentialNumberIndex()),
            beginDate,
            endDate,
            owner
        );
    }

    public static int GetIdIndex()
    {
        return 0;
    }

    public static int GetStateNameIndex()
    {
        return 1;
    }

    public static int GetCityNameIndex()
    {
        return 2;
    }

    public static int GetStreetNameIndex()
    {
        return 3;
    }

    public static int GetResidentialNumberIndex()
    {
        return 4;
    }
}
