package Frameworks.Database.SQLite;

public class ManageWarehouseTableQueryHelper
{
    public static final String MANAGE_WAREHOUSE_TABLE = "manageWarehouse";
    public static final String WAREHOUSE_ID = "warehouseId";
    public static final String BEGIN_DATE = "beginDate";
    public static final String END_DATE = "endDate";
    public static final String PERSON_CPF = "personCpf";

    public static String GetCreateCommand()
    {
        return "CREATE TABLE "+MANAGE_WAREHOUSE_TABLE+"("
                +WAREHOUSE_ID+" TEXT,"
                +BEGIN_DATE+" TEXT,"
                +PERSON_CPF+" TEXT,"
                +END_DATE+" TEXT,"
                +"PRIMARY KEY("+WAREHOUSE_ID+","+PERSON_CPF+","+BEGIN_DATE+")"
                +")";
    }
}
