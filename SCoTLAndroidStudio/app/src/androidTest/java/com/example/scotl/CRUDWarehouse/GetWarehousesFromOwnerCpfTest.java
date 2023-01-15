package com.example.scotl.CRUDWarehouse;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Warehouse;

public class GetWarehousesFromOwnerCpfTest
{
    @Test
    public void ExecuteGetWarehousesFromOwnerCpfTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);
        DatabaseAccess.warehouseOperationsInterface = database;

        Warehouse[] result = DatabaseAccess.warehouseOperationsInterface.GetWarehousesByOwnerCpf
                (
                        "22222222222",
                        false
                );

        if(result == null)
        {
            return;
        }

        for(Warehouse w : result)
        {
            if(w == null)
            {
                continue;
            }

            MyLog.LogMessage(w.toString());
        }
    }
}
