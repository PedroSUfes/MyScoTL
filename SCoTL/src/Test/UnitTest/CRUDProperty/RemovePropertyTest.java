package Test.UnitTest.CRUDProperty;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.CRUDProperty;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Property;

public class RemovePropertyTest 
{
    private static MyDatabase database = new MyDatabase();

    public static void main(String[] args)
    {
        DatabaseAccess.propertyOperationsInterface = database;

        TryRegisterProperty();
        TryRemoveProperty();
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
                    "2212",
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

    private static void TryRemoveProperty()
    {
        if(DatabaseAccess.propertyOperationsInterface.TryRemoveProperty("2212"))
        {
            System.out.println("Success 1");
        }
        if(DatabaseAccess.propertyOperationsInterface.TryRemoveProperty("2212"))
        {
            System.out.println("Success 2");
        }
    }
}
