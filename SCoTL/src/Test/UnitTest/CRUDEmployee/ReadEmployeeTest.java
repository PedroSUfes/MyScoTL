package Test.UnitTest.CRUDEmployee;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.CRUDEmployee;
import Policy.BusinessRules.CRUDProperty;
import Policy.BusinessRules.CRUDWarehouse;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Person;
import Policy.Entity.Property;
import Policy.Entity.Servant;
import Policy.Entity.Warehouse;
import Policy.Entity.WarehouseManager;

public class ReadEmployeeTest 
{
    private static MyDatabase database = new MyDatabase();
    
    private static Property property1 = null;
    private static Property property2 = null;
    
    private static Warehouse warehouse1 = null;

    public static void main(String[] args)
    {
        DatabaseAccess.employeeOperationsInterface = database;
        DatabaseAccess.propertyOperationsInterface = database;
        DatabaseAccess.warehouseOperationsInterface = database;

        RegisterProperties();
        RegisterServants();
        RegisterWarehouses();
        RegisterWarehouseManagers();
        // ReadEmployeesTest();
        ReadEmployee();
    }

    private static void RegisterProperties()
    {
        property1 = new Property
        (
            "1111", 
            "Propriedade 1", 
            "Espirito Santo", 
            "Rua da Propriedade 1", 
            12
        );
        property2 = new Property
        (
            "2222", 
            "Propriedade 2", 
            "Espirito Santo", 
            "Rua da Propriedade 2", 
            12
        );

        if(CRUDProperty.TryRegisterProperty(property1))
        {
            System.out.println("Success property 1");
        }
        if(CRUDProperty.TryRegisterProperty(property2))
        {
            System.out.println("Success property 2");
        }          
    }

    private static void RegisterServants()
    {
        var servant1 = new Servant
        (
            "1111", 
            "Servente 1", 
            "0800", 
            "Today", 
            "Today", 
            property1
        );
        var servant2 = new Servant
        (
            "1111", 
            "Servente 1", 
            "0800", 
            "Today", 
            "Today", 
            property2
        );
     
        if(CRUDEmployee.TryRegisterServant(servant1, "Today"))
        {
            System.out.println("Success in register servant 1");
        }
        // if(CRUDEmployee.TryRegisterServant(servant2, "Today"))
        // {
        //     System.out.println("Success in register servant 1");
        // }
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

    private static void ReadEmployeesTest()
    {
        var employees = CRUDEmployee.GetEmployees();
        if(employees == null)
        {
            return;
        }

        for(var employee : employees)
        {
            if(employee == null)
            {
                continue;
            }

            System.out.println(employee);
        }
    }

    private static void ReadEmployee()
    {
        var employee = CRUDEmployee.GetEmployee("111");
        if(employee == null)
        {
            return;
        }

        System.out.println(employee);
    }
}
