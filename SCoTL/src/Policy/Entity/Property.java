package Policy.Entity;

public class Property 
{
    private String m_id;
    private String m_propertyName;
    private String m_stateName;    
    private String m_streetName;    
    private int m_number;    

    public Property
    (
        String id,
        String propertyName,
        String stateName,
        String streetName,
        int number
    )
    {
        m_id = id;
        m_propertyName = propertyName;
        m_stateName = stateName;
        m_streetName = streetName;
        m_number = number;
    }

    public Property(Property property)
    {
        m_id = String.copyValueOf(property.m_id.toCharArray());
        m_propertyName = String.copyValueOf(property.m_propertyName.toCharArray());
        m_stateName = String.copyValueOf(property.m_stateName.toCharArray());
        m_streetName = String.copyValueOf(property.m_streetName.toCharArray());
        property.m_number = property.m_number;
    }

    public String GetId() 
    {
        return new String(m_id);
    }

    public String GetPropertyName() 
    {
        return new String(m_propertyName);
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

    public void SetPropertyName(String name)
    {
        m_propertyName = String.copyValueOf(name.toCharArray());
    }

    public void SetStateNAme(String name)
    {
        m_stateName = String.copyValueOf(name.toCharArray());
    }

    public void SetStreetName(String name)
    {
        m_streetName = String.copyValueOf(name.toCharArray());
    }

    public void SetNumber(int number)
    {
        m_number = number;
    }

    public void CopyValuesOf(Property property)
    {
        m_id = property.GetId();
        m_number = property.GetNumber();
        m_propertyName = property.GetStateName();
        m_stateName = property.GetStateName();
        m_streetName = property.GetStreetName();
    }

    @Override
    public String toString()
    {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append("ID: "+m_id+"\n"); 
        toReturn.append("Property Name: "+m_propertyName+"\n");
        toReturn.append("State Name: "+m_stateName+"\n");
        toReturn.append("Street Name: "+m_streetName+"\n");
        toReturn.append("Residential Number: "+m_number+"\n");

        return toReturn.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == this)
        {
            return true;
        }

        if(o == null || o.getClass() != Property.class)
        {
            return false;
        }

        var casted = (Property) o;
        return m_id.equals(casted.m_id);
    }
}
