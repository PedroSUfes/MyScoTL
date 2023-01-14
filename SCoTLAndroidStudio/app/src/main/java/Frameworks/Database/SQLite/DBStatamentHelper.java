package Frameworks.Database.SQLite;

public class DBStatamentHelper
{
    public static String m_whereClause = null;
    public static String[] m_args = null;

    public DBStatamentHelper(String whereClause, String[] args)
    {
        m_whereClause = whereClause;
        m_args = args;
    }

    public static String getM_whereClause() {
        return m_whereClause;
    }

    public static String[] getM_args() {
        return m_args;
    }
}
