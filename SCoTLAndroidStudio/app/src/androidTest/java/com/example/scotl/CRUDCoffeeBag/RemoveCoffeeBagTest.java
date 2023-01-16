package com.example.scotl.CRUDCoffeeBag;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;

public class RemoveCoffeeBagTest
{
    @Test
    public void ExecuteRemoveCoffeeBagTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);
        DatabaseAccess.coffeeBagOperationsInterface = database;

        Boolean result = DatabaseAccess.coffeeBagOperationsInterface.TryRemoveCoffeeBag("1112", "1111");
        MyLog.LogMessage(result.toString());
    }
}
