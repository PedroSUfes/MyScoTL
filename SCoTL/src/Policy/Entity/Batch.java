package Policy.Entity;

public class Batch 
{
    private String m_id = new String();
    private String m_creationDate = new String();
    
    public Batch
    (
        String id,
        String creationDate
    )
    {
        m_id = id;
        m_creationDate = creationDate;
    }

    public Batch(Batch toCopy)
    {
        // Lembre-se que String é uma classe. Logo, é um tipo referência
        // Assim, para não guardar uma referência a uma string externa,
        // faz-se uma cópia

        m_id = String.copyValueOf(toCopy.m_id.toCharArray());
        m_creationDate = String.copyValueOf(toCopy.m_creationDate.toCharArray());
    }

    public String GetId() 
    {
        return m_id;
    }

    public String GetCreationDate() 
    {
        return m_creationDate;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Bach ID: "+m_id+"\n");
        stringBuilder.append("Created on: "+m_creationDate+"\n");

        return stringBuilder.toString();
    }
}
