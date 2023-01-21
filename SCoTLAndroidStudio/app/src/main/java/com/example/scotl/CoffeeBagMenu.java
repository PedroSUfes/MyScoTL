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

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;
import Policy.Entity.CoffeeBag;
import Policy.Entity.Warehouse;

public class CoffeeBagMenu extends AppCompatActivity {

	private TableLayout stk;
	private EditText id_coffeeBag_txt;
	private EditText id_batch_txt;
	private EditText storage_date_txt;
	private EditText id_warehouse_txt;
	private Button add_button;
	private Button list_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_coffee_bag_menu);

		id_coffeeBag_txt = findViewById(R.id.Id_coffebag_text);
		id_batch_txt = findViewById(R.id.id_batch_text);
		id_warehouse_txt = findViewById(R.id.id_warehouse_text);
		storage_date_txt = findViewById(R.id.storage_Date_text);
		add_button = findViewById(R.id.add_button);
		list_button = findViewById(R.id.list_button);
		stk = (TableLayout) findViewById(R.id.table_list);

		Table();

		add_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});

		list_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});
	}

	public void Table(){
		stk.removeAllViews();

		SQLiteDAO database = new SQLiteDAO(CoffeeBagMenu.this);
		DatabaseAccess.coffeeBagOperationsInterface = database;
		CoffeeBag[] coffeeBags = DatabaseAccess.coffeeBagOperationsInterface.GetCoffeeBags();


		TableRow tbrow0 = new TableRow(this);

		TextView tv0 = new TextView(this);
		tv0.setText(" ID SACA ");
		tv0.setTextColor(Color.BLACK);
		tv0.setTextSize(20);
		tbrow0.addView(tv0);

		TextView tv1 = new TextView(this);
		tv1.setText(" ID LOTE ");
		tv1.setTextColor(Color.BLACK);
		tv1.setTextSize(20);
		tbrow0.addView(tv1);

		TextView tv2 = new TextView(this);
		tv2.setText(" ID GALPÃO ");
		tv2.setTextColor(Color.BLACK);
		tv2.setTextSize(20);
		tbrow0.addView(tv2);

		TextView tv3 = new TextView(this);
		tv3.setText(" Dt. DE ARMAZENAMENTO ");
		tv3.setTextColor(Color.BLACK);
		tv3.setTextSize(20);
		tbrow0.addView(tv3);

		stk.addView(tbrow0);

		//Teste para encher a tabela e verificar o scroll
		CoffeeBag[] teste = new CoffeeBag[34];
		for(int i = 0; i < teste.length; i++){
			teste[i] = new CoffeeBag(Integer.toString(i), new Batch("1", "20/12/2020"), new Warehouse("3"), "21/01/2023");
		}

		if(coffeeBags != null){
			for (CoffeeBag coffeeBag : coffeeBags) {
				TableRow tbrow = new TableRow(this);

				TextView t1v = new TextView(this);
				t1v.setText(coffeeBag.GetId());
				t1v.setTextColor(Color.parseColor("#FF765138"));
				t1v.setGravity(Gravity.CENTER);
				t1v.setTextSize(18);
				tbrow.addView(t1v);

				TextView t2v = new TextView(this);
				t2v.setText(coffeeBag.GetBatch().GetId());
				t2v.setTextColor(Color.parseColor("#FF765138"));
				t2v.setGravity(Gravity.CENTER);
				t2v.setTextSize(18);
				tbrow.addView(t2v);

				TextView t3v = new TextView(this);
				t3v.setText(coffeeBag.GetWarehouse().GetId());
				t3v.setTextColor(Color.parseColor("#FF765138"));
				t3v.setGravity(Gravity.CENTER);
				t3v.setTextSize(18);
				tbrow.addView(t3v);

				TextView t4v = new TextView(this);
				t4v.setText(coffeeBag.GetStorageDate());
				t4v.setTextColor(Color.parseColor("#FF765138"));
				t4v.setGravity(Gravity.CENTER);
				t4v.setTextSize(18);
				tbrow.addView(t4v);

				stk.addView(tbrow);
			}

		}
	}


}