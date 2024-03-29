package Frameworks.Utility.TableRowDefiner;


import Frameworks.Adapters.BatchRow.SystemClientBatchTableRowGenerator;
import Frameworks.Adapters.CoffeeBagRow.SystemClientCoffeeBagTableRowGenerator;

import Frameworks.Adapters.EmployeeRow.SystemClientEmployeeTableRowGenerator;
import Frameworks.Adapters.PropertyRow.SystemClientPropertyTableRowGenerator;
import Frameworks.Adapters.TableRowGenerator;
import Frameworks.Adapters.WarehouseRow.SystemClientWarehouseTableRowGenerator;
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
		TableRowGenerator.SetBatchTableRowGenerator(new SystemClientBatchTableRowGenerator());
        TableRowGenerator.SetCoffeeBagTableRowGenerator(new SystemClientCoffeeBagTableRowGenerator());
        TableRowGenerator.SetWarehouseTableRowGenerator(new SystemClientWarehouseTableRowGenerator());
    }
}
