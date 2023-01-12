package Policy.Entity;

public class WarehouseManager 
extends
    Employee
{
    private Warehouse m_warehouse = null;
    private String m_hiringDate = null;
    
    public WarehouseManager
    (
        String cpf, 
        String name, 
        String cellphone, 
        String birthDate, 
        String hiringDate,
        Warehouse warehouse
    ) 
    {
        super(cpf, name, cellphone, birthDate, hiringDate);
    
        m_warehouse = new Warehouse(warehouse);
        m_hiringDate = new String(hiringDate);
    }
    
    public WarehouseManager(WarehouseManager toCopy)
    {
        super(toCopy);

        m_warehouse = new Warehouse(toCopy.m_warehouse);
        m_hiringDate = new String(toCopy.GetHiringDate());
    }

    public Warehouse GetWarehouse()
    {
        return new Warehouse(m_warehouse);
    }

    @Override
    public EmployeeType GetEmployeeType()
    {
        return EmployeeType.WAREHOUSE_MANAGER;
    }

    public void SetWarehouse(Warehouse warehouse)
    {
        m_warehouse.CopyAttributesOf(warehouse);
    }

    public void CopyValuesOf(WarehouseManager toCopy)
    {
        super.CopyValuesOf(toCopy);

        m_warehouse = toCopy.GetWarehouse();
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString()+"\n");
        stringBuilder.append("Job: "+GetEmployeeType().toString()+"\n");
        stringBuilder.append("Manages warehouse: "+m_warehouse+"\n");
        stringBuilder.append("----------------------------------------------------\n");

        return stringBuilder.toString();
    }
}
