package Test.UnitTest.CRUDProperty;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.CRUDProperty;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Property;

public class UpdatePropertyTest 
{
    private static MyDatabase database = null;

    public static void main(String[] args)
    {
        InjectDatabase();
        TryRegisterProperty();
        TryUpdateProperty();
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
                    "Espirito Santos",
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
    }

    private static void TryUpdateProperty()
    {
        var property = DatabaseAccess.propertyOperationsInterface.GetPropertyById("2211");        
        if(property == null)
        {
            return;
        }

        property.SetPropertyName("New name");
        property.SetNumber(12);

        if(DatabaseAccess.propertyOperationsInterface.TryUpdateProperty(property))
        {
            System.out.println("Success to update");
        }
    }
}
