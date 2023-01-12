package com.example.scotl.CRUDBatch;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;

public class RegisterBatchTest
{
    @Test
    public void ExecuteRegisterBatchTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);
        DatabaseAccess.batchOperationsInterface = database;

        boolean result = DatabaseAccess.batchOperationsInterface.TryRegisterBatch
        (
            new Batch
            (
                "1112",
                "Today"
            )
        );

        System.out.println(result);
    }
}
