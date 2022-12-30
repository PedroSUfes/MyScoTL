package Policy.Entity;

public class Person 
{
    protected String m_cpf = null;
    protected String m_name = null;
    protected String m_cellphone = null;
    protected String m_birthDate = null;

    public Person
    (
        String cpf,
        String name,
        String cellphone,
        String birthDate
    )
    {
        m_cpf = cpf;
        m_name = name;
        m_cellphone = cellphone;
        m_birthDate = birthDate;
    }

    public Person(Person toCopy)
    {
        m_cpf = toCopy.m_cpf;
        m_name = toCopy.m_name;
        m_cellphone = toCopy.m_cellphone;
        m_birthDate = toCopy.m_birthDate;
    }

    public String GetCpf()
    {
        return m_cpf;
    }

    public String GetName()
    {
        return m_name;
    }

    public String GetCellphone()
    {
        return m_cellphone;
    }

    public String GetBirthDate()
    {
        return m_birthDate;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("----------------------------------------------------\n");
        stringBuilder.append("CPF: "+m_cpf+"\n");
        stringBuilder.append("Name: "+m_name+"\n");
        stringBuilder.append("Cellphone: "+m_cellphone+"\n");
        stringBuilder.append("Birth date: "+m_birthDate+"\n");
        
        return stringBuilder.toString();
    }
}
