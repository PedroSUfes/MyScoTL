package Policy.BusinessRules.Adapters;

import Policy.Entity.Servant;
import Policy.Entity.Employee;
import Policy.Entity.WarehouseManager;

public interface EmployeeOperationsInterface 
{
    public Employee[] GetEmployees(boolean withPastRegister); // Remover
    public Employee[] GetEmployee(String cpf, boolean withPastRegister); // Remover
    public Servant[] GetServants(boolean withPastRegister);
    public Servant[] GetServant(String cpf, boolean withPastRegister);
    public WarehouseManager[] GetWarehouseManagers(boolean withPastRegister); // Atualizar !!
    public WarehouseManager[] GetWarehouseManager(String cpf, boolean withPastRegister);
    public Boolean TryRegisterServant(Servant servant);
    public Boolean TryRegisterWarehouseManager(WarehouseManager warehouseManager);
    public Boolean TryUpdateServant(Servant servant, String date);
    public Boolean TryUpdateWarehouseManager(WarehouseManager warehouseManager, String date);
    public Boolean TryRemoveEmployee(String cpf);
}
