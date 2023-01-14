package com.example.scotl.CRUDEmployee.CRUDWarehouse;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Warehouse;

public class GetWarehousesTest
{
    @Test
    public void ExecuteGetWarehousesTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);

        DatabaseAccess.warehouseOperationsInterface = database;

        Warehouse[] warehouses = DatabaseAccess.warehouseOperationsInterface.GetWarehouses();
        if(warehouses == null)
        {
            return;
        }
        for(Warehouse w : warehouses)
        {
            if(w == null)
            {
                continue;
            }

            MyLog.LogMessage(w.toString());
        }
    }
}
