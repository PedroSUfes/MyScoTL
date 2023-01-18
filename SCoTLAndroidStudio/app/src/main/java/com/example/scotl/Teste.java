package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Teste extends AppCompatActivity {

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
		tv0.setTextColor(Color.WHITE);
		tbrow0.addView(tv0);

		TextView tv1 = new TextView(this);
		tv1.setText(" Creation Date ");
		tv1.setTextColor(Color.WHITE);
		tbrow0.addView(tv1);

		stk.addView(tbrow0);

		for(int i = 0; i < 50; i++){
			TableRow tbrow = new TableRow(this);

			TextView t1v = new TextView(this);
			t1v.setText("" + i);
			t1v.setTextColor(Color.WHITE);
			t1v.setGravity(Gravity.CENTER);
			tbrow.addView(t1v);

			TextView t2v = new TextView(this);
			t2v.setText("Product " + i);
			t2v.setTextColor(Color.WHITE);
			t2v.setGravity(Gravity.CENTER);
			tbrow.addView(t2v);

			stk.addView(tbrow);
		}
	}
}