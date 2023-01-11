package Frameworks.Database.SQLite;

public class IsWarehouseOwnerTableQueryHelper
{
    public static final String IS_WAREHOUSE_OWNER_TABLE = "isWarehouseOwner";
    public static final String WAREHOUSE_ID = "warehouseId";
    public static final String OWNER_CPF = "ownerCpf";
    public static final String BEGIN_DATE = "beginDate";
    public static final String END_DATE = "endDate";

    public static String GetCreateCommand()
    {
        return "CREATE TABLE "+IS_WAREHOUSE_OWNER_TABLE+"("
                +WAREHOUSE_ID+" TEXT,"
                +OWNER_CPF+" TEXT,"
                +BEGIN_DATE+" TEXT,"
                +END_DATE+" TEXT,"
                +"PRIMARY KEY("+WAREHOUSE_ID+","+OWNER_CPF+","+BEGIN_DATE+")"
                +")";
    }
}
