package com.example.scotl.CRUDServant;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Property;
import Policy.Entity.Servant;

public class UpdateServantTest
{
    @Test
    public void ExecuteUpdateServantTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);

        DatabaseAccess.employeeOperationsInterface = database;

        Servant toUpdate = new Servant
                (
                        "1111",
                        "Pedro Santos Moreira",
                        "+55 (27) 992250622",
                        "20/06/200",
                        new Property("1113")
                );
        Boolean result = DatabaseAccess.employeeOperationsInterface.TryUpdateServant
                (
                        toUpdate,
                        "Today"
                );

        MyLog.LogMessage(result.toString());
    }
}
