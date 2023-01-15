package com.example.scotl.CRUDEmployee.CRUDWarehouseManager;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.WarehouseManager;

public class GetWarehouseManagerWithCpfTest
{
    @Test
    public void ExecuteGetWarehouseManagerWithCpfTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);
        DatabaseAccess.employeeOperationsInterface = database;

        WarehouseManager[] result = DatabaseAccess.employeeOperationsInterface.GetWarehouseManager("1111", false);

        if(result == null)
        {
            return;
        }

        for (WarehouseManager r : result)
        {
            if(r == null)
            {
                continue;
            }

            MyLog.LogMessage(r.toString());
        }
    }
}
