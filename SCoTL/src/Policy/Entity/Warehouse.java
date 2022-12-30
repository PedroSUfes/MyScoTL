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
        m_streetName = streetName;
        m_number = number;
        m_owner = owner;
    }

    public Warehouse(Warehouse toCopy)
    {
        m_id = toCopy.m_id;
        m_stateName = toCopy.m_stateName;
        m_streetName = toCopy.m_streetName;
        m_number = toCopy.m_number;
        m_owner = new Person(toCopy.GetOwner());
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

    public void SetStateName(String stateName)
    {   
        m_stateName = stateName;
    }

    public void SetStreetName(String streetName)
    {
        m_streetName = streetName;
    }

    public void SetNumber(int number)
    {
        m_number = number;
    }

    public void SetOwner(Person owner)
    {
        m_owner = owner;
    }
    
    public void CopyAttributesOf(Warehouse toCopy)
    {
        m_id = toCopy.m_id;
        m_stateName = toCopy.m_stateName;
        m_streetName = toCopy.m_streetName;
        m_number = toCopy.m_number;
        m_owner = toCopy.GetOwner();
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
        stringBuilder.append("Owner: \n"+m_owner+"\n");

        return stringBuilder.toString();
    }
}
