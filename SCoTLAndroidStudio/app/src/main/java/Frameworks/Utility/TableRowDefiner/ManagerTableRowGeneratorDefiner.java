package Frameworks.Utility.TableRowDefiner;

import Frameworks.Adapters.BatchRow.SystemClientBatchTableRowGenerator;
import Frameworks.Adapters.CoffeeBagRow.SystemClientCoffeeBagTableRowGenerator;
import Frameworks.Adapters.EmployeeRow.ManagerEmployeeTableRowGenerator;
import Frameworks.Adapters.PropertyRow.ManagerPropertyTableRowGenerator;
import Frameworks.Adapters.TableRowGenerator;
import Frameworks.Adapters.WarehouseRow.ManagerWarehouseTableRowGenerator;
import Policy.BusinessRules.LoginManager;
import Policy.BusinessRules.UserType;

public class ManagerTableRowGeneratorDefiner
    implements
        TableRowGeneratorDefiner
{
    @Override
    public void DefineTableRowGenerator()
    {
        if(LoginManager.GetUserType() != UserType.WAREHOUSE_MANAGER)
        {
            return;
        }

        TableRowGenerator.SetEmployeeTableRowGenerator(new ManagerEmployeeTableRowGenerator());
        TableRowGenerator.SetPropertyTableRowGenerator(new ManagerPropertyTableRowGenerator());
        TableRowGenerator.SetBatchTableRowGenerator(new SystemClientBatchTableRowGenerator());
        TableRowGenerator.SetCoffeeBagTableRowGenerator(new SystemClientCoffeeBagTableRowGenerator());
        TableRowGenerator.SetWarehouseTableRowGenerator(new ManagerWarehouseTableRowGenerator());
    }
}
