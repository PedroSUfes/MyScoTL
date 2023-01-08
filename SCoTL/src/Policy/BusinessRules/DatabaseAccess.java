package Policy.BusinessRules;

public class DatabaseAccess 
{
    // Devem ser definidas a partir de injeção de dependência
    public static LoginInterface loginInterface = null;    
    public static EmployeeOperationsInterface employeeOperationsInterface = null;    
    public static BatchOperationsInterface batchOperationsInterface = null;    
    public static CoffeeBagOperationsInterface coffeeBagOperationsInterface = null;
    public static WarehouseOperationsInterface warehouseOperationsInterface = null; 
    public static PropertyOperationsInterface propertyOperationsInterface = null;  
}
