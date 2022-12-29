package Policy.BusinessRules;

import Policy.Entity.Warehouse;

public interface WarehouseOperationsInterface 
{
    public Warehouse[] GetWarehouses();
    public Warehouse[] GetWarehouses(String stateName);
    public Warehouse[] GetWarehouses(String stateName, String streetName);
    public Warehouse[] GetWarehousesByOwnerCpf(String ownerCpf);
    public Warehouse GetWarehouse(String id);
    public Warehouse GetWarehouse(String stateName, String streetName, int number);
    public Boolean TryRegisterWarehouse(Warehouse warehouse);
    public Boolean TryUpdateWarehouse(Warehouse warehouse);
    public Boolean TryRemoveWarehouse(String id);
}
