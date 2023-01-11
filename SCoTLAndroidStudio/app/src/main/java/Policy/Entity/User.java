package Policy.Entity;

import Policy.BusinessRules.UserType;

public class User 
{
    private String m_login = null;
    private String m_password = null;
    private UserType m_userType = UserType.NULL;

    public User
    (
        String login,
        String password,
        UserType userType
    )
    {
        m_login = new String(login);
        m_password = new String(password);
        m_userType = userType;
    }

    public String GetLogin() 
    {
        return new String(m_login);
    }

    public String GetPassword() 
    {
        return (new String());
    }

    public UserType GetUserType()
    {
        return m_userType;
    }
}
