package com.example.scotl.CRUDServant;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Property;
import Policy.Entity.Servant;

public class RegisterServantTest
{
    @Test
    public void ExecuteRegisterServantTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);

        DatabaseAccess.propertyOperationsInterface = database;
        DatabaseAccess.employeeOperationsInterface = database;

        boolean result = DatabaseAccess.employeeOperationsInterface.TryRegisterServant
        (
            new Servant
            (
                "1111",
                "Pedro",
                "0800",
                "20/06/2001",
                "Today",
                DatabaseAccess.propertyOperationsInterface.GetPropertyById("1111")
//                new Property("333")
            )
        );

        System.out.println(result);
    }
}
