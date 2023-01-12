package com.example.scotl.CRUDWarehouse;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Person;
import Policy.Entity.Warehouse;

public class RegisterWarehouseTest
{
    @Test
    public void ExecuteRegisterWarehouseTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);

        DatabaseAccess.warehouseOperationsInterface = database;

        boolean result = DatabaseAccess.warehouseOperationsInterface.TryRegisterWarehouse
        (
            new Warehouse
            (
                "1111",
                "Estado dos Estados",
                "Cidade das Cidades",
                "Rua das Ruas",
                12,
                new Person
                (
                    "1211",
                    "Fulano",
                    "0800",
                    "Today"
                )
            ),
            "Today"
        );

        System.out.println(result);
    }
}
