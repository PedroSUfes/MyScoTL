package Policy.BusinessRules;

import Policy.Entity.Servant;
import Policy.Entity.Warehouse;
import Policy.Entity.Employee;
import Policy.Entity.Property;
import Policy.Entity.WarehouseManager;

public interface EmployeeOperationsInterface 
{
    public Employee[] GetEmployees();
    public Employee GetEmployee(String cpf);
    public Boolean TryRegisterServant(Servant servant, String beginDate);
    public Boolean TryRegisterWarehouseManager(WarehouseManager warehouseManager, String beginDate);
    public Boolean TryUpdateServant(Servant servant);
    public Boolean TryUpdateServantProperty(String servantCpf, Property property, String date);
    public Boolean TryUpdateWarehouseManager(WarehouseManager warehouseManager);
    public Boolean TryUpdateWarehouseManagerWarehouse(String warehouseManagerCpf, Warehouse warehouse, String date);
    public Boolean TryRemoveEmployee(String cpf);
}
