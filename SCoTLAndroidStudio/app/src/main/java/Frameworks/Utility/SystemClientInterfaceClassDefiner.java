package Frameworks.Utility;

import com.example.scotl.BatchListMenu;
import com.example.scotl.CoffeeBagMenu;
import com.example.scotl.MainMenuActivity;
import com.example.scotl.SystemClientReadEmployeeActivity;
import com.example.scotl.SystemClientReadPropertyActivity;
import com.example.scotl.SystemClientReadWarehouseActivity;

import Policy.BusinessRules.UserType;

public class SystemClientInterfaceClassDefiner implements InterfaceClassDefiner
{

	public void DefineMenuClassPrincipal(UserType userType){
		if(userType != UserType.SYSTEM_CLIENT){
			return;
		}

		InterfaceClasses.mainMenuClass = MainMenuActivity.class;
		InterfaceClasses.employeeClass = SystemClientReadEmployeeActivity.class;
		InterfaceClasses.propertyClass = SystemClientReadPropertyActivity.class;
		InterfaceClasses.batchClass = BatchListMenu.class;
		InterfaceClasses.coffeeBagClass = CoffeeBagMenu.class;
		InterfaceClasses.warehouseClass = SystemClientReadWarehouseActivity.class;
	}
}
