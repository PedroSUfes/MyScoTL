package Policy.Entity;

public class Batch 
{
    private String m_id = null;
    private String m_creationDate = null;

    //Constructors
    public Batch()
    {

    }

    public Batch
    (
        String id,
        String creationDate
    )
    {
        m_id = new String(id);
        m_creationDate = new String(creationDate);
    }

    public Batch(Batch toCopy)
    {
        // Lembre-se que String é uma classe. Logo, é um tipo referência
        // Assim, para não guardar uma referência a uma string externa,
        // faz-se uma cópia

        m_id = new String(toCopy.m_id);
        m_creationDate = new String(toCopy.m_creationDate);
    }

    //Getters and Setters
    public String GetId() 
    {
        //return new String(m_id);
        return m_id;
    }

    public String GetCreationDate() 
    {
        //return new String(m_creationDate);
        return m_creationDate;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Batch ID: "+m_id+"\n");
        stringBuilder.append("Created on: "+m_creationDate+"\n");

        return stringBuilder.toString();
    }
}
