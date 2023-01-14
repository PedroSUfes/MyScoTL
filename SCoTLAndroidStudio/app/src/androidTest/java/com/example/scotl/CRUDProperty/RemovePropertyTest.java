package com.example.scotl.CRUDProperty;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.ManageWarehouseTableQueryHelper;
import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;

public class RemovePropertyTest
{
    @Test
    public void ExecuteRemovePropertyTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);

        DatabaseAccess.propertyOperationsInterface = database;

        Boolean result = DatabaseAccess.propertyOperationsInterface.TryRemoveProperty("1111");

        MyLog.LogMessage(result.toString());
    }
}
