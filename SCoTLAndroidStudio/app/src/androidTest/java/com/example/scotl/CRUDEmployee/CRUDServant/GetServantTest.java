package com.example.scotl.CRUDEmployee.CRUDServant;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Servant;

public class GetServantTest
{
    @Test
    public void ExecuteGetServantTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);

        DatabaseAccess.employeeOperationsInterface = database;

        Servant[] servantArray = DatabaseAccess.employeeOperationsInterface.GetServant("1111", false);
        if(servantArray == null)
        {
            return;
        }

        for(Servant s : servantArray)
        {
            if(s == null)
            {
                continue;
            }

            MyLog.LogMessage(s.toString());
        }
    }
}
