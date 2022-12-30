package Test.UnitTest;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Person;
import Policy.Entity.Warehouse;

public class WarehouseCRUDUnitTest
{
    private static MyDatabase database = null;

    public static void main(String[] args)
    {
        InjectDatabase();
        PopulateDatabase();
        // PrintAllWarehouses();
        // UpdateWarehouse();
        // RemoveWarehouse("1234");
        // PrintAllWarehouses();
        // RemoveWarehouse("1235");
        // System.out.println("\n\n");
        // PrintAllWarehouses();
        // PrintWarehousesInState("SP");
        PrintWarehousesInStateAndStreet("ES", "Rua dos Bobos");
    }    

    private static void InjectDatabase()
    {
        database = new MyDatabase();
        DatabaseAccess.warehouseOperationsInterface = database;
    }

    private static void PopulateDatabase()
    {
        DatabaseAccess.warehouseOperationsInterface.TryRegisterWarehouse
        (
            new Warehouse
            (
                "1234", 
                "ES", 
                "Rua dos Bobos", 
                7, 
                new Person("1234", "Pedro", "27992250622", "20/06/2001")
            ),
            "Today"
        );

        DatabaseAccess.warehouseOperationsInterface.TryRegisterWarehouse
        (
            new Warehouse
            (
                "1235", 
                "SP", 
                "Rua dos Bobos", 
                7, 
                new Person("1234", "Pedro", "27992250622", "20/06/2001")
            ),
            "Today"
        );

        DatabaseAccess.warehouseOperationsInterface.TryRegisterWarehouse
        (
            new Warehouse
            (
                "1236", 
                "SP", 
                "Rua dos Bobos", 
                7, 
                new Person("1234", "Pedro", "27992250622", "20/06/2001")
            ),
            "Today"
        );
    }

    private static void PrintAllWarehouses()
    {
        var warehouseArray = DatabaseAccess.warehouseOperationsInterface.GetWarehouses();
        if(warehouseArray.length == 0)
        {
            System.out.println("No warehouses in database");
            return;
        }
        for(var element : warehouseArray)
        {
            System.out.println(element);
        }
    }

    private static void UpdateWarehouse()
    {
        var toUpdate = DatabaseAccess.warehouseOperationsInterface.GetWarehouse("1234");
        toUpdate.SetStateName("MG");
        toUpdate.SetStreetName("Rua das Flores");
        toUpdate.SetOwner
        (
            new Person
            (
                "1235",
                "Pedro",
                "2222",
                "Some Day"
            )
        );

        DatabaseAccess.warehouseOperationsInterface.TryUpdateWarehouse(toUpdate, "Tomorrow");
    }

    private static void RemoveWarehouse(String id)
    {
        DatabaseAccess.warehouseOperationsInterface.TryRemoveWarehouse(id);
    }

    private static void PrintWarehousesInState(String stateName)
    {
        var searchResult = DatabaseAccess.warehouseOperationsInterface.GetWarehouses(stateName);
        if(searchResult.length == 0)
        {
            System.out.println("No warehouses in state "+stateName);
            return;
        }

        for(var element : searchResult)
        {
            System.out.println(element);
        }
    }

    private static void PrintWarehousesInStateAndStreet(String stateName, String streetName)
    {
        var searchResult = DatabaseAccess.warehouseOperationsInterface.GetWarehouses(stateName, streetName);
        if(searchResult.length == 0)
        {
            System.out.println("No warehouses in state "+stateName+" and street "+streetName);
            return;
        }

        for(var element : searchResult)
        {
            System.out.println(element);
        }
    }
}
