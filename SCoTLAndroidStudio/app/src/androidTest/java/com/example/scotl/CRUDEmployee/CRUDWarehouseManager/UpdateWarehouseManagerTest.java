package com.example.scotl.CRUDEmployee.CRUDWarehouseManager;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Person;
import Policy.Entity.Warehouse;
import Policy.Entity.WarehouseManager;

public class UpdateWarehouseManagerTest
{
    @Test
    public void ExecuteUpdateWarehouseManagerTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);

        DatabaseAccess.employeeOperationsInterface = database;

        Boolean result = DatabaseAccess.employeeOperationsInterface.TryUpdateWarehouseManager
                (
                    new WarehouseManager
                            (
                                    "1111",
                                    "Gerente 1",
                                    "0800",
                                    "11/08/1991",
                                    new Warehouse("1112")
                            ),
                        "Today"
                );

        MyLog.LogMessage(result.toString());
    }
}
