package Frameworks.Database.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Policy.Entity.Batch;

public class BatchTableQueryHelper
{
    public static final String BATCH_TABLE = "batch";
    public static final String ID = "id";
    public static final String CREATION_DATE = "creationDate";

    public static String GetCreateCommand()
    {
        return "CREATE TABLE "+BATCH_TABLE+" ("
                +ID +" TEXT PRIMARY KEY,"
                +CREATION_DATE + " TEXT"
                +")";
    }

    public static String GetStatmentHelper(){
        return BATCH_TABLE;
    }

    public static int GetBatchIdIndex()
    {
        return 0;
    }

    public static int GetCreationDateIndex()
    {
        return 1;
    }

    public static String GetSelectAllQuery()
    {
        return "SELECT * FROM "+BATCH_TABLE;
    }

    public static String GetSelectQuery(String id)
    {
        return "SELECT * FROM "+BATCH_TABLE+" WHERE "+ID+"='"+id+"'";
    }

    public static boolean Exists(SQLiteDatabase database, String id)
    {
        Cursor cursor = database.rawQuery(GetSelectQuery(id), null);
        return cursor.moveToFirst();
    }

    public static ContentValues GetContentValues(Batch batch)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, batch.GetId());
        contentValues.put(CREATION_DATE, batch.GetCreationDate());
        return contentValues;

    }
}
