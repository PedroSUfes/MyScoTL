package com.example.scotl.CRUDProperty;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Property;

public class GetPropertyFromIdTest
{
    @Test
    public void ExecuteGetPropertyFromIdTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);
        DatabaseAccess.propertyOperationsInterface = database;

        Property result = DatabaseAccess.propertyOperationsInterface.GetPropertyById("1111");
        if(result == null)
        {
            return;
        }

        MyLog.LogMessage(result.toString());
    }
}
