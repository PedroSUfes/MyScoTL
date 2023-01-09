package Test.UnitTest.CRUDEmployee;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.CRUDEmployee;
import Policy.BusinessRules.CRUDWarehouse;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Person;
import Policy.Entity.Warehouse;
import Policy.Entity.WarehouseManager;

public class ReadWarehouseManagerTest 
{
    private static MyDatabase database = new MyDatabase();

    private static Warehouse warehouse1 = null;

    public static void main(String[] args)
    {
        DatabaseAccess.employeeOperationsInterface = database;
        DatabaseAccess.warehouseOperationsInterface = database;

        RegisterWarehouses();
        RegisterWarehouseManagers();
        ReadWarehouseManagers();
        // ReadWarehouseManager();
    }

    private static void RegisterWarehouses()
    {
        var owner1 = new Person
        (
            "2111",
            "Dono 1",
            "0800",
            "Today"
        );

        warehouse1 = new Warehouse
        (
            "1111", 
            "Espirito Santo", 
            "Rua das Ruas", 
            12, 
            owner1
        );

        if(CRUDWarehouse.TryRegisterWarehouse(warehouse1, "Today"))
        {
            System.out.println("Success in register warehouse 1");
        }
    }

    private static void RegisterWarehouseManagers()
    {
        var warehouseManager1 = new WarehouseManager
        (
            "1111", 
            "Gerente 1", 
            "0800", 
            "Today", 
            "Today", 
            warehouse1
        );
        var warehouseManager2 = new WarehouseManager
        (
            "2222", 
            "Gerente 2", 
            "0800", 
            "Today", 
            "Today", 
            warehouse1
        );

        if(CRUDEmployee.TryRegisterWarehouseManager(warehouseManager1, "Today"))
        {
            System.out.println("Success in register warehouse manager 1");
        }
        if(CRUDEmployee.TryRegisterWarehouseManager(warehouseManager2, "Today"))
        {
            System.out.println("Success in register warehouse manager 2");
        }
    }

    private static void ReadWarehouseManagers()
    {
        var warehouseManagers = CRUDEmployee.GetWarehouseManagers();
        if(warehouseManagers == null)
        {
            return;
        }

        for(var warehouseManager : warehouseManagers)
        {
            if(warehouseManager == null)
            {
                continue;
            }

            System.out.println(warehouseManager);
        }
    }
    
    private static void ReadWarehouseManager()
    {

    }
}
