package Frameworks.Database.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Policy.Entity.Person;
import Policy.Entity.Property;
import Policy.Entity.Servant;

public class PersonTableQueryHelper
{
    public static final String PERSON_TABLE = "person";
    public static final String CPF = "cpf";
    public static final String NAME = "name";
    public static final String BIRTH_DATE = "birth_date";
    public static final String CELLPHONE = "telephone";

    public static String GetCreateCommand()
    {
        return "CREATE TABLE "+PERSON_TABLE+" ("
                +CPF +" TEXT PRIMARY KEY,"
                +NAME + " TEXT,"
                +BIRTH_DATE + " TEXT,"
                +CELLPHONE + " TEXT"
                +")";
    }

    public static String GetSelectAllQuery()
    {
        return "SELECT * FROM "+PERSON_TABLE;
    }

    public static String GetSelectQuery(String cpf)
    {
        return "SELECT * FROM "+PERSON_TABLE+" WHERE cpf='"+cpf+"'";
    }

    public static int GetCpfIndex()
    {
        return 0;
    }

    public static int GetNameIndex()
    {
        return 1;
    }

    public static int GetBirthDateIndex()
    {
        return 2;
    }

    public static int GetCellphoneIndex()
    {
        return 3;
    }

    public static ContentValues GetContentValue(Person person)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CPF, person.GetCpf());
        contentValues.put(NAME, person.GetName());
        contentValues.put(BIRTH_DATE, person.GetBirthDate());
        contentValues.put(CELLPHONE, person.GetCellphone());
        return contentValues;
    }

    public static boolean Exists(SQLiteDatabase database, String cpf)
    {
        Cursor cursor = database.rawQuery(GetSelectQuery(cpf), null);
        return cursor.moveToFirst();
    }

    public static Person GetPersonFromCursor(Cursor cursor)
    {
        return new Person
        (
            cursor.getString(GetCpfIndex()),
            cursor.getString(GetNameIndex()),
            cursor.getString(GetCellphoneIndex()),
            cursor.getString(GetBirthDateIndex())
        );
    }

    public static Servant GetServantFromCursor(Cursor personCursor, Property property, String hiringDate)
    {
        return new Servant
        (
            personCursor.getString(GetCpfIndex()),
            personCursor.getString(GetNameIndex()),
            personCursor.getString(GetCellphoneIndex()),
            personCursor.getString(GetBirthDateIndex()),
            hiringDate,
            property
        );
    }
}
