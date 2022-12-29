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

    public String GetId() 
    {
        return m_id;
    }

    public Batch GetBatch() 
    {
        return m_batch;
    }

    public Warehouse GEtWarehouse() 
    {
        return m_warehouse;
    }

    public String GetStorageDate() 
    {
        return m_storageDate;
    }
}
