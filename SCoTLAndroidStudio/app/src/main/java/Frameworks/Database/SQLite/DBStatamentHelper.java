package Frameworks.Database.SQLite;

public class DBStatamentHelper
{
    public String m_whereClause = null;
    public String[] m_args = null;

    public DBStatamentHelper(String whereClause, String[] args)
    {
        m_whereClause = whereClause;
        m_args = args;
    }
}
