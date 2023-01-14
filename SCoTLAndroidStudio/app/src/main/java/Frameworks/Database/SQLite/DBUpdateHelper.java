package Frameworks.Database.SQLite;

public class DBUpdateHelper
{
    public String m_whereClause = null;
    public String[] m_args = null;

    public DBUpdateHelper(String whereClause, String[] args)
    {
        m_whereClause = whereClause;
        m_args = args;
    }
}
