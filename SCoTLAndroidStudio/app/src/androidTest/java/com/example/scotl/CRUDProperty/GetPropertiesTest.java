package com.example.scotl.CRUDProperty;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Property;

public class GetPropertiesTest
{
    @Test
    public void ExecuteGetPropertiesTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);
        DatabaseAccess.propertyOperationsInterface = database;

        Property[] result = DatabaseAccess.propertyOperationsInterface.GetProperties();
        if(result == null)
        {
            return;
        }

        for (Property p : result)
        {
            if(p == null)
            {
                continue;
            }

            MyLog.LogMessage(p.toString());
        }
    }
}
