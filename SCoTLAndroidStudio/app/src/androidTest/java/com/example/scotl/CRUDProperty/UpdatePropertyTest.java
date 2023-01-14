package com.example.scotl.CRUDProperty;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Property;

public class UpdatePropertyTest
{
    @Test
    public void ExecuteUpdatePropertyTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);

        DatabaseAccess.propertyOperationsInterface = database;

        Boolean result = DatabaseAccess.propertyOperationsInterface.TryUpdateProperty
                (
                    new Property
                            (
                                    "1112",
                                    "Vila das Ruas",
                                    "Estado das Artes",
                                    "Cidade dos Extremos",
                                    "Rua das Calamidades",
                                    18
                            )
                );

        MyLog.LogMessage(result.toString());
    }
}
