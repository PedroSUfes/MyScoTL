package Policy.BusinessRules;

import Policy.Entity.Servant;
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
    public Boolean TryUpdateWarehouseManager(WarehouseManager warehouseManager);
    public Boolean TryRemoveEmployee(String cpf, String currentDate);
}
