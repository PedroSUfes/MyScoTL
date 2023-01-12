package com.example.scotl.CRUDBatch;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;

public class GetBatchesTest {

    @Test
    public void test() {

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);

        DatabaseAccess.batchOperationsInterface = database;

        DatabaseAccess.batchOperationsInterface.GetBatches();
    }
}
