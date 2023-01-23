package com.example.scotl.CRUDProperty;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Property;

public class ResgisterPropertyTest
{
    @Test
    public void ExecuteRegisterPropertyTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);

        DatabaseAccess.propertyOperationsInterface = database;

        boolean result = DatabaseAccess.propertyOperationsInterface.TryRegisterProperty
        (
            new Property
            (
                "2222",
                "Vila das Vilas",
                "Espirito Santo",
                "Vitoria",
                "Rua das Ruas",
                2
            )
        );

        System.out.println(result);
    }
}
