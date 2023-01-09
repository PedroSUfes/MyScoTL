package Test.UnitTest.CRUDCoffeeBag;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.CRUDBatch;
import Policy.BusinessRules.CRUDCoffeeBag;
import Policy.BusinessRules.CRUDWarehouse;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;
import Policy.Entity.CoffeeBag;
import Policy.Entity.Person;
import Policy.Entity.Warehouse;

public class ReadCoffeeBagTest 
{
    private static MyDatabase database = new MyDatabase();
    
    public static void main(String[] args)
    {
        DatabaseAccess.warehouseOperationsInterface = database;
        DatabaseAccess.batchOperationsInterface = database;
        DatabaseAccess.coffeeBagOperationsInterface = database;

        RegisterCoffeeBags();
        // ReadCoffeeBags();
        ReadCoffeeBagsInBatch();
    }

    private static void RegisterCoffeeBags()
    {
        var batch1 = new Batch
        (
            "1111",
            "Today"
        );
        var batch2 = new Batch
        (
            "2222",
            "Tomorrow"
        );
        var batch3 = new Batch
        (
            "3333",
            "Tomorrow"
        );

        var warehouse = new Warehouse
        (
            "1111", 
            "Espirito Santo", 
            "Rua das Avenidas", 
            12, 
            new Person
            (
                "1111",
                "Pessoa 1",
                "0800",
                "Today"
            )
        );

        CRUDBatch.TryRegisterBatch(batch1);
        CRUDBatch.TryRegisterBatch(batch2);
        CRUDBatch.TryRegisterBatch(batch3);

        CRUDWarehouse.TryRegisterWarehouse(warehouse, "Today");

        var coffeeBag1 = new CoffeeBag
        (
            "1111", 
            batch1, 
            warehouse, 
            "Today"
        );

        var coffeeBag2 = new CoffeeBag
        (
            "1111", 
            batch2, 
            warehouse, 
            "Today"
        );

        if(CRUDCoffeeBag.TryRegisterCoffeeBag(coffeeBag1))
        {
            System.out.println("Success 1");
        }
        if(CRUDCoffeeBag.TryRegisterCoffeeBag(coffeeBag2))
        {
            System.out.println("Success 2");
        }
    }

    private static void ReadCoffeeBags()
    {
        var coffeeBags = CRUDCoffeeBag.GetCoffeeBags();
        if(coffeeBags == null)
        {
            System.out.println("No coffee bags");
            return;
        }
        for(var coffeeBag : coffeeBags)
        {
            if(coffeeBag == null)
            {
                continue;
            }

            System.out.println(coffeeBag);
        }
    }

    private static void ReadCoffeeBagsInBatch()
    {
        var coffeeBags = CRUDCoffeeBag.GetCoffeeBags("3333");
        if(coffeeBags == null)
        {
            return;
        }

        for(var coffeeBag : coffeeBags)
        {
            if(coffeeBag == null)
            {
                continue;
            }

            System.out.println(coffeeBag);
        }
    }
}
