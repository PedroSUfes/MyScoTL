package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.CRUDBatch;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;

public class BatchListMenu extends AppCompatActivity {

	private TableLayout stk;
	private EditText id_batch_txt;
	private EditText creation_date_txt;
	private Button add_button;
	private Button list_button;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setContentView(R.layout.activity_batch_list_menu);

		id_batch_txt = findViewById(R.id.Id_Batch_text);
		creation_date_txt = findViewById(R.id.creation_Date_text);
		add_button = findViewById(R.id.add_button);
		list_button = findViewById(R.id.list_button);
		stk = (TableLayout) findViewById(R.id.table_list);

		Table();

		add_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Batch batch;

				try{
					batch = new Batch(id_batch_txt.getText().toString(), creation_date_txt.getText().toString());
					Toast.makeText(BatchListMenu.this, batch.toString(), Toast.LENGTH_SHORT).show();


					//Adicionando o novo Lote no banco

					SQLiteDAO database = new SQLiteDAO(BatchListMenu.this);
					DatabaseAccess.batchOperationsInterface = database;
					Boolean result = DatabaseAccess.batchOperationsInterface.TryRegisterBatch(batch);

					Toast.makeText(BatchListMenu.this, result.toString(), Toast.LENGTH_SHORT).show();

					Table();

				}catch(Exception e){
					Toast.makeText(BatchListMenu.this, "Erro ", Toast.LENGTH_SHORT).show();
				}

			}
		});

		list_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				stk.removeAllViews();
				Toast.makeText(BatchListMenu.this, "TESTE2", Toast.LENGTH_SHORT).show();
				Table();
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
		tv0.setTextColor(Color.BLACK);
		tv0.setTextSize(30);
		tbrow0.addView(tv0);

		TextView tv1 = new TextView(this);
		tv1.setText(" DATA DE CRIAÇÃO ");
		tv1.setTextColor(Color.BLACK);
		tv1.setTextSize(30);
		tbrow0.addView(tv1);

		stk.addView(tbrow0);

		//Teste para encher a tabela e verificar o scroll
		Batch[] teste = new Batch[34];
		for(int i = 0; i < teste.length; i++){
			teste[i] = new Batch(Integer.toString(i), "18/01/2023");
		}

		if(teste != null){
			for (Batch batch : batches) {
				TableRow tbrow = new TableRow(this);

				TextView t1v = new TextView(this);
				t1v.setText(batch.GetId());
				t1v.setTextColor(Color.parseColor("#FF765138"));
				t1v.setGravity(Gravity.CENTER);
				t1v.setTextSize(18);
				tbrow.addView(t1v);

				TextView t2v = new TextView(this);
				t2v.setText(batch.GetCreationDate());
				t2v.setTextColor(Color.parseColor("#FF765138"));
				t2v.setGravity(Gravity.CENTER);
				t2v.setTextSize(18);
				tbrow.addView(t2v);

				stk.addView(tbrow);
			}

		}

	}
}