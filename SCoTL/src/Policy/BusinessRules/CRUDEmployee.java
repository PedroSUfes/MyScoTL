package Policy.BusinessRules;

import Policy.Entity.Employee;
import Policy.Entity.Property;
import Policy.Entity.Servant;
import Policy.Entity.Warehouse;
import Policy.Entity.WarehouseManager;

public class CRUDEmployee 
{
    public static Employee[] GetEmployees()
    {
        return DatabaseAccess.employeeOperationsInterface.GetEmployees();
    }

    public static Employee GetEmployee(String cpf)
    {
        return DatabaseAccess.employeeOperationsInterface.GetEmployee(cpf);
    }

    public static Boolean TryRegisterServant(Servant servant, String beginDate)
    {
        return DatabaseAccess.employeeOperationsInterface.TryRegisterServant(servant, beginDate);
    }

    public static Boolean TryRegisterWarehouseManager(WarehouseManager warehouseManager, String beginDate)
    {
        return DatabaseAccess.employeeOperationsInterface.TryRegisterWarehouseManager(warehouseManager, beginDate);
    }

    public static Boolean TryUpdateServant(Servant servant)
    {
        return DatabaseAccess.employeeOperationsInterface.TryUpdateServant(servant);
    }
    
    public static Boolean TryUpdateServantProperty(String servantCpf, Property property, String date)
    {
        return DatabaseAccess.employeeOperationsInterface.TryUpdateServantProperty(servantCpf, property, date);
    }

    public static Boolean TryUpdateWarehouseManager(WarehouseManager warehouseManager)
    {
        return DatabaseAccess.employeeOperationsInterface.TryUpdateWarehouseManager(warehouseManager);
    }

    public static Boolean TryUpdateWarehouseManagerWarehouse(String warehouseManagerCpf, Warehouse warehouse, String date)
    {
        return DatabaseAccess.employeeOperationsInterface.TryUpdateWarehouseManagerWarehouse(warehouseManagerCpf, warehouse, date);
    }
    
    public static Boolean TryRemoveEmployee(String cpf)
    {
        return DatabaseAccess.employeeOperationsInterface.TryRemoveEmployee(cpf);
    }
}
