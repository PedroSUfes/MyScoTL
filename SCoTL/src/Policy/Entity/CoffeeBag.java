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
        m_id = id;
        m_batch = batch;
        m_warehouse = warehouse;
        m_storageDate = storageDate;
    }

    public CoffeeBag(CoffeeBag toCopy)
    {
        m_id = String.copyValueOf(toCopy.m_id.toCharArray());
        m_batch = new Batch(toCopy.m_batch);
        m_warehouse = new Warehouse(toCopy.GetWarehouse());
        m_storageDate = String.copyValueOf(toCopy.m_storageDate.toCharArray());
    }

    public String GetId() 
    {
        return m_id;
    }

    public Batch GetBatch() 
    {
        return m_batch;
    }

    public Warehouse GetWarehouse() 
    {
        return m_warehouse;
    }

    public String GetStorageDate() 
    {
        return m_storageDate;
    }

    @Override
    public String toString()
    {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("Coffee bag ID: "+m_id+"\n");
        stringBuilder.append("Storage date: "+m_storageDate+"\n");
        stringBuilder.append(m_batch);
        stringBuilder.append(m_warehouse);

        return stringBuilder.toString();
    }
}
