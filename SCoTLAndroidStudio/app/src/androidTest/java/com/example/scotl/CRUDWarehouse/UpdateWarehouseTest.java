package com.example.scotl.CRUDWarehouse;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Person;
import Policy.Entity.Warehouse;

public class UpdateWarehouseTest
{
    @Test
    public void ExecuteUpdateWarehouseTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);

        DatabaseAccess.warehouseOperationsInterface = database;

        Boolean result = DatabaseAccess.warehouseOperationsInterface.TryUpdateWarehouse
                (
                        new Warehouse
                                (
                                        "1113",
                                        "Estados dos Vales",
                                        "Cidade das Avenidas",
                                        "Rua sem Saida",
                                        16,
                                        "Today",
                                        null,
                                        new Person
                                                (
                                                        "1112",
                                                        "Dono 2",
                                                        "0800",
                                                        "20/09/1988"
                                                )
                                )
                );

        MyLog.LogMessage(result.toString());
    }
}
