package com.example.scotl.CRUDCoffeeBag;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;
import Policy.Entity.CoffeeBag;
import Policy.Entity.Warehouse;

public class RegisterCoffeeBagTest
{
    @Test
    public void Main()
    {
        // Registrar sacas no banco de dados
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SQLiteDAO database = new SQLiteDAO(appContext);
        DatabaseAccess.coffeeBagOperationsInterface = database;

        Boolean result = DatabaseAccess.coffeeBagOperationsInterface.TryRegisterCoffeeBag
                (
                        new CoffeeBag
                                (
                                        "2222",
                                        new Batch("1234", "22/01/2023"),
                                        new Warehouse
                                                (
                                                        "1113"
                                                ),
                                        "23/01/2023"
                                )
                );

        MyLog.LogMessage(result.toString());
    }
}
