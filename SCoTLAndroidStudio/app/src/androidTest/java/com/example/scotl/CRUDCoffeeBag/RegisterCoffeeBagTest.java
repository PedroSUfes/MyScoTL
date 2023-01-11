package com.example.scotl.CRUDCoffeeBag;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.CoffeeBag;

public class RegisterCoffeeBagTest
{
    @Test
    public void Main()
    {
        // Registrar sacas no banco de dados
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        SQLiteDAO database = new SQLiteDAO(appContext);
//
//        DatabaseAccess.coffeeBagOperationsInterface = database;
//
//        DatabaseAccess.coffeeBagOperationsInterface.TryRegisterCoffeeBag(new CoffeeBag());
    }
}
