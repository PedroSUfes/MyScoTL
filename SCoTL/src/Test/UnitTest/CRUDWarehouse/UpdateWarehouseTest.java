package Test.UnitTest.CRUDWarehouse;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Person;
import Policy.Entity.Warehouse;

public class UpdateWarehouseTest 
{
    private static MyDatabase database = new MyDatabase();

    public static void main(String[] args)
    {
        DatabaseAccess.warehouseOperationsInterface = database;
        DatabaseAccess.employeeOperationsInterface = database;

        RegisterWarehouses();
        TryUpdateWarehouse(); 
    }

    private static void RegisterWarehouses()
    {
        var person1 = new Person
        (
            "1111",
            "Fulano",
            "0800",
            "Ontem"
        );
        var warehouse1 = new Warehouse
        (
            "1111",
            "Espirito Santo", 
            "Rua das Avalanches", 
            23, 
            person1
        );

        if(DatabaseAccess.warehouseOperationsInterface.TryRegisterWarehouse(warehouse1, "Today"))
        {
            System.out.println("Success 1");
        }
        else
        {
            System.out.println("Fail 1");
        }

        var warehouse2 = new Warehouse
        (
            "1112",
            "Espirito Santo", 
            "Rua das Avalanches", 
            23, 
            person1
        );
        if(DatabaseAccess.warehouseOperationsInterface.TryRegisterWarehouse(warehouse2, "Today"))
        {
            System.out.println("Success 2");
        }
        else
        {
            System.out.println("Fail 2");
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
                "Fulano2",
                "0800",
                "Amanha"
            )
        );
        if(DatabaseAccess.warehouseOperationsInterface.TryRegisterWarehouse(warehouse3, "Today"))
        {
            System.out.println("Success 3");
        }
        else
        {
            System.out.println("Fail 3");
        }
    }

    private static void TryUpdateWarehouse()
    {
        var warehouse1 = DatabaseAccess.warehouseOperationsInterface.GetWarehouse("1111");

        if(warehouse1 != null)
        {
            warehouse1.SetNumber(1234);
            warehouse1.SetOwner
            (
                new Person
                (
                    "1112",
                    "Fulano2",
                    "0800",
                    "Amanha"
                )
            );
            var updateResult = DatabaseAccess.warehouseOperationsInterface.TryUpdateWarehouse
            (
                warehouse1, 
                "Today"
            );

            if(updateResult)
            {
                System.out.println("Success 1");
            }
        }
    }
}
