package Frameworks.Utility;

import com.example.scotl.SystemClientMenu;

import Policy.BusinessRules.UserType;

public class SystemClientInterfaceClassDefiner implements InterfaceClassDefiner{

	public void DefineMenuClassPrincipal(UserType userType){
		if(userType != UserType.SYSTEM_CLIENT){
			return;
		}

		InterfaceClasses.mainMenuClass = SystemClientMenu.class;
	}

}
