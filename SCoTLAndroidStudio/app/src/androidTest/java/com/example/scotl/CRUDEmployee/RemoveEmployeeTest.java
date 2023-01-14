package com.example.scotl.CRUDEmployee;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;

public class RemoveEmployeeTest
{
    @Test
    public void ExecuteRemoveEmployeeTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);

        DatabaseAccess.employeeOperationsInterface = database;

        Boolean result = DatabaseAccess.employeeOperationsInterface.TryRemoveEmployee("1115");

        MyLog.LogMessage(result.toString());
    }
}
