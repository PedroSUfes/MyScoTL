package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import Frameworks.Adapters.TableRowGenerator;

public class BatchTable_Test extends AppCompatActivity {

	TableLayout stk;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teste);

		TableInit();

	}

	public void TableInit(){
		stk = (TableLayout) findViewById(R.id.table_main);

		//-------- Table Header ----------

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

		//---------- Table Rows -----------

		/*
		* Fazer a chamada da função de GetAll
		* Guardar o retorno em um Array
		* setar TableRowGenerator com esse array
		* Guardar o retorno de TableRowGenerator
		* e fazer um for para percorrer o array de tableRows fazendo a inserção
		*
		* for(int i = 0; i < tbrows.length; i++){
		*     stk.addView(tbrows[i]);
		* }
		* */


	}
}