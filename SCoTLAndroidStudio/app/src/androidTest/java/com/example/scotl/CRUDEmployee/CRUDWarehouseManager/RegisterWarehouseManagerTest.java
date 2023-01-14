package com.example.scotl.CRUDEmployee.CRUDWarehouseManager;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Warehouse;
import Policy.Entity.WarehouseManager;

public class RegisterWarehouseManagerTest
{
    @Test
    public void Main()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);
        DatabaseAccess.employeeOperationsInterface = database;
        DatabaseAccess.warehouseOperationsInterface = database;

        Boolean result = DatabaseAccess.employeeOperationsInterface.TryRegisterWarehouseManager
        (
            new WarehouseManager
            (
                "2222",
                "Fulano Gerente",
                "0800",
                "Today",
                 "Tomorrow",
                 null,
                 new Warehouse("1116")
            )
        );

        MyLog.LogMessage(result.toString());
    }
}
