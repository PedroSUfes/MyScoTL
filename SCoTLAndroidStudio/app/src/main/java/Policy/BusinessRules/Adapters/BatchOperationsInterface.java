package Policy.BusinessRules.Adapters;

import java.util.List;

import Policy.Entity.Batch;

public interface BatchOperationsInterface 
{
    public Batch[] GetBatches();
    public Batch GetBatch(String batchId);
    public Boolean TryRegisterBatch(Batch batch);
    public Boolean TryRemoveBatch(String batchId);
}
