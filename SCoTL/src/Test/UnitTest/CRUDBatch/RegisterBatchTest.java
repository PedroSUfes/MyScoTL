package Test.UnitTest.CRUDBatch;

import Frameworks.Database.MyDatabase;
import Policy.BusinessRules.CRUDBatch;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;

public class RegisterBatchTest 
{
    private static MyDatabase database = new MyDatabase();

    public static void main(String[] args)
    {
        DatabaseAccess.batchOperationsInterface = database;
        TryRegisterBatches();
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
        CRUDBatch.TryRegisterBatch
        (
            new Batch
            (
                "3333",
                "Tomorrow"
            )
        );
    }
}
