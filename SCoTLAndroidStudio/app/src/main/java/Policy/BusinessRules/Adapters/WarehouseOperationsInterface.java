package Policy.BusinessRules.Adapters;

import Policy.Entity.Warehouse;

public interface WarehouseOperationsInterface 
{
    public Warehouse[] GetWarehouses(boolean withPastRegister);
    public Warehouse[] GetWarehouses(String stateName, boolean withPastRegister);
    public Warehouse[] GetWarehouses(String stateName, String cityName, boolean withPastRegister);
    public Warehouse[] GetWarehouses(String stateName, String cityName, String streetName, boolean withPastRegister);
    public Warehouse[] GetWarehouses(String stateName, String cityName, String streetName, int residentialNumber, boolean withPastRegister);
    public Warehouse[] GetWarehousesByOwnerCpf(String ownerCpf, boolean withPastRegister);
    public Warehouse[] GetWarehouse(String id, boolean withPastRegister);
    public Boolean TryRegisterWarehouse(Warehouse warehouse);
    public Boolean TryUpdateWarehouse(Warehouse warehouse);
    public Boolean TryRemoveWarehouse(String id);
}
