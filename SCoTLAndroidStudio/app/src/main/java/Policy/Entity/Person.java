package Policy.Entity;

public class Person 
{
    protected String m_cpf = null;
    protected String m_name = null;
    protected String m_cellphone = null;
    protected String m_birthDate = null;

    public Person(String cpf)
    {
        m_cpf = cpf;
    }

    public Person
    (
        String cpf,
        String name,
        String cellphone,
        String birthDate
    )
    {
        m_cpf = new String(cpf);
        m_name = new String(name);
        m_cellphone = new String(cellphone);
        m_birthDate = new String(birthDate);
    }

    public Person(Person toCopy)
    {
        if(toCopy == null)
        {
            return;
        }
        m_cpf = toCopy.GetCpf();
        m_name = toCopy.GetName();
        m_cellphone = toCopy.GetCellphone();
        m_birthDate = toCopy.GetBirthDate();
    }

    public String GetCpf()
    {
        return m_cpf == null ? null : new String(m_cpf);
    }

    public String GetName()
    {
        return m_name == null ? null : new String(m_name);
    }

    public String GetCellphone()
    {
        return m_name == null ? null : new String(m_cellphone);
    }

    public String GetBirthDate()
    {
        return m_birthDate == null ? null : new String(m_birthDate);
    }

    public void SetCpf(String cpf)
    {
        m_cpf = String.copyValueOf(cpf.toCharArray());
    }

    public void SetName(String name)
    {
        m_name = String.copyValueOf(name.toCharArray());
    }

    public void SetCellphone(String cellphone)
    {
        m_cellphone = String.copyValueOf(cellphone.toCharArray());
    }

    public void CopyValuesOf(Person person)
    {
        m_cpf = person.GetCpf();
        m_name = person.GetName();
        m_cellphone = person.GetCellphone();
        m_birthDate = person.GetBirthDate(); 
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
