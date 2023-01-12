package Frameworks.Database.SQLite;

public class BuyCoffeeBagTableQueryHelper
{
    public static final String BUY_COFFEE_BAG_TABLE = "buyCoffeeBagTable";
    public static final String BATCH_ID = "batchId";
    public static final String COFFEE_BAG_ID = "coffeeBagId";
    public static final String BUYER_CPF = "buyerCpf";

    public static String GetCreateCommand()
    {
        return "CREATE TABLE "+BUY_COFFEE_BAG_TABLE+"("
                +BATCH_ID+" TEXT,"
                +COFFEE_BAG_ID+" TEXT,"
                +BUYER_CPF+" TEXT,"
                +"Primary KEY("+BATCH_ID+", "+COFFEE_BAG_ID+"),"
                +"FOREIGN KEY("+BUYER_CPF+") REFERENCES "+PersonTableQueryHelper.PERSON_TABLE+"("+PersonTableQueryHelper.CPF +")"
                +")";
    }
}
