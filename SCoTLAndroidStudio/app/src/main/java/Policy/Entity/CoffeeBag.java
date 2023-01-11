package Policy.Entity;

public class CoffeeBag 
{
    private String m_id = null;
    private Batch m_batch = null;
    private Warehouse m_warehouse = null;
    private String m_storageDate = null;
    
    public CoffeeBag
    (
        String id,
        Batch batch,
        Warehouse warehouse,
        String storageDate
    )
    {
        m_id = new String(id);
        m_batch = new Batch(batch);
        m_warehouse = new Warehouse(warehouse);
        m_storageDate = new String(storageDate);
    }

    public CoffeeBag(CoffeeBag toCopy)
    {
        m_id = new String(toCopy.m_id);
        m_batch = new Batch(toCopy.m_batch);
        m_warehouse = new Warehouse(toCopy.GetWarehouse());
        m_storageDate = new String(toCopy.m_storageDate);
    }

    public String GetId() 
    {
        return new String(m_id);
    }

    public Batch GetBatch() 
    {
        return new Batch(m_batch);
    }

    public Warehouse GetWarehouse() 
    {
        return new Warehouse(m_warehouse);
    }

    public String GetStorageDate() 
    {
        return new String(m_storageDate);
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Coffee bag ID: "+m_id+"\n");
        stringBuilder.append("Storage date: "+m_storageDate+"\n");
        stringBuilder.append(m_batch);
        stringBuilder.append(m_warehouse);

        return stringBuilder.toString();
    }
}
