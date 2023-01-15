package com.example.scotl.Login;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;

public class LoginTest
{
    @Test
    public void ExecuteLoginTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);
        DatabaseAccess.loginInterface = database;

        Boolean result = DatabaseAccess.loginInterface.TryLogin("Pedro", "123");
        MyLog.LogMessage(result.toString());
    }
}
