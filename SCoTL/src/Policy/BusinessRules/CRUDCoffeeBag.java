package Policy.BusinessRules;

import Policy.Entity.CoffeeBag;

public class CRUDCoffeeBag 
{
    public static CoffeeBag[] GetCoffeeBags()
    {
        return DatabaseAccess.coffeeBagOperationsInterface.GetCoffeeBags();
    }

    public static CoffeeBag[] GetCoffeeBags(String batchId)
    {
        return DatabaseAccess.coffeeBagOperationsInterface.GetCoffeeBags(batchId);
    }

    public static CoffeeBag GetCoffeeBag(String batchId, String coffeeBagId)
    {
        return DatabaseAccess.coffeeBagOperationsInterface.GetCoffeeBag(batchId, coffeeBagId);
    }

    public static Boolean TryRegisterCoffeeBag(CoffeeBag coffeeBag)
    {
        return DatabaseAccess.coffeeBagOperationsInterface.TryRegisterCoffeeBag(coffeeBag);
    }

    public static Boolean TryRemoveCoffeeBag(String batchId, String coffeeBagId)
    {
        return DatabaseAccess.coffeeBagOperationsInterface.TryRemoveCoffeeBag(batchId, coffeeBagId);
    }
}
