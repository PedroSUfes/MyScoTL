package com.example.scotl.CRUDWarehouse;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;

public class RemoveWarehouseTest
{
    @Test
    public void ExecuteRemoveWarehouseTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);
        DatabaseAccess.warehouseOperationsInterface = database;

        Boolean result = DatabaseAccess.warehouseOperationsInterface.TryRemoveWarehouse("1112");

        MyLog.LogMessage(result.toString());
    }
}
