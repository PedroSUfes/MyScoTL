package com.example.scotl.CRUDCoffeeBag;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.CoffeeBag;

public class SelectCoffeeBagTest
{
    @Test
    public void ExecuteSelectCoffeeBagTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);
        DatabaseAccess.coffeeBagOperationsInterface = database;

        CoffeeBag result = DatabaseAccess.coffeeBagOperationsInterface.GetCoffeeBag("1112", "2222");
        if(result == null)
        {
            return;
        }

        MyLog.LogMessage(result.toString());
    }
}
