package com.example.scotl.Login;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;

public class RemoveUserTest
{
    @Test
    public void ExecuteRemoveUserTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);

        Boolean result = database.TryRemoveUser("Pedro");
        MyLog.LogMessage(result.toString());
    }
}
