package Policy.Entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Employee
extends
    Person
{
    protected String m_hiringDate = null;
    protected String m_endDate = null;
        
    public Employee
    (
        @NotNull String cpf,
        @NotNull String name,
        @NotNull String cellphone,
        @NotNull String birthDate,
        @NotNull String hiringDate,
        @Nullable String endDate
    ) 
    {
        super(cpf, name, cellphone, birthDate);
        
        m_hiringDate = new String(hiringDate);
        if(endDate != null)
        {
            m_endDate = new String(endDate);
        }
    }

    public Employee
            (
                    @NotNull Person person,
                    @NotNull String hiringDate,
                    @Nullable String endDate
            )
    {
        super(person);
        m_hiringDate = new String(hiringDate);
        m_endDate = endDate == null ? null : new String(endDate);
    }

    public Employee(Employee toCopy)
    {
        super(toCopy.m_cpf, toCopy.m_name, toCopy.m_cellphone, toCopy.m_birthDate);

        m_hiringDate = toCopy.GetHiringDate();
        m_endDate = toCopy.GetEndDate();
    }

    public Employee
            (
                    @NotNull String cpf,
                    @NotNull String name,
                    @NotNull String cellphone,
                    @NotNull String birthDate
            )
    {
        super(cpf, name, cellphone, birthDate);
    }

    public String GetHiringDate()
    {
        return m_hiringDate == null ? null : new String(m_hiringDate);
    }

    public String GetEndDate()
    {
        return m_endDate == null ? null : new String(m_endDate);
    }

    public void SetEndDate(@NotNull String endDate)
    {
        m_endDate = new String(endDate);
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

    public static Employee GetEmployeeCopy(@NotNull Employee toCopy)
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
