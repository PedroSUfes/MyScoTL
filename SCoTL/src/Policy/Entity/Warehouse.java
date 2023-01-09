package Policy.Entity;

public class Warehouse 
{
    private String m_id = new String();    
    private String m_stateName = new String();    
    private String m_streetName = new String();    
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
        m_id = String.copyValueOf(id.toCharArray());
        m_stateName = String.copyValueOf(stateName.toCharArray());
        m_streetName = String.copyValueOf(streetName.toCharArray());
        m_number = number;
        m_owner = new Person(owner);
    }

    public Warehouse(Warehouse toCopy)
    {
        m_id = toCopy.GetId();
        m_stateName = toCopy.GetStateName();
        m_streetName = toCopy.GetStateName();
        m_number = toCopy.m_number;
        m_owner = new Person(toCopy.GetOwner());
    }

    public String GetId() 
    {
        return new String(m_id);
    }

    public String GetStateName() 
    {
        return new String(m_stateName);
    }

    public String GetStreetName() 
    {
        return new String(m_streetName);
    }

    public int GetNumber() 
    {
        return m_number;
    }

    public Person GetOwner() 
    {
        return new Person(m_owner);
    }

    public void SetStateName(String stateName)
    {   
        m_stateName = String.copyValueOf(stateName.toCharArray());
    }

    public void SetStreetName(String streetName)
    {
        m_streetName = String.copyValueOf(streetName.toCharArray());
    }

    public void SetNumber(int number)
    {
        m_number = number;
    }

    public void SetOwner(Person owner)
    {
        m_owner.CopyValuesOf(owner);
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
    public boolean equals(Object o)
    {
        if(o == this)
        {
            return true;
        }

        if(o.getClass() != Warehouse.class)
        {
            return false;
        }

        var casted = (Warehouse) o;

        return casted.GetId().equals(m_id);
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("Warehouse ID: "+m_id+"\n");
        stringBuilder.append("State name: "+m_stateName+"\n");
        stringBuilder.append("Street name: "+m_streetName+"\n");
        stringBuilder.append("Number: "+m_number+"\n");
        stringBuilder.append("Owner: \n"+m_owner+"\n");

        return stringBuilder.toString();
    }
}
