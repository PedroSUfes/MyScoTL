package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import Frameworks.Adapters.TableRowGenerator;
import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;

public class BatchListMenu extends AppCompatActivity {

	private TableLayout stk;
	private EditText date_batch_txt;
	private Button add_button;
	private Button list_button;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setContentView(R.layout.activity_batch_list_menu);

		date_batch_txt = findViewById(R.id.date_Batch_text);
		add_button = findViewById(R.id.add_button);
		list_button = findViewById(R.id.list_button);
		stk = (TableLayout) findViewById(R.id.table_list);

		Table();

		add_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				openActivityCadastrar();

			}
		});

		list_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				if(date_batch_txt.getText().toString().equals("")){
					stk.removeAllViews();
					Table();
				}
				else{
					String date = date_batch_txt.getText().toString();
					stk.removeAllViews();
					Table(date);
					date_batch_txt.setText("");

				}
			}
		});
	}

	public void Table(){

		stk.removeAllViews();

		SQLiteDAO database = new SQLiteDAO(BatchListMenu.this);
		DatabaseAccess.batchOperationsInterface = database;
		Batch[] batches = DatabaseAccess.batchOperationsInterface.GetBatches();

		TableRow tbrow0 = new TableRow(this);

		TextView tv0 = new TextView(this);
		tv0.setText(" ID ");
		tv0.setTextColor(Color.WHITE);
		tv0.setTextSize(30);
		tbrow0.addView(tv0);

		TextView tv1 = new TextView(this);
		tv1.setText(" DATA DE CRIAÇÃO ");
		tv1.setTextColor(Color.WHITE);
		tv1.setTextSize(30);
		tbrow0.addView(tv1);

		stk.addView(tbrow0);

		TableRowGenerator.setTableRowGeneratorBatch();




		if(batches != null){
			for (Batch batch : batches) {
				TableRow tbrow = new TableRow(this);

				TextView t1v = new TextView(this);
				t1v.setText(batch.GetId());
				t1v.setTextColor(Color.WHITE);
				t1v.setGravity(Gravity.CENTER);
				t1v.setTextSize(18);
				tbrow.addView(t1v);

				TextView t2v = new TextView(this);
				t2v.setText(batch.GetCreationDate());
				t2v.setTextColor(Color.WHITE);
				t2v.setGravity(Gravity.CENTER);
				t2v.setTextSize(18);
				tbrow.addView(t2v);

				stk.addView(tbrow);
			}

		}

	}

	public void Table(String date){

		stk.removeAllViews();

		SQLiteDAO database = new SQLiteDAO(BatchListMenu.this);
		DatabaseAccess.batchOperationsInterface = database;
		Batch[] batches = DatabaseAccess.batchOperationsInterface.GetBatches();


		TableRow tbrow0 = new TableRow(this);

		TextView tv0 = new TextView(this);
		tv0.setText(" ID ");
		tv0.setTextColor(Color.WHITE);
		tv0.setTextSize(30);
		tbrow0.addView(tv0);

		TextView tv1 = new TextView(this);
		tv1.setText(" DATA DE CRIAÇÃO ");
		tv1.setTextColor(Color.WHITE);
		tv1.setTextSize(30);
		tbrow0.addView(tv1);

		stk.addView(tbrow0);

		if(batches != null){
			for (Batch batch : batches) {
				if(batch.GetCreationDate().equals(date)) {
					TableRow tbrow = new TableRow(this);

					TextView t1v = new TextView(this);
					t1v.setText(batch.GetId());
					t1v.setTextColor(Color.WHITE);
					t1v.setGravity(Gravity.CENTER);
					t1v.setTextSize(18);
					tbrow.addView(t1v);

					TextView t2v = new TextView(this);
					t2v.setText(batch.GetCreationDate());
					t2v.setTextColor(Color.WHITE);
					t2v.setGravity(Gravity.CENTER);
					t2v.setTextSize(18);
					tbrow.addView(t2v);

					stk.addView(tbrow);
				}
			}

		}

	}

	public void openActivityCadastrar(){
		Intent intent = new Intent(this, BatchRegisterMenu.class);
		startActivity(intent);
	}
}