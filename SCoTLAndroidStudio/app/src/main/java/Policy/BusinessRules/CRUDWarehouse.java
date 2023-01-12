package Policy.BusinessRules;

import Policy.Entity.Warehouse;

public class CRUDWarehouse 
{
    public static Warehouse[] GetWarehouses()
    {
        return DatabaseAccess.warehouseOperationsInterface.GetWarehouses();
    }

    public static Warehouse[] GetWarehouses(String stateName)
    {
        return DatabaseAccess.warehouseOperationsInterface.GetWarehouses(stateName);
    }

    public static Warehouse[] GetWarehouses(String stateName, String streetName)
    {
        return DatabaseAccess.warehouseOperationsInterface.GetWarehouses(stateName, streetName);
    }

    public static Warehouse[] GetWarehousesByOwnerCpf(String ownerCpf)
    {
        return DatabaseAccess.warehouseOperationsInterface.GetWarehousesByOwnerCpf(ownerCpf);
    }

    public static Warehouse[] GetWarehouse(String id, boolean withPastRegister)
    {
        return DatabaseAccess.warehouseOperationsInterface.GetWarehouse(id, withPastRegister);
    }

    public static Warehouse GetWarehouse(String stateName, String streetName, int number)
    {
        return DatabaseAccess.warehouseOperationsInterface.GetWarehouse(stateName, streetName, number);
    }

    public static Boolean TryRegisterWarehouse(Warehouse warehouse)
    {
        return DatabaseAccess.warehouseOperationsInterface.TryRegisterWarehouse(warehouse);
    }

    public static Boolean TryUpdateWarehouse(Warehouse warehouse, String date)
    {
        return DatabaseAccess.warehouseOperationsInterface.TryUpdateWarehouse(warehouse, date);
    }
    
    public static Boolean TryRemoveWarehouse(String id)
    {
        return DatabaseAccess.warehouseOperationsInterface.TryRemoveWarehouse(id);
    }
}
