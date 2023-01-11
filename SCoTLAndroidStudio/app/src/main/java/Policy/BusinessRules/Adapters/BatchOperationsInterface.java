package Policy.BusinessRules.Adapters;

import Policy.Entity.Batch;

public interface BatchOperationsInterface 
{
    public Batch[] GetBatches();
    public Batch GetBatch(String batchId);
    public Boolean TryRegisterBatch(Batch batch);
    public Boolean TryRemoveBatch(String batchId);
}
