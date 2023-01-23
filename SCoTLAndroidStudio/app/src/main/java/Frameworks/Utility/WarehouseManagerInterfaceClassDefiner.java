package Frameworks.Utility;


import com.example.scotl.BatchListMenu;
import com.example.scotl.CoffeeBagMenu;
import com.example.scotl.MainMenuActivity;
import com.example.scotl.ManagerReadEmployeeActivity;
import com.example.scotl.ManagerReadPropertyActivity;
import com.example.scotl.ManagerReadWarehouseActivity;

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
		InterfaceClasses.batchClass = BatchListMenu.class;
		InterfaceClasses.coffeeBagClass = CoffeeBagMenu.class;
		InterfaceClasses.propertyClass = ManagerReadPropertyActivity.class;
		InterfaceClasses.warehouseClass = ManagerReadWarehouseActivity.class;
	}
}
