package Test.UnitTest;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Property;

public class PropertyCRUDUnitTest 
{
    private static MyDatabase database = null;

    public static void main(String[] args)
    {
        InjectDatabase();
        // TestPropertyWithSameIdError();
        ValidDatabasePopulation();
        // GetPropertiesTest();
        // GetPropertyById("1234");
        // TestUpdate(new Property("1234", "Village das aves", "ES", "Rua das Ruas", 1234));
        TestRemove("1235");
        TestRemove("1236");
        TestRemove("1240");
        GetPropertiesTest();
    }    

    private static void InjectDatabase()
    {
        database = new MyDatabase();
        DatabaseAccess.propertyOperationsInterface = database;
    }

    private static void TestPropertyWithSameIdError()
    {
        DatabaseAccess.propertyOperationsInterface.TryRegisterProperty
        (
            new Property("1234", "Village dos Passaros", "ES", "Rua", 1234)
        );
        DatabaseAccess.propertyOperationsInterface.TryRegisterProperty
        (
            new Property("1234", "Village dos Passaros", "ES", "Rua", 1234)
        );
    }

    private static void ValidDatabasePopulation()
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

    private static void GetPropertiesTest()
    {
        var properties = DatabaseAccess.propertyOperationsInterface.GetProperties();
        for(var element : properties)
        {
            System.out.println(element);
        }
    }

    private static void GetPropertyById(String id)
    {
        var property = DatabaseAccess.propertyOperationsInterface.GetPropertyById(id);
        if(property == null)
        {
            return;
        }

        System.out.println("Retrieved:\n"+property);
    }

    private static void TestUpdate(Property property)
    {
        if(!DatabaseAccess.propertyOperationsInterface.TryUpdateProperty(property))
        {
            return;
        }

        System.out.println("Updated: \n"+DatabaseAccess.propertyOperationsInterface.GetPropertyById(property.GetId()));
    }

    private static void TestRemove(String id)
    {
        if(!DatabaseAccess.propertyOperationsInterface.TryRemoveProperty(id))
        {
            return;
        }
    }
}
