package com.example.scotl.CRUDCoffeeBag;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.CoffeeBag;

public class GetCoffeeBagsTest
{
    @Test
    public void ExecuteGetCoffeeBagsTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);
        DatabaseAccess.coffeeBagOperationsInterface = database;

        CoffeeBag[] result = DatabaseAccess.coffeeBagOperationsInterface.GetCoffeeBags();
        if(result == null)
        {
            return;
        }

        for(CoffeeBag c : result)
        {
            if(c == null)
            {
                continue;
            }

            MyLog.LogMessage(c.toString());
        }
    }
}
