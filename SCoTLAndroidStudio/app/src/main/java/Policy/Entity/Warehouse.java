package Policy.Entity;

public class Warehouse 
{
    private String m_id = null;
    private String m_stateName = null;
    private String m_cityName = null;
    private String m_streetName = null;
    private int m_number = 0;    
    private Person m_owner = null;

    public Warehouse() {}

    public Warehouse(String id)
    {
        m_id = new String(id);
    }

    public Warehouse
    (
        String id,
        String stateName,
        String cityName,
        String streetName,
        int number,
        Person owner
    )
    {
        m_id = new String(id);
        m_stateName = new String(stateName);
        m_cityName = new String(cityName);
        m_streetName = new String(streetName);
        m_number = number;
        m_owner = new Person(owner);
    }

    public Warehouse(Warehouse toCopy)
    {
        m_id = toCopy.GetId();
        if(toCopy.m_stateName != null)
        {
            m_stateName = toCopy.GetStateName();
        }
        if(toCopy.m_cityName != null)
        {
            m_cityName = toCopy.m_cityName;
        }
        if(toCopy.m_streetName != null)
        {
            m_streetName = toCopy.GetStreetName();
        }
        if(toCopy.m_owner != null)
        {
            m_owner = new Person(toCopy.GetOwner());
        }
        m_number = toCopy.m_number;
    }

    public String GetId() 
    {
        return new String(m_id);
    }

    public String GetStateName() 
    {
        return new String(m_stateName);
    }

    public String GetCityName()
    {
        return new String(m_cityName);
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

    public void SetCityName(String cityName)
    {
        m_cityName = String.copyValueOf(cityName.toCharArray());
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

        Warehouse casted = (Warehouse) o;

        return casted.GetId().equals(m_id);
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("Warehouse ID: "+m_id+"\n");
        stringBuilder.append("State name: "+m_stateName+"\n");
        stringBuilder.append("City name: "+m_cityName+"\n");
        stringBuilder.append("Street name: "+m_streetName+"\n");
        stringBuilder.append("Number: "+m_number+"\n");
        stringBuilder.append("Owner: \n"+m_owner+"\n");

        return stringBuilder.toString();
    }
}
