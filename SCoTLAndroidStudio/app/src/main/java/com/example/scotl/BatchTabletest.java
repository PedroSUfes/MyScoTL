package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import Frameworks.Adapters.TableRowGenerator;
import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.CRUDBatch;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;

public class BatchTabletest extends AppCompatActivity {

	private TableLayout tableLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teste);

		init();
	}

	public void init(){
		TableLayout stk = (TableLayout) findViewById(R.id.table_main);
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

		TableRowGenerator tableRowGenerator = new TableRowGenerator();


		//stk.addView(tbrow);


	}
}