package Frameworks.Database.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class WorksOnTableQueryHelper
{
    public static String WORKS_ON_TABLE = "worksOnTable";
    public static String PERSON_CPF = "personCpf";
    public static String BEGIN_DATE = "beginDate";
    public static String PROPERTY_ID = "propertyId";
    public static String END_DATE = "endDate";

    public static String GetCreateCommand()
    {
        return "CREATE TABLE "+WORKS_ON_TABLE+" ("
                +PERSON_CPF +" TEXT,"
                +BEGIN_DATE + " TEXT,"
                +PROPERTY_ID+" TEXT,"
                +END_DATE + " TEXT,"
                +"PRIMARY KEY("+PERSON_CPF+","+BEGIN_DATE+"),"
                +"FOREIGN KEY("+PROPERTY_ID+") REFERENCES "+PropertyTableQueryHelper.PROPERTY_TABLE+"("+PropertyTableQueryHelper.ID+")"
                +")";
    }

    public static String GetSelectQuery(String personCpf)
    {
        return "SELECT * FROM "+WORKS_ON_TABLE+" WHERE personCpf='"+personCpf+"'";
    }

    public static String GetSelectAllCommand()
    {
        return "SELECT * FROM "+WORKS_ON_TABLE;
    }

    public static int GetPersonCpfIndex()
    {
        return 0;
    }

    public static int GetBeginDateIndex()
    {
        return 1;
    }

    public static int GetPropertyIdIndex()
    {
        return 2;
    }

    public static int GetEndDateIndex()
    {
        return 3;
    }

    public static ContentValues GetContentValue(String personCpf, String beginDate, String propertyId)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PERSON_CPF, personCpf);
        contentValues.put(BEGIN_DATE, beginDate);
        contentValues.put(PROPERTY_ID, PROPERTY_ID);
        return contentValues;
    }

    public static boolean PersonExists(SQLiteDatabase database, String personCpf)
    {
        Cursor worksOnCursor = database.rawQuery(GetSelectQuery(personCpf), null);
        return worksOnCursor.moveToFirst();
    }
}
