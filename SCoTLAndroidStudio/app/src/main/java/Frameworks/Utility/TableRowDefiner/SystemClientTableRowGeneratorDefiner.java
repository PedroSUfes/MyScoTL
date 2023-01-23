package Frameworks.Utility.TableRowDefiner;

import Frameworks.Adapters.CoffeeBagRow.SystemClientCoffeeBagTableRowGenerator;
import Frameworks.Adapters.EmployeeRow.SystemClientEmployeeTableRowGenerator;
import Frameworks.Adapters.PropertyRow.SystemClientPropertyTableRowGenerator;
import Frameworks.Adapters.TableRowGenerator;
import Policy.BusinessRules.LoginManager;
import Policy.BusinessRules.UserType;

public class SystemClientTableRowGeneratorDefiner
    implements
        TableRowGeneratorDefiner
{

    @Override
    public void DefineTableRowGenerator()
    {
        if(LoginManager.GetUserType() != UserType.SYSTEM_CLIENT)
        {
            return;
        }

        TableRowGenerator.SetEmployeeTableRowGenerator(new SystemClientEmployeeTableRowGenerator());
        TableRowGenerator.SetPropertyTableRowGenerator(new SystemClientPropertyTableRowGenerator());
        TableRowGenerator.SetCoffeeBagTableRowGenerator(new SystemClientCoffeeBagTableRowGenerator());
    }
}
