package Test.UnitTest;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.EmployeeType;
import Policy.Entity.Person;
import Policy.Entity.Property;
import Policy.Entity.Servant;
import Policy.Entity.Warehouse;
import Policy.Entity.WarehouseManager;

public class EmployeeCRUDUnitTest 
{
    private static MyDatabase database = null;

    public static void main(String[] args)
    {
        InjectDatabase();
        AddProperties();
        AddWarehouses();
        AddEmployees();
        // PrintEmployees();
        // PrintServants();
        // UpdateServant();
        // UpdateServantProperty();
        PrintEmployeeOfType(EmployeeType.WAREHOUSE_MANAGER);
        // UpdateWarehouseManagerWarehouse();
        RemoveEmployee();
        PrintEmployeeOfType(EmployeeType.WAREHOUSE_MANAGER);
    }

    private static void InjectDatabase()
    {
        database = new MyDatabase();
        DatabaseAccess.employeeOperationsInterface = database;
        DatabaseAccess.propertyOperationsInterface = database;
        DatabaseAccess.warehouseOperationsInterface = database;
    }

    private static void AddProperties()
    {
        DatabaseAccess.propertyOperationsInterface.TryRegisterProperty
        (
            new Property("1234", "Village dos Passaros", "ES", "Rua", 1234)
        );
        DatabaseAccess.propertyOperationsInterface.TryRegisterProperty
        (
            new Property("1235", "Village dos bosques", "ES", "Rua", 1234)
        );
        DatabaseAccess.propertyOperationsInterface.TryRegisterProperty
        (
            new Property("1236", "Village das flores", "ES", "Rua", 1234)
        );
        DatabaseAccess.propertyOperationsInterface.TryRegisterProperty
        (
            new Property("1237", "Village das casas", "ES", "Rua", 1234)
        );
    }

    private static void AddWarehouses()
    {
        DatabaseAccess.warehouseOperationsInterface.TryRegisterWarehouse
        (
            new Warehouse
            (
                "1111", 
                "ES", 
                "Rua dos Bobos", 
                25, 
                new Person
                (
                    "1234",
                    "Pedro",
                    "Cellphone",
                    "Today"
                )
            ), 
            "Some Day"
        );
        DatabaseAccess.warehouseOperationsInterface.TryRegisterWarehouse
        (
            new Warehouse
            (
                "2222", 
                "ES", 
                "Rua dos Bobos", 
                26, 
                new Person
                (
                    "1235",
                    "Pedro2",
                    "Cellphone",
                    "Today"
                )
            ), 
            "Some Day"
        );
        DatabaseAccess.warehouseOperationsInterface.TryRegisterWarehouse
        (
            new Warehouse
            (
                "3333", 
                "ES", 
                "Rua dos Bobos", 
                27, 
                new Person
                (
                    "1236",
                    "Pedro3",
                    "Cellphone",
                    "Today"
                )
            ), 
            "Some Day"
        );
        DatabaseAccess.warehouseOperationsInterface.TryRegisterWarehouse
        (
            new Warehouse
            (
                "4444", 
                "ES", 
                "Rua das Lamentacoes", 
                28, 
                new Person
                (
                    "1237",
                    "Pedro4",
                    "Cellphone",
                    "Today"
                )
            ), 
            "Some Day"
        );
    }

