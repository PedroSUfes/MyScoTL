package Policy.Entity;

public abstract class Employee 
extends
    Person
{
    protected String m_hiringDate = null;
        
    public Employee
    (
        String cpf, 
        String name, 
        String cellphone, 
        String birthDate,
        String hiringDate
    ) 
    {
        super(cpf, name, cellphone, birthDate);
        
        m_hiringDate = hiringDate;
    }
    
    public String GetHiringDate()
    {
        return m_hiringDate;
    }

    public abstract EmployeeType GetEmployeeType();

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("Hiring date: "+m_hiringDate+"\n");
        
        return stringBuilder.toString();
    }
}
