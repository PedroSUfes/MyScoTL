package Policy.BusinessRules;

import java.util.List;

import Policy.Entity.Batch;

public class CRUDBatch 
{
    public static List<Batch> GetBatches()
    {
        return DatabaseAccess.batchOperationsInterface.GetBatches();
    }

    public static Batch GetBatch(String batchId)
    {
        return DatabaseAccess.batchOperationsInterface.GetBatch(batchId);
    }

    public static Boolean TryRegisterBatch(Batch batch)
    {
        return DatabaseAccess.batchOperationsInterface.TryRegisterBatch(batch);
    }

    public static Boolean TryRemoveBatch(String batchId)
    {
        return DatabaseAccess.batchOperationsInterface.TryRemoveBatch(batchId);
    }
}
