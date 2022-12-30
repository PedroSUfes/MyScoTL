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

    public Employee(Employee toCopy)
    {
        super(toCopy.m_cpf, toCopy.m_name, toCopy.m_cellphone, toCopy.m_birthDate);

        m_hiringDate = toCopy.m_hiringDate;
    }

    public String GetHiringDate()
    {
        return m_hiringDate;
    }
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("Hiring date: "+m_hiringDate+"\n");
        
        return stringBuilder.toString();
    }

    public abstract EmployeeType GetEmployeeType();

    public static Employee GetEmployeeCopy(Employee toCopy)
    {
        if(toCopy.GetEmployeeType() == EmployeeType.SERVANT)
        {
            return new Servant((Servant) toCopy);
        }
        else if(toCopy.GetEmployeeType() == EmployeeType.WAREHOUSE_MANAGER)
        {
            return new WarehouseManager((WarehouseManager) toCopy);
        }
    
        return null;
    }
}
