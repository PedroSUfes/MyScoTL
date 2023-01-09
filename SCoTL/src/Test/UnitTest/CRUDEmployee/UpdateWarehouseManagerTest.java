package Test.UnitTest.CRUDEmployee;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.CRUDEmployee;
import Policy.BusinessRules.CRUDWarehouse;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Person;
import Policy.Entity.Warehouse;
import Policy.Entity.WarehouseManager;

public class UpdateWarehouseManagerTest 
{
    private static MyDatabase database = new MyDatabase();

    private static Warehouse warehouse1 = null;
    private static Warehouse warehouse2 = null;
    private static Warehouse warehouse3 = null;

    private static WarehouseManager warehouseManager1 = null;
    private static WarehouseManager warehouseManager2 = null;

    public static void main(String[] args)
    {
        DatabaseAccess.warehouseOperationsInterface = database;
        DatabaseAccess.employeeOperationsInterface = database;

        RegisterWarehouses();
        RegisterWarehouseManagers();
        UpdateWarehouseManagers();
    }

    private static void RegisterWarehouses()
    {
        var owner1 = new Person
        (
            "1111",
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

        warehouse2 = new Warehouse
        (
            "2222", 
            "Espirito Santo", 
            "Rua das Avenidas", 
            14, 
            owner1
        );

        warehouse3 = new Warehouse
        (
            "3333", 
            "Espirito Santo", 
            "Rua das Avenidas", 
            14, 
            owner1
        );

        if(CRUDWarehouse.TryRegisterWarehouse(warehouse1, "Today"))
        {
            System.out.println("Success in register warehouse 1");
        }
        if(CRUDWarehouse.TryRegisterWarehouse(warehouse2, "Today"))
        {
            System.out.println("Success in register warehouse 2");
        }
        if(CRUDWarehouse.TryRegisterWarehouse(warehouse3, "Today"))
        {
            System.out.println("Success in register warehouse 3");
        }
    }

    private static void RegisterWarehouseManagers()
    {
        warehouseManager1 = new WarehouseManager
        (
            "1111", 
            "Gerente 1", 
            "0800", 
            "Today", 
            "Today", 
            warehouse1
        );
        warehouseManager2 = new WarehouseManager
        (
            "2222", 
            "Gerente 2", 
            "0800", 
            "Today", 
            "Today", 
            warehouse2
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

    private static void UpdateWarehouseManagers()
    {
        warehouseManager1.SetCellphone("0801");
        warehouseManager1.SetName("Gerente Primeiro");
        warehouseManager1.SetWarehouse(warehouse3);
        if(CRUDEmployee.TryUpdateWarehouseManager(warehouseManager1, "Today"))
        {
            System.out.println("Success update warehouse manager 1");
        }
    }
}
