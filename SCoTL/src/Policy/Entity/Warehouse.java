package Policy.Entity;

public class Warehouse 
{
    private String m_id = null;    
    private String m_stateName = null;    
    private String m_streetName = null;    
    private int m_number = 0;    
    private Person m_owner = null;

    public Warehouse
    (
        String id,
        String stateName,
        String streetName,
        int number,
        Person owner
    )
    {
        m_id = id;
        m_stateName = stateName;
        m_number = number;
        m_owner = owner;
    }

    public String GetId() 
    {
        return m_id;
    }

    public String GetStateName() 
    {
        return m_stateName;
    }

    public String GetStreetName() 
    {
        return m_streetName;
    }

    public int GetNumber() 
    {
        return m_number;
    }

    public Person GetOwner() 
    {
        return m_owner;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("Id: "+m_id+"\n");
        stringBuilder.append("State name: "+m_stateName+"\n");
        stringBuilder.append("Street name: "+m_streetName+"\n");
        stringBuilder.append("Number: "+m_number+"\n");
        stringBuilder.append("Owner: "+m_owner+"\n");

        return stringBuilder.toString();
    }
}
