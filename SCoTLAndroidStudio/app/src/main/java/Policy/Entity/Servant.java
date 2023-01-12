package Policy.Entity;

public class Servant 
extends
    Employee
{
    private Property m_property = null;
    
    public Servant
    (
        String cpf, 
        String name, 
        String cellphone, 
        String birthDate, 
        String hiringDate,
        Property property
    ) 
    {
        super(cpf, name, cellphone, birthDate, hiringDate);
        
        m_property = new Property(property);
    }
    
    public Servant(Servant toCopy)
    {
        super(toCopy);

        m_property = new Property(toCopy.m_property);
    }

    public Property GetProperty()
    {
        return new Property(m_property);
    }

    public void SetProperty(Property property)
    {
        m_property.CopyValuesOf(property);
    }

    public void CopyValuesOf(Servant toCopy)
    {
        super.CopyValuesOf(toCopy);

        m_property.CopyValuesOf(toCopy.m_property);
    }

    @Override
    public EmployeeType GetEmployeeType()
    {
        return EmployeeType.SERVANT;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString()+"\n");
        stringBuilder.append("Job: "+GetEmployeeType().toString()+"\n");
        stringBuilder.append("Hiring date: "+m_hiringDate+"\n");
        stringBuilder.append("Works on property: "+m_property+"\n");
        stringBuilder.append("----------------------------------------------------\n");

        return stringBuilder.toString();
    }
}