    private static void AddEmployees()
    {
        var property = DatabaseAccess.propertyOperationsInterface.GetPropertyById("1234");
        if(property != null)
        {
            DatabaseAccess.employeeOperationsInterface.TryRegisterServant
            (
                new Servant
                (
                    "1111", 
                    "Name", 
                    "11", 
                    "20062001", 
                    "Today", 
                    property
                ),

                "Today"
            );

            DatabaseAccess.employeeOperationsInterface.TryRegisterServant
            (
                new Servant
                (
                    "1111", 
                    "Name", 
                    "11", 
                    "20062001", 
                    "Today", 
                    property
                ),

                "Today"
            );
        }

        var warehouse1 = DatabaseAccess.warehouseOperationsInterface.GetWarehouse("1111");
        var warehouse2 = DatabaseAccess.warehouseOperationsInterface.GetWarehouse("2222");
        var warehouse3 = DatabaseAccess.warehouseOperationsInterface.GetWarehouse("3333");
        if(warehouse1 != null)
        {
            DatabaseAccess.employeeOperationsInterface.TryRegisterWarehouseManager
            (
                new WarehouseManager
                (
                    "11111", 
                    "Manager 1", 
                    "Cellphone", 
                    "Birth date", 
                    "Hiring date", 
                    warehouse1
                ), 
                
                "Today"
            );
        }
        if(warehouse2 != null)
        {
            DatabaseAccess.employeeOperationsInterface.TryRegisterWarehouseManager
            (
                new WarehouseManager
                (
                    "22222", 
                    "Manager 2", 
                    "Cellphone", 
                    "Birth date", 
                    "Hiring date", 
                    warehouse2
                ), 

                "Today"
            );
        }
        if(warehouse3 != null)
        {
            DatabaseAccess.employeeOperationsInterface.TryRegisterWarehouseManager
            (
                new WarehouseManager
                (
                    "33333", 
                    "Manager 3", 
                    "Cellphone", 
                    "Birth date", 
                    "Hiring date", 
                    warehouse3
                ), 

                "Today"
            );
        }
    }

    private static void PrintEmployees()
    {
        var employeeArray = DatabaseAccess.employeeOperationsInterface.GetEmployees();
        for(var element : employeeArray)
        {
            if(element == null)
            {
                continue;
            }

            System.out.println(element);
        }
    }

    private static void PrintServants()
    {
        var employeeArray = DatabaseAccess.employeeOperationsInterface.GetEmployees();
        for(var element : employeeArray)
        {
            if(element.GetEmployeeType() == EmployeeType.WAREHOUSE_MANAGER)
            {
                continue;
            }

            System.out.println(element);
        }
    }

    private static void UpdateServant()
    {
        var servant = (Servant) DatabaseAccess.employeeOperationsInterface.GetEmployee("1111");
        if(servant == null)
        {
            return;
        }

        servant.SetCellphone("27992250622");
        servant.SetProperty(null);

        DatabaseAccess.employeeOperationsInterface.TryUpdateServant(servant);
    }

    private static void UpdateServantProperty()
    {
        var property = DatabaseAccess.propertyOperationsInterface.GetPropertyById("1235");
        if(property == null)
        {
            return;
        }

        DatabaseAccess.employeeOperationsInterface.TryUpdateServantProperty
        (
            "1111", 
            property, 
            "Today"
        );
    }

    private static void UpdateWarehouseManager()
    {
        var warehouseManager = (WarehouseManager) DatabaseAccess.employeeOperationsInterface.GetEmployee("22222");
        if(warehouseManager == null)
        {
            return;
        }

        warehouseManager.SetCellphone("27992250622");
        // warehouseManager.SetWarehouse
        // (
        //     DatabaseAccess.warehouseOperationsInterface.GetWarehouse("2222")
        // );

        DatabaseAccess.employeeOperationsInterface.TryUpdateWarehouseManager(warehouseManager);
    }

    private static void UpdateWarehouseManagerWarehouse()
    {
        var newWarehouse = DatabaseAccess.warehouseOperationsInterface.GetWarehouse("1111");
        if(newWarehouse == null)
        {
            return;
        }

        DatabaseAccess.employeeOperationsInterface.TryUpdateWarehouseManagerWarehouse
        (
            "11111",
            newWarehouse,
            "Today"
        );
    }

    private static void PrintEmployeeOfType(EmployeeType employeeType)
    {
        var employeeArray = DatabaseAccess.employeeOperationsInterface.GetEmployees();
        for(var element : employeeArray)
        {
            if(element.GetEmployeeType() != employeeType)
            {
                continue;
            }

            System.out.println(element);
        }
    }

    private static void RemoveEmployee()
    {
        DatabaseAccess.employeeOperationsInterface.TryRemoveEmployee("11111");
    }
}
