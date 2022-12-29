package Policy.Entity;

public class Batch 
{
    private String m_id = null;
    private String m_creationDate = null;
    
    public Batch
    (
        String id,
        String creationDate
    )
    {
        m_id = id;
        m_creationDate = creationDate;
    }

    public String GetId() 
    {
        return m_id;
    }

    public String GetCreationDate() 
    {
        return m_creationDate;
    }
}
