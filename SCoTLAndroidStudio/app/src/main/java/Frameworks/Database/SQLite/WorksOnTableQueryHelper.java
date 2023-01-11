package Frameworks.Database.SQLite;

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
                +"FOREIGN KEY("+PROPERTY_ID+") REFERENCES("+PropertyTableQueryHelper.PROPERTY_TABLE+"("+PropertyTableQueryHelper.ID+")),"
                +END_DATE + " TEXT,"
                +"PRIMARY KEY("+PERSON_CPF+","+BEGIN_DATE+")"
                +")";
    }
}
