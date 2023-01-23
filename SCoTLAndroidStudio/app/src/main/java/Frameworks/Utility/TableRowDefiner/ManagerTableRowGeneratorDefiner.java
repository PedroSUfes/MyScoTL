package Frameworks.Utility.TableRowDefiner;

import Frameworks.Adapters.EmployeeRow.ManagerEmployeeTableRowGenerator;
import Frameworks.Adapters.PropertyRow.ManagerPropertyTableRowGenerator;
import Frameworks.Adapters.TableRowGenerator;
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
    }
}
