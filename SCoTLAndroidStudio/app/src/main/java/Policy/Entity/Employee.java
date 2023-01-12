package Policy.Entity;

public abstract class Employee 
extends
    Person
{
    protected String m_hiringDate = null;
    protected String m_endDate = null;
        
    public Employee
    (
        String cpf, 
        String name, 
        String cellphone, 
        String birthDate,
        String hiringDate,
        String endDate
    ) 
    {
        super(cpf, name, cellphone, birthDate);
        
        m_hiringDate = new String(hiringDate);
        if(endDate != null)
        {
            m_endDate = new String(endDate);
        }
    }

    public Employee(Employee toCopy)
    {
        super(toCopy.m_cpf, toCopy.m_name, toCopy.m_cellphone, toCopy.m_birthDate);

        m_hiringDate = new String(toCopy.m_hiringDate);
        if(toCopy.m_endDate != null)
        {
            m_endDate = new String(toCopy.m_endDate);
        }
    }

    public String GetHiringDate()
    {
        return new String(m_hiringDate);
    }

    public String GetEndDate()
    {
        return new String(m_endDate);
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("Hiring date: "+m_hiringDate+"\n");
        stringBuilder.append("End date: "+m_endDate+"\n");

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
