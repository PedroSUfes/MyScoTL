package Frameworks.Database.SQLite;

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
                +RESIDENTIAL_NUMBER + " INTEGER,"
                +")";
    }
}
