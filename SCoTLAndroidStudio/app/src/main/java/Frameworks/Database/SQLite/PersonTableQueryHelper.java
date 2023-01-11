package Frameworks.Database.SQLite;

public class PersonTableQueryHelper
{
    public static final String PERSON_TABLE = "person";
    public static final String PRIMARY_KEY = "cpf";
    public static final String NAME = "name";
    public static final String BIRTH_DATE = "birth_date";
    public static final String TELEPHONE = "telephone";

    public static String GetCreateCommand()
    {
        return "CREATE TABLE "+PERSON_TABLE+" ("
                +PRIMARY_KEY +" TEXT PRIMARY KEY,"
                +NAME + " TEXT,"
                +BIRTH_DATE + " TEXT,"
                +TELEPHONE + " TEXT"
                +")";
    }

    public static String GetSelectAllQuery()
    {
        return "SELECT * FROM "+PERSON_TABLE;
    }
}
