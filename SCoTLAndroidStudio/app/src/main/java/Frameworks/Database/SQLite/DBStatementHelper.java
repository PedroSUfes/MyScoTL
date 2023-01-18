package Frameworks.Database.SQLite;

public class DBStatementHelper
{
    public String m_whereClause = null;
    public String[] m_args = null;

    public DBStatementHelper(String whereClause, String[] args)
    {
        m_whereClause = whereClause;
        m_args = args;
    }
}
