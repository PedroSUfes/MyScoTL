package Frameworks.Database.SQLite;

public class BatchTableQueryHelper
{
    public static final String BATCH_TABLE = "batch";
    public static final String ID = "id";
    public static final String CREATION_DATE = "creationDate";

    public static String GetCreateCommand()
    {
        return "CREATE TABLE "+BATCH_TABLE+" ("
                +ID +" TEXT PRIMARY KEY,"
                +CREATION_DATE + " TEXT,"
                +")";
    }
}
