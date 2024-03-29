package Policy.BusinessRules.Adapters;

import Policy.Entity.Servant;
import Policy.Entity.Employee;
import Policy.Entity.WarehouseManager;

public interface EmployeeOperationsInterface 
{
    public Employee[] GetEmployees();
    public Employee GetEmployee(String cpf);
    public Servant[] GetServants();
    public Servant GetServant(String cpf);
    public WarehouseManager[] GetWarehouseManagers();
    public WarehouseManager GetWarehouseManager(String cpf);
    public Boolean TryRegisterServant(Servant servant, String beginDate);
    public Boolean TryRegisterWarehouseManager(WarehouseManager warehouseManager, String beginDate);
    public Boolean TryUpdateServant(Servant servant, String date);
    public Boolean TryUpdateWarehouseManager(WarehouseManager warehouseManager, String date);
    public Boolean TryRemoveEmployee(String cpf);
}
