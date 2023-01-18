package Frameworks.Utility;

import com.example.scotl.BatchTabletest;
import com.example.scotl.WarehouseMenu;

import Policy.BusinessRules.UserType;

public class WarehouseManagerInterfaceClassDefiner implements InterfaceClassDefiner{

	public void DefineMenuClassPrincipal(UserType userType){
		if(userType != UserType.WAREHOUSE_MANAGER){
			return;
		}

		InterfaceClasses.mainMenuClass = WarehouseMenu.class;
	}

}
