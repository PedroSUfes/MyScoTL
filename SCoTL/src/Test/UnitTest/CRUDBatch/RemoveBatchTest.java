package Test.UnitTest.CRUDBatch;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.CRUDBatch;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;

public class RemoveBatchTest 
{
    private static MyDatabase database = new MyDatabase();

    public static void main(String[] args)
    {
        DatabaseAccess.batchOperationsInterface = database;

        TryRegisterBatches();
        TryRemoveBatch("1111");
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
    }

    private static void TryRemoveBatch(String batchId)
    {
        if(CRUDBatch.TryRemoveBatch(batchId))
        {   
            System.out.println("Success");
        }

        // if(CRUDBatch.TryRemoveBatch(batchId))
        // {   
        //     System.out.println("Success");
        // }
    }
}
