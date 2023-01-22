package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.CRUDBatch;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;

public class BatchTabletest extends AppCompatActivity {

	private TableLayout stk;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teste);

		init();
	}

	public void init(){
		stk = (TableLayout) findViewById(R.id.table_main);
		TableRow tbrow0 = new TableRow(this);

		TextView tv0 = new TextView(this);
		tv0.setText(" Id ");
		tv0.setTextColor(Color.BLUE);
		tv0.setTextSize(30);
		tbrow0.addView(tv0);

		TextView tv1 = new TextView(this);
		tv1.setText(" Creation Date ");
		tv1.setTextColor(Color.BLUE);
		tv1.setTextSize(30);
		tbrow0.addView(tv1);

		stk.addView(tbrow0);

		//List/Array com classe

			SQLiteDAO db = new SQLiteDAO(this);
			DatabaseAccess.batchOperationsInterface = db;
			Batch[] teste = CRUDBatch.GetBatches();
			/*Batch[] teste = new Batch[5];
			teste[0] = new Batch("1", "18/01/2023");
			teste[1] = new Batch("2", "17/01/2023");
			teste[2] = new Batch("3", "16/01/2023");
			teste[3] = new Batch("5", "20/01/2023");
			teste[4] = new Batch("9", "02/01/2023");
			//Batch[] teste = TableRowGenerator.getTableRowGeneratorBatch();*/
		
			if(teste != null){
				for(int i = 0; i < teste.length; i++){
					TableRow tbrow = new TableRow(this);

					TextView t1v = new TextView(this);
					t1v.setText(teste[i].GetId());
					t1v.setTextColor(Color.BLACK);
					t1v.setGravity(Gravity.CENTER);
					t1v.setTextSize(18);
					tbrow.addView(t1v);

					TextView t2v = new TextView(this);
					t2v.setText(teste[i].GetCreationDate());
					t2v.setTextColor(Color.BLACK);
					t2v.setGravity(Gravity.CENTER);
					t2v.setTextSize(18);
					tbrow.addView(t2v);

					stk.addView(tbrow);
				}

			}

	}
}