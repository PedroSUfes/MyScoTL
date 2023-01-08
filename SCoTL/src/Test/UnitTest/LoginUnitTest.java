package Test.UnitTest;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.DatabaseAccess;
import Policy.BusinessRules.LoginManager;
import Policy.BusinessRules.UserType;
import Policy.Entity.User;

public class LoginUnitTest 
{
    private static MyDatabase database = null;

    public static void main(String[] args) throws Exception 
    {
        InjectDatabase();
        DefineUsers();
        TryLogIn();
    }
    
    private static void InjectDatabase()
    {
        database = new MyDatabase();
        DatabaseAccess.loginInterface = database;
    }
    
    private static void DefineUsers()
    {
        database.AddUser(new User("Pedro", "200601", UserType.SYSTEM_CLIENT));
    }
    
    private static void TryLogIn()
    {
        if(LoginManager.TryLogin("Pedro", "20001"))
        {   
            System.out.println("Success on login");
            System.out.println("User Type: "+LoginManager.GetUserType());
        }
    }
}
