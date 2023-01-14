package com.example.scotl.CRUDEmployee.CRUDServant;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Servant;

public class GetAllServantsTest
{
    @Test
    public void ExecuteGetAllServantsTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);

        DatabaseAccess.employeeOperationsInterface = database;

        Servant[] servants = DatabaseAccess.employeeOperationsInterface.GetServants(true);
        if(servants == null)
        {
            return;
        }
        for(Servant s : servants)
        {
            if(s == null)
            {
                continue;
            }

            MyLog.LogMessage(s.toString());
        }
    }
}
