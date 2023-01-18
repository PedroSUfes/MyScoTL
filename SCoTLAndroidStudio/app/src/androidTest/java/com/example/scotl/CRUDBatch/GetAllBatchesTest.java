package com.example.scotl.CRUDBatch;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;

public class GetAllBatchesTest
{
	@Test
	public void ExecuteGetAllBatchesTest()
	{
		Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
		SQLiteDAO database = new SQLiteDAO(appContext);
		DatabaseAccess.batchOperationsInterface = database;

		Batch[] batches = DatabaseAccess.batchOperationsInterface.GetBatches();
		if(batches == null)
		{
			return;
		}
		MyLog.LogMessage("Tudo certo");
		for(Batch b : batches)
		{
			if(b == null)
			{
				continue;
			}

			MyLog.LogMessage(b.toString());
		}
	}
}
