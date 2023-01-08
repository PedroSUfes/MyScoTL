package Test.UnitTest.CRUDProperty;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.CRUDProperty;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Property;

public class RegisterPropertyTest 
{
    private static MyDatabase database = null;

    public static void main(String[] args)
    {
        InjectDatabase();
        TryRegisterProperty();
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
}
