package Test.UnitTest;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Property;
import Policy.Entity.Servant;

public class EmployeeCRUDUnitTest 
{
    private static MyDatabase database = null;

    public static void main(String[] args)
    {
        InjectDatabase();
        AddProperties();
        AddEmployees();
        PrintEmployees();
    }

    private static void InjectDatabase()
    {
        database = new MyDatabase();
        DatabaseAccess.employeeOperationsInterface = database;
        DatabaseAccess.propertyOperationsInterface = database;
    }

    private static void AddProperties()
    {
        DatabaseAccess.propertyOperationsInterface.TryRegisterProperty
        (
            new Property("1234", "Village dos Passaros", "ES", "Rua", 1234)
        );
        DatabaseAccess.propertyOperationsInterface.TryRegisterProperty
        (
            new Property("1235", "Village dos Passaros", "ES", "Rua", 1234)
        );
        DatabaseAccess.propertyOperationsInterface.TryRegisterProperty
        (
            new Property("1236", "Village dos Passaros", "ES", "Rua", 1234)
        );
        DatabaseAccess.propertyOperationsInterface.TryRegisterProperty
        (
            new Property("1237", "Village dos Passaros", "ES", "Rua", 1234)
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
}
