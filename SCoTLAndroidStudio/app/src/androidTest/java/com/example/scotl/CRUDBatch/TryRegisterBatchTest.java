package com.example.scotl.CRUDBatch;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;

public class TryRegisterBatchTest {

    @Test
    public void test() {

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);

        DatabaseAccess.batchOperationsInterface = database;

        DatabaseAccess.batchOperationsInterface.TryRegisterBatch(new Batch("2", "11/01/2023"));
    }
}
