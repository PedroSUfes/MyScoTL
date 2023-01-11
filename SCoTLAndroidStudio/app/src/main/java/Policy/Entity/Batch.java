package Policy.Entity;

public class Batch 
{
    private String m_id = null;
    private String m_creationDate = null;

    public Batch()
    {

    }

    public Batch
    (
        String id,
        String creationDate
    )
    {
        m_id = String.copyValueOf(id.toCharArray());
        m_creationDate = String.copyValueOf(creationDate.toCharArray());
    }

    public Batch(Batch toCopy)
    {
        // Lembre-se que String é uma classe. Logo, é um tipo referência
        // Assim, para não guardar uma referência a uma string externa,
        // faz-se uma cópia

        m_id = new String(toCopy.m_id);
        m_creationDate = new String(toCopy.m_creationDate);
    }

    public String GetId() 
    {
        return new String(m_id);
    }

    public String GetCreationDate() 
    {
        return new String(m_creationDate);
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
