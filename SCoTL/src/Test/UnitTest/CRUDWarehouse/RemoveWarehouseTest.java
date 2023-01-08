package Test.UnitTest.CRUDWarehouse;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Person;
import Policy.Entity.Warehouse;

public class RemoveWarehouseTest 
{
    private static MyDatabase database = new MyDatabase();

    public static void main(String[] args)
    {
        DatabaseAccess.warehouseOperationsInterface = database;
        
        RegisterWarehouses();
        TryRemoveWarehouse();
    }

    private static void RegisterWarehouses()
    {
        var warehouse1 = new Warehouse
        (
            "1111",
            "Espirito Santo", 
            "Rua das Tempestades", 
            23, 
            new Person
            (
                "1111",
                "Fulano",
                "0800",
                "Ontem"
            )
        );

        if(DatabaseAccess.warehouseOperationsInterface.TryRegisterWarehouse(warehouse1, "Today"))
        {
            System.out.println("Success 1");
        }

        var warehouse2 = new Warehouse
        (
            "1112",
            "Espirito Santo", 
            "Rua das Avalanches", 
            23, 
            new Person
            (
                "1112",
                "Fulano",
                "0800",
                "Ontem"
            )
        );
        if(DatabaseAccess.warehouseOperationsInterface.TryRegisterWarehouse(warehouse2, "Today"))
        {
            System.out.println("Success 2");
        }
        var warehouse3 = new Warehouse
        (
            "1113",
            "Espirito Santo", 
            "Rua das Avalanches", 
            23, 
            new Person
            (
                "1112",
                "Fulano",
                "0800",
                "Ontem"
            )
        );
        if(DatabaseAccess.warehouseOperationsInterface.TryRegisterWarehouse(warehouse3, "Today"))
        {
            System.out.println("Success 3");
        }
    }

    private static void TryRemoveWarehouse()
    {
        if(DatabaseAccess.warehouseOperationsInterface.TryRemoveWarehouse("1111"))
        {
            System.out.println("Success 1");
        }
        if(DatabaseAccess.warehouseOperationsInterface.TryRemoveWarehouse("1112"))
        {
            System.out.println("Success 2");
        }
    }
}
