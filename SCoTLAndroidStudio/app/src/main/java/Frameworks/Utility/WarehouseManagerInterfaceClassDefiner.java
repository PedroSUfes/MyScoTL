package Frameworks.Utility;

import com.example.scotl.MainMenuActivity;
import com.example.scotl.ManagerReadEmployeeActivity;
import com.example.scotl.ManagerReadPropertyActivity;

import Policy.BusinessRules.UserType;

public class WarehouseManagerInterfaceClassDefiner
		implements
			InterfaceClassDefiner
{

	public void DefineMenuClassPrincipal(UserType userType){
		if(userType != UserType.WAREHOUSE_MANAGER){
			return;
		}

		InterfaceClasses.mainMenuClass = MainMenuActivity.class;
		InterfaceClasses.employeeClass = ManagerReadEmployeeActivity.class;
		InterfaceClasses.propertyClass = ManagerReadPropertyActivity.class;
	}
}
