package Policy.BusinessRules;

import Policy.Entity.CoffeeBag;

public interface CoffeeBagOperationsInterface 
{
    public CoffeeBag[] GetCoffeeBags();
    public CoffeeBag[] GetCoffeeBags(String batchId);
    public CoffeeBag GetCoffeeBag(String batchId, String coffeeBagId);
    public Boolean TryRegisterCoffeeBag(CoffeeBag coffeeBag);
    public Boolean TryRemoveBag(String batchId, String coffeeBagId);
}
