package Policy.BusinessRules;

public class LoginManager 
{
    private static UserType m_userType = UserType.NULL;

    public static Boolean TryLogin(String login, String password)
    {
        return DatabaseAccess.loginInterface.TryLogin(login, password);
    }

    public static UserType GetUserType()
    {
        return m_userType;
    }

    public static void SetUserType(UserType userType)
    {
        m_userType = userType;
    }
}
