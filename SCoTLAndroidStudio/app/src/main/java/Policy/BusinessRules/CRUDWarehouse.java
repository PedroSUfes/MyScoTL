package Policy.BusinessRules;

import Policy.Entity.Warehouse;

public class CRUDWarehouse 
{
    public static Warehouse[] GetWarehouses(boolean withPastRegister)
    {
        return DatabaseAccess.warehouseOperationsInterface.GetWarehouses(withPastRegister);
    }

    public static Warehouse[] GetWarehouses(String stateName, boolean withPastRegister)
    {
        return DatabaseAccess.warehouseOperationsInterface.GetWarehouses(stateName, withPastRegister);
    }

    public static Warehouse[] GetWarehouses(String stateName, String cityName, boolean withPastRegister)
    {
        return DatabaseAccess.warehouseOperationsInterface.GetWarehouses(stateName, cityName, withPastRegister);
    }

    public static Warehouse[] GetWarehousesByOwnerCpf(String ownerCpf, boolean withPastRegister)
    {
        return DatabaseAccess.warehouseOperationsInterface.GetWarehousesByOwnerCpf(ownerCpf, withPastRegister);
    }

    public static Warehouse[] GetWarehouse(String id, boolean withPastRegister)
    {
        return DatabaseAccess.warehouseOperationsInterface.GetWarehouse(id, withPastRegister);
    }

    public static Warehouse[] GetWarehouses(String stateName, String cityName, String streetName, boolean withPastRegister)
    {
        return DatabaseAccess.warehouseOperationsInterface.GetWarehouses(stateName, cityName, streetName, withPastRegister);
    }

    public static Warehouse[] GetWarehouses(String stateName, String cityName, String streetName, int residentialNumber, boolean withPastRegister)
    {
        return DatabaseAccess.warehouseOperationsInterface.GetWarehouses(stateName, cityName, streetName, residentialNumber, withPastRegister);
    }

    public static Boolean TryRegisterWarehouse(Warehouse warehouse)
    {
        return DatabaseAccess.warehouseOperationsInterface.TryRegisterWarehouse(warehouse);
    }

    public static Boolean TryUpdateWarehouse(Warehouse warehouse)
    {
        return DatabaseAccess.warehouseOperationsInterface.TryUpdateWarehouse(warehouse);
    }
    
    public static Boolean TryRemoveWarehouse(String id)
    {
        return DatabaseAccess.warehouseOperationsInterface.TryRemoveWarehouse(id);
    }
}
