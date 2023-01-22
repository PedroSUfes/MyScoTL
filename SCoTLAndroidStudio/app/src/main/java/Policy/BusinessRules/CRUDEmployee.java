package Policy.BusinessRules;

import Policy.Entity.Employee;
import Policy.Entity.Servant;
import Policy.Entity.WarehouseManager;

public class CRUDEmployee 
{
    public static Employee[] GetEmployees(boolean withPastRegister)
    {
        return DatabaseAccess.employeeOperationsInterface.GetEmployees(withPastRegister);
    }

    public static Employee GetEmployee(String cpf, boolean withPastRegister)
    {
        return DatabaseAccess.employeeOperationsInterface.GetEmployee(cpf, withPastRegister);
    }

    public static Servant[] GetServants(boolean withPastRegister)
    {
        return DatabaseAccess.employeeOperationsInterface.GetServants(withPastRegister);
    }

    public static Servant[] GetServant(String cpf, boolean withPastRegister)
    {
        return DatabaseAccess.employeeOperationsInterface.GetServant(cpf, withPastRegister);
    }

    public static WarehouseManager[] GetWarehouseManagers(boolean withPastRegister)
    {
        return DatabaseAccess.employeeOperationsInterface.GetWarehouseManagers(withPastRegister);
    }

    public static WarehouseManager[] GetWarehouseManager(String cpf, boolean withPastRegister)
    {
        return DatabaseAccess.employeeOperationsInterface.GetWarehouseManager(cpf, withPastRegister);
    }

    public static Boolean TryRegisterServant(Servant servant)
    {
        return DatabaseAccess.employeeOperationsInterface.TryRegisterServant(servant);
    }

    public static Boolean TryRegisterWarehouseManager(WarehouseManager warehouseManager)
    {
        return DatabaseAccess.employeeOperationsInterface.TryRegisterWarehouseManager(warehouseManager);
    }

    public static Boolean TryUpdateServant(Servant servant, String date)
    {
        return DatabaseAccess.employeeOperationsInterface.TryUpdateServant(servant, date);
    }
    
    // public static Boolean TryUpdateServantProperty(String servantCpf, Property property, String date)
    // {
    //     return DatabaseAccess.employeeOperationsInterface.TryUpdateServantProperty(servantCpf, property, date);
    // }

    public static Boolean TryUpdateWarehouseManager(WarehouseManager warehouseManager, String date)
    {
        return DatabaseAccess.employeeOperationsInterface.TryUpdateWarehouseManager(warehouseManager, date);
    }

    // public static Boolean TryUpdateWarehouseManagerWarehouse(String warehouseManagerCpf, Warehouse warehouse, String date)
    // {
    //     return DatabaseAccess.employeeOperationsInterface.TryUpdateWarehouseManagerWarehouse(warehouseManagerCpf, warehouse, date);
    // }
    
    public static Boolean TryRemoveEmployee(String cpf)
    {
        return DatabaseAccess.employeeOperationsInterface.TryRemoveEmployee(cpf);
    }
}
