package com.example.scotl.CRUDBatch;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;

public class GetBatchTest
{
	@Test
	public void ExecuteGetBatchTest()
	{
		Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
		SQLiteDAO database = new SQLiteDAO(appContext);
		DatabaseAccess.batchOperationsInterface = database;

		Batch result = DatabaseAccess.batchOperationsInterface.GetBatch("112");

		if(result != null){
			System.out.println("Id encontrado\n"+result);
		}
		else{
			System.out.println("Id não encontrado.");
		}

	}
}
