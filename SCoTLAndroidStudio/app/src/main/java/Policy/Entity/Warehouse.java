package Policy.Entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Warehouse
{
    private String m_id = null;
    private String m_stateName = null;
    private String m_cityName = null;
    private String m_streetName = null;
    private int m_number = 0;
    private String m_beginDate = null;
    private String m_endDate = null;
    private Person m_owner = null;

    public Warehouse() {}

    public Warehouse(String id)
    {
        m_id = new String(id);
    }

    public Warehouse
    (
        @NonNull String id,
        @NonNull String stateName,
        @NonNull String cityName,
        @NonNull String streetName,
        int number,
        String beginDate,
        @Nullable String endDate,
        @NonNull Person owner
    )
    {
        m_id = new String(id);
        m_stateName = new String(stateName);
        m_cityName = new String(cityName);
        m_streetName = new String(streetName);
        m_beginDate = new String(beginDate);
        if(m_endDate != null)
        {
            m_endDate = new String(endDate);
        }
        m_number = number;
        m_owner = new Person(owner);
    }

    public Warehouse(Warehouse toCopy)
    {
        m_id = toCopy.GetId();
        if(toCopy.m_stateName != null)
        {
            m_stateName = toCopy.GetStateName();
        }
        if(toCopy.m_cityName != null)
        {
            m_cityName = toCopy.GetCityName();
        }
        if(toCopy.m_streetName != null)
        {
            m_streetName = toCopy.GetStreetName();
        }
        if(toCopy.m_owner != null)
        {
            m_owner = new Person(toCopy.GetOwner());
        }
        if(toCopy.GetBeginDate() != null)
        {
            m_beginDate = toCopy.GetBeginDate();
        }
        if(toCopy.GetEndDate() != null)
        {
            m_endDate = toCopy.GetEndDate();
        }
        m_number = toCopy.m_number;
    }

    public String GetId() 
    {
        if(m_id == null)
        {
            return null;
        }

        return new String(m_id);
    }

    public String GetStateName() 
    {
        if(m_stateName == null)
        {
            return null;
        }
        return new String(m_stateName);
    }

    public String GetCityName()
    {
        if(m_cityName == null)
        {
            return null;
        }
        return new String(m_cityName);
    }

    public String GetStreetName() 
    {
        if(m_streetName == null)
        {
            return null;
        }
        return new String(m_streetName);
    }

    public String GetBeginDate()
    {
        if(m_beginDate == null)
        {
            return null;
        }
        return new String(m_beginDate);
    }

    public String GetEndDate()
    {
        if(m_endDate == null)
        {
            return null;
        }
        return new String(m_endDate);
    }

    public int GetNumber() 
    {
        return m_number;
    }

    public Person GetOwner() 
    {
        if(m_owner == null)
        {
            return null;
        }
        return new Person(m_owner);
    }

    public void SetStateName(@NonNull String stateName)
    {
        m_stateName = String.copyValueOf(stateName.toCharArray());
    }

    public void SetCityName(@NonNull String cityName)
    {
        m_cityName = String.copyValueOf(cityName.toCharArray());
    }

    public void SetStreetName(@NonNull String streetName)
    {
        m_streetName = String.copyValueOf(streetName.toCharArray());
    }

    public void SetNumber(int number)
    {
        m_number = number;
    }

    public void SetOwner(@NonNull Person owner)
    {
        if(m_owner == null)
        {
            m_owner = new Person(owner);
            return;
        }
        m_owner.CopyValuesOf(owner);
    }
    
    public void CopyAttributesOf(@NonNull Warehouse toCopy)
    {
        m_id = toCopy.GetId();
        if(toCopy.m_stateName != null)
        {
            m_stateName = toCopy.GetStateName();
        }
        if(toCopy.m_cityName != null)
        {
            m_streetName = toCopy.GetStreetName();
        }
        if(toCopy.m_streetName != null)
        {
            m_streetName = toCopy.GetStreetName();
        }
        m_number = toCopy.GetNumber();
        if(toCopy.m_owner != null)
        {
            m_owner = toCopy.GetOwner();
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == null)
        {
            return false;
        }

        if(o == this)
        {
            return true;
        }

        if(o.getClass() != Warehouse.class)
        {
            return false;
        }

        Warehouse casted = (Warehouse) o;

        return casted.GetId().equals(m_id);
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("Warehouse ID: "+m_id+"\n");
        stringBuilder.append("State name: "+m_stateName+"\n");
        stringBuilder.append("City name: "+m_cityName+"\n");
        stringBuilder.append("Street name: "+m_streetName+"\n");
        stringBuilder.append("Number: "+m_number+"\n");
        stringBuilder.append("Begin date: "+m_beginDate+"\n");
        stringBuilder.append("End date: "+m_endDate+"\n");
        stringBuilder.append("Owner: \n"+m_owner+"\n");

        return stringBuilder.toString();
    }
}
