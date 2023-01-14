package Frameworks.Database.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Policy.Entity.Property;

public class PropertyTableQueryHelper
{
    public static final String PROPERTY_TABLE = "property";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String STATE_NAME = "stateName";
    public static final String CITY_NAME = "cityName";
    public static final String STREET_NAME = "streetName";
    public static final String RESIDENTIAL_NUMBER = "residentialNumber";

    public static String GetCreateCommand()
    {
        return "CREATE TABLE "+PROPERTY_TABLE+" ("
                +ID +" TEXT PRIMARY KEY,"
                +NAME + " TEXT,"
                +STATE_NAME + " TEXT,"
                +CITY_NAME + " TEXT,"
                +STREET_NAME + " TEXT,"
                +RESIDENTIAL_NUMBER + " INTEGER"
                +")";
    }

    public static String GetSelectQuery(String id)
    {
        return "SELECT * FROM "+PROPERTY_TABLE+" WHERE "+ID+"='"+id+"'";
    }

    public static boolean Exists(SQLiteDatabase database, String propertyId)
    {
        Cursor propertyCursor = database.rawQuery(GetSelectQuery(propertyId), null);
        return propertyCursor.moveToFirst();
    }

    public static ContentValues GetContentValues(Property property)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, property.GetId());
        contentValues.put(NAME, property.GetPropertyName());
        contentValues.put(STATE_NAME, property.GetStateName());
        contentValues.put(CITY_NAME, property.GetCityName());
        contentValues.put(STREET_NAME, property.GetStreetName());
        contentValues.put(RESIDENTIAL_NUMBER, property.GetNumber());
        return contentValues;
    }

    public static Property GetPropertyFromCursor(Cursor cursor)
    {
        return new Property
        (
            cursor.getString(GetIdIndex()),
            cursor.getString(GetPropertyNameIndex()),
            cursor.getString(GetStateNameIndex()),
            cursor.getString(GetCityNameIndex()),
            cursor.getString(GetStreetNameIndex()),
            cursor.getInt(GetResidentialNumberIndex())
        );
    }

    public static DBStatamentHelper GetStatementHelper(String id)
    {
        return new DBStatamentHelper
                (
                    ID+"=?",
                        new String[]{id}
                );
    }

    public static int GetIdIndex()
    {
        return 0;
    }

    public static int GetPropertyNameIndex()
    {
        return 1;
    }

    public static int GetStateNameIndex()
    {
        return 2;
    }

    public static int GetCityNameIndex()
    {
        return 3;
    }

    public static int GetStreetNameIndex()
    {
        return 4;
    }

    public static int GetResidentialNumberIndex()
    {
        return 5;
    }
}
