package Test.UnitTest.CRUDProperty;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.CRUDProperty;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Property;

public class ReadPropertyTest 
{
    private static MyDatabase database = null;

    public static void main(String[] args)
    {
        InjectDatabase();
        TryRegisterProperty();
        // ReadProperties();
        ReadProperty();
    }

    private static void InjectDatabase()
    {
        database = new MyDatabase();
        DatabaseAccess.propertyOperationsInterface = database;
    }

    private static void TryRegisterProperty()
    {
        if
        (
            CRUDProperty.TryRegisterProperty
            (
                new Property
                (
                    "2211",
                    "Village dos Passaros",
                    "Espirito Santo",
                    "Rua",
                    0
                )
            )
        )
        {
            System.out.println("Success2");
        }
        if
        (
            CRUDProperty.TryRegisterProperty
            (
                new Property
                (
                    "2210",
                    "Village dos Animais",
                    "Espirito Santo",
                    "Rua",
                    0
                )
            )
        )
        {
            System.out.println("Success1");
        }
        if
        (
            CRUDProperty.TryRegisterProperty
            (
                new Property
                (
                    "2212",
                    "Village dos Bichos",
                    "Espirito Santo",
                    "Rua",
                    0
                )
            )
        )
        {
            System.out.println("Success3\n");
        }
    }

    private static void ReadProperties()
    {
        var properties = DatabaseAccess.propertyOperationsInterface.GetProperties();

        for(var property : properties)
        {
            if(property == null)
            {
                continue;
            }

            System.out.println(property);
        }
    }

    private static void ReadProperty()
    {
        var property = DatabaseAccess.propertyOperationsInterface.GetPropertyById("2210");
        if(property == null)
        {
            return;
        }

        System.out.println(property);
    }
}
