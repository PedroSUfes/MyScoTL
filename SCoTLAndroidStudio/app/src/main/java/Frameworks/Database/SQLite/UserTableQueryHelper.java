package Frameworks.Database.SQLite;

import android.content.ContentValues;

public class UserTableQueryHelper
{
    public static final String USER_TABLE = "user";
    public static final String LOGIN = "login";
    public static final String CPF_PERSON = "cpfPerson";
    public static final String PASSWORD = "password";
    public static final String USER_TYPE = "userType";

    public static String GetCreateCommand()
    {
        return "CREATE TABLE "+USER_TABLE+" ("
                +LOGIN +" TEXT PRIMARY KEY,"
                +CPF_PERSON+" TEXT,"
                +PASSWORD + " TEXT,"
                +USER_TYPE + " INTEGER,"
                +"FOREIGN KEY("+CPF_PERSON+") REFERENCES "+PersonTableQueryHelper.PERSON_TABLE+"("+PersonTableQueryHelper.CPF +")"
                +")";
    }

    public static String GetSelectAllQuery()
    {
        return "SELECT * FROM "+USER_TABLE;
    }

    public static String GetSelectQuery(String login, String password)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GetSelectAllQuery());
        stringBuilder.append(" WHERE "+LOGIN+"='"+login+"' AND "+PASSWORD+"='"+password+"'");
        return  stringBuilder.toString();
    }

    public static ContentValues GetContentValues(String login, String password)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOGIN, login);
        contentValues.put(PASSWORD, password);
        return contentValues;
    }

    public static int GetUserTypeIndex()
    {
        return 3;
    }
}
