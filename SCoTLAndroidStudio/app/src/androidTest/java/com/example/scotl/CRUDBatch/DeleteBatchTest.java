package com.example.scotl.CRUDBatch;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;

public class DeleteBatchTest
{
	@Test
	public void ExecuteDeleteBatchTest()
	{
		Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
		SQLiteDAO database = new SQLiteDAO(appContext);
		DatabaseAccess.batchOperationsInterface = database;

		boolean result = DatabaseAccess.batchOperationsInterface.TryRemoveBatch("53");

		System.out.println(result);
	}
}
