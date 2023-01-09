package Test.UnitTest.CRUDBatch;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.CRUDBatch;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;

public class ReadBatchTest 
{
    private static MyDatabase database = new MyDatabase();
    
    public static void main(String[] args)
    {
        DatabaseAccess.batchOperationsInterface = database;

        TryRegisterBatches();
        // GetBatches();
        GetBatchById("2222");
    }

    private static void TryRegisterBatches()
    {
        CRUDBatch.TryRegisterBatch
        (
            new Batch
            (
                "1111", 
                "Today"
            )
        );
        CRUDBatch.TryRegisterBatch
        (
            new Batch
            (
                "2222",
                "Tomorrow"
            )
        );
        CRUDBatch.TryRegisterBatch
        (
            new Batch
            (
                "3333",
                "Tomorrow"
            )
        );
    }

    private static void GetBatches()
    {
        var batches = DatabaseAccess.batchOperationsInterface.GetBatches();
        if(batches == null)
        {
            System.out.println("No batches");
            return;
        }

        for(var batch : batches)
        {
            if(batch == null)
            {
                continue;
            }

            System.out.println(batch);
        }
    }

    private static void GetBatchById(String id)
    {
        var batch = CRUDBatch.GetBatch(id);
        if(batch == null)
        {
            return;
        }

        System.out.println(batch);
    }
}
