package Frameworks.Database.SQLite;

public class CoffeeBagTableQueryHelper
{
    public static final String COFFEE_BAG_TABLE = "coffeeBag";
    public static final String ID = "id";
    public static final String BATCH_ID = "bachId";
    public static final String WAREHOUSE_ID = "warehouse_Id";

    public static String GetCreateCommand()
    {
        return "CREATE TABLE "+COFFEE_BAG_TABLE+"("
                +ID+" TEXT,"
                +BATCH_ID+" TEXT,"
                +"FOREIGN KEY("+WAREHOUSE_ID+") REFERENCES("+WarehouseTableQueryHelper.WAREHOUSE_TABLE+"("+WarehouseTableQueryHelper.ID+")),"
                +"Primary KEY("+ID+", "+BATCH_ID+")"
                +")";
    }
}
