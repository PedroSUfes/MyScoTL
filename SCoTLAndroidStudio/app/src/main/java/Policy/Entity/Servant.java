package Policy.Entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

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
         String endDate,
        Property property
    ) 
    {
        super(cpf, name, cellphone, birthDate, hiringDate, endDate);
        
        m_property = new Property(property);
    }
    
    public Servant(Servant toCopy)
    {
        super(toCopy);

        m_property = new Property(toCopy.m_property);
    }

    public Servant
            (
                    String cpf,
                    String name,
                    String cellphone,
                    String birthDate,
                    Property property
            )
    {
        super(cpf, name, cellphone, birthDate);

        m_property = new Property(property);
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
    public String GetWorkLocalId()
    {
        return m_property.GetId();
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
