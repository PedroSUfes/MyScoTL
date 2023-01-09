package Test.UnitTest.CRUDEmployee;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.CRUDEmployee;
import Policy.BusinessRules.CRUDProperty;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Property;
import Policy.Entity.Servant;

public class UpdateServantTest 
{
    private static MyDatabase database = new MyDatabase();
    
    private static Property property1 = null;
    private static Property property2 = null;

    private static Servant servant1 = null;
    private static Servant servant2 = null;

    public static void main(String[] args)
    {
        DatabaseAccess.employeeOperationsInterface = database;
        DatabaseAccess.propertyOperationsInterface = database;

        RegisterProperties();
        RegisterServants();
        UpdateServants();
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
        servant1 = new Servant
        (
            "1111", 
            "Servente 1", 
            "0800", 
            "Today", 
            "Today", 
            property1
        );
        servant2 = new Servant
        (
            "2222", 
            "Servente 2", 
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

    private static void UpdateServants()
    {
        servant1.SetName("Servente Primeiro");
        servant1.SetCellphone("0801");
        servant1.SetProperty(property2);
        if(CRUDEmployee.TryUpdateServant(servant1, "Today"))
        {
            System.out.println("Success in update servant 1");
        }

        // servant2.SetName("Servente Segundo");
        // servant2.SetCellphone("0802");
        // if(CRUDEmployee.TryUpdateServant(servant2, "Today"))
        // {
        //     System.out.println("Success in update servant 2");
        // }
    }
}
