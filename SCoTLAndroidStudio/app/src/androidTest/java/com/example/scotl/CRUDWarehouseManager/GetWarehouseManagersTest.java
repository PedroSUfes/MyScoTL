package com.example.scotl.CRUDWarehouseManager;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.WarehouseManager;

public class GetWarehouseManagersTest
{
    @Test
    public void ExecuteGetWarehouseManagersTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);

        DatabaseAccess.employeeOperationsInterface = database;

        WarehouseManager[] result = DatabaseAccess.employeeOperationsInterface.GetWarehouseManagers();
        if(result == null)
        {
            return;
        }

        for(WarehouseManager w : result)
        {
            if(w == null)
            {
                continue;
            }

            MyLog.LogMessage(w.toString());
        }
    }
}
