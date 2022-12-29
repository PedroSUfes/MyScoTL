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
        
        m_property = property;
    }
    
    public Property GetProperty()
    {
        return m_property;
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
        stringBuilder.append("Works on property: "+m_property+"\n");
        stringBuilder.append("----------------------------------------------------\n");

        return stringBuilder.toString();
    }
}
