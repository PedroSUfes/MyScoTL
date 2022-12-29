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
        m_id = property.m_id;
        m_propertyName = property.m_propertyName;
        m_stateName = property.m_stateName;
        m_streetName = property.m_streetName;
        property.m_number = property.m_number;
    }

    public String GetId() 
    {
        return m_id;
    }

    public String GetPropertyName() 
    {
        return m_propertyName;
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
}
