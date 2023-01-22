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

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;
import Policy.Entity.CoffeeBag;
import Policy.Entity.Warehouse;

public class CoffeeBagMenu extends AppCompatActivity {

	private TableLayout stk;
	private EditText id_coffeeBag_txt;
	private EditText id_batch_txt;
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
		add_button = findViewById(R.id.add_button);
		list_button = findViewById(R.id.list_button);
		stk = (TableLayout) findViewById(R.id.table_list);

		SQLiteDAO database = new SQLiteDAO(CoffeeBagMenu.this);
		DatabaseAccess.coffeeBagOperationsInterface = database;
		DatabaseAccess.warehouseOperationsInterface = database;
		DatabaseAccess.batchOperationsInterface = database;

		Table();

		add_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				openActivityCoffeBagRegister();
			}
		});

		list_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				String id_coffeBag = id_coffeeBag_txt.getText().toString();
				String id_batch = id_batch_txt.getText().toString();
				String id_warehouse = id_warehouse_txt.getText().toString();

				if(id_coffeBag.length() > 0 && id_batch.length() > 0 && id_warehouse.length() > 0){
					Toast.makeText(CoffeeBagMenu.this, "Só é possivel filtrar por 1 campo", Toast.LENGTH_SHORT).show();
				}
				else if(id_coffeBag.length() > 0 && id_batch.length() > 0){
					Toast.makeText(CoffeeBagMenu.this, "Só é possivel filtrar por 1 campo", Toast.LENGTH_SHORT).show();

				}
				else if(id_batch.length() > 0 && id_warehouse.length() > 0){
					Toast.makeText(CoffeeBagMenu.this, "Só é possivel filtrar por 1 campo", Toast.LENGTH_SHORT).show();

				}
				else if(id_coffeBag.length() > 0 && id_warehouse.length() > 0){
					Toast.makeText(CoffeeBagMenu.this, "Só é possivel filtrar por 1 campo", Toast.LENGTH_SHORT).show();

				}
				else if(id_coffeBag.length() > 0){
					TableSortByCoffee(id_coffeBag);
					id_coffeeBag_txt.setText("");
				}
				else if(id_batch.length() > 0){
					TableSortByBatch(id_batch);
					id_batch_txt.setText("");
				}
				else if(id_warehouse.length() > 0){
					TableSortByWarehouse(id_warehouse);
					id_warehouse_txt.setText("");
				}
				else{
					Table();
				}
			}
		});
	}

	public void Table(){
		stk.removeAllViews();
		CoffeeBag[] coffeeBags = DatabaseAccess.coffeeBagOperationsInterface.GetCoffeeBags();


		TableRow tbrow0 = new TableRow(this);

		TextView tv0 = new TextView(this);
		tv0.setText(" ID SACA ");
		tv0.setTextColor(Color.WHITE);
		tv0.setTextSize(16);
		tbrow0.addView(tv0);

		TextView tv1 = new TextView(this);
		tv1.setText(" ID LOTE ");
		tv1.setTextColor(Color.WHITE);
		tv1.setTextSize(16);
		tbrow0.addView(tv1);

		TextView tv2 = new TextView(this);
		tv2.setText(" ID GALPÃO ");
		tv2.setTextColor(Color.WHITE);
		tv2.setTextSize(16);
		tbrow0.addView(tv2);

		TextView tv3 = new TextView(this);
		tv3.setText(" DATA ARMAZENADO");
		tv3.setTextColor(Color.WHITE);
		tv3.setTextSize(16);
		tbrow0.addView(tv3);

		stk.addView(tbrow0);

		if(coffeeBags != null){
			for (CoffeeBag coffeeBag : coffeeBags) {
				TableRow tbrow = new TableRow(this);

				TextView t1v = new TextView(this);
				t1v.setText(coffeeBag.GetId());
				t1v.setTextColor(Color.WHITE);
				t1v.setGravity(Gravity.CENTER);
				t1v.setTextSize(18);
				tbrow.addView(t1v);

				TextView t2v = new TextView(this);
				t2v.setText(coffeeBag.GetBatch().GetId());
				t2v.setTextColor(Color.WHITE);
				t2v.setGravity(Gravity.CENTER);
				t2v.setTextSize(18);
				tbrow.addView(t2v);

				TextView t3v = new TextView(this);
				t3v.setText(coffeeBag.GetWarehouse().GetId());
				t3v.setTextColor(Color.WHITE);
				t3v.setGravity(Gravity.CENTER);
				t3v.setTextSize(18);
				tbrow.addView(t3v);

				TextView t4v = new TextView(this);
				t4v.setText(coffeeBag.GetStorageDate());
				t4v.setTextColor(Color.WHITE);
				t4v.setGravity(Gravity.CENTER);
				t4v.setTextSize(18);
				tbrow.addView(t4v);

				stk.addView(tbrow);
			}

		}
	}

	public void TableSortByCoffee(String coffeeID){
		stk.removeAllViews();
		CoffeeBag[] coffeeBags = DatabaseAccess.coffeeBagOperationsInterface.GetCoffeeBags();


		TableRow tbrow0 = new TableRow(this);

		TextView tv0 = new TextView(this);
		tv0.setText(" ID SACA ");
		tv0.setTextColor(Color.WHITE);
		tv0.setTextSize(16);
		tbrow0.addView(tv0);

		TextView tv1 = new TextView(this);
		tv1.setText(" ID LOTE ");
		tv1.setTextColor(Color.WHITE);
		tv1.setTextSize(16);
		tbrow0.addView(tv1);

		TextView tv2 = new TextView(this);
		tv2.setText(" ID GALPÃO ");
		tv2.setTextColor(Color.WHITE);
		tv2.setTextSize(16);
		tbrow0.addView(tv2);

		TextView tv3 = new TextView(this);
		tv3.setText(" DATA ARMAZENADO");
		tv3.setTextColor(Color.WHITE);
		tv3.setTextSize(16);
		tbrow0.addView(tv3);

		stk.addView(tbrow0);

		if(coffeeBags != null){
			for (CoffeeBag coffeeBag : coffeeBags) {

				if(coffeeBag.GetId().equals(coffeeID)){
					TableRow tbrow = new TableRow(this);

					TextView t1v = new TextView(this);
					t1v.setText(coffeeBag.GetId());
					t1v.setTextColor(Color.WHITE);
					t1v.setGravity(Gravity.CENTER);
					t1v.setTextSize(18);
					tbrow.addView(t1v);

					TextView t2v = new TextView(this);
					t2v.setText(coffeeBag.GetBatch().GetId());
					t2v.setTextColor(Color.WHITE);
					t2v.setGravity(Gravity.CENTER);
					t2v.setTextSize(18);
					tbrow.addView(t2v);

					TextView t3v = new TextView(this);
					t3v.setText(coffeeBag.GetWarehouse().GetId());
					t3v.setTextColor(Color.WHITE);
					t3v.setGravity(Gravity.CENTER);
					t3v.setTextSize(18);
					tbrow.addView(t3v);

					TextView t4v = new TextView(this);
					t4v.setText(coffeeBag.GetStorageDate());
					t4v.setTextColor(Color.WHITE);
					t4v.setGravity(Gravity.CENTER);
					t4v.setTextSize(18);
					tbrow.addView(t4v);

					stk.addView(tbrow);
				}
			}
		}
	}

	public void TableSortByBatch(String batchID){
		stk.removeAllViews();
		CoffeeBag[] coffeeBags = DatabaseAccess.coffeeBagOperationsInterface.GetCoffeeBags();


		TableRow tbrow0 = new TableRow(this);

		TextView tv0 = new TextView(this);
		tv0.setText(" ID SACA ");
		tv0.setTextColor(Color.WHITE);
		tv0.setTextSize(16);
		tbrow0.addView(tv0);

		TextView tv1 = new TextView(this);
		tv1.setText(" ID LOTE ");
		tv1.setTextColor(Color.WHITE);
		tv1.setTextSize(16);
		tbrow0.addView(tv1);

		TextView tv2 = new TextView(this);
		tv2.setText(" ID GALPÃO ");
		tv2.setTextColor(Color.WHITE);
		tv2.setTextSize(16);
		tbrow0.addView(tv2);

		TextView tv3 = new TextView(this);
		tv3.setText(" DATA ARMAZENADO");
		tv3.setTextColor(Color.WHITE);
		tv3.setTextSize(16);
		tbrow0.addView(tv3);

		stk.addView(tbrow0);

		if(coffeeBags != null){
			for (CoffeeBag coffeeBag : coffeeBags) {

				if(coffeeBag.GetBatch().GetId().equals(batchID)){
					TableRow tbrow = new TableRow(this);

					TextView t1v = new TextView(this);
					t1v.setText(coffeeBag.GetId());
					t1v.setTextColor(Color.WHITE);
					t1v.setGravity(Gravity.CENTER);
					t1v.setTextSize(18);
					tbrow.addView(t1v);

					TextView t2v = new TextView(this);
					t2v.setText(coffeeBag.GetBatch().GetId());
					t2v.setTextColor(Color.WHITE);
					t2v.setGravity(Gravity.CENTER);
					t2v.setTextSize(18);
					tbrow.addView(t2v);

					TextView t3v = new TextView(this);
					t3v.setText(coffeeBag.GetWarehouse().GetId());
					t3v.setTextColor(Color.WHITE);
					t3v.setGravity(Gravity.CENTER);
					t3v.setTextSize(18);
					tbrow.addView(t3v);

					TextView t4v = new TextView(this);
					t4v.setText(coffeeBag.GetStorageDate());
					t4v.setTextColor(Color.WHITE);
					t4v.setGravity(Gravity.CENTER);
					t4v.setTextSize(18);
					tbrow.addView(t4v);

					stk.addView(tbrow);
				}
			}
		}
	}

	public void TableSortByWarehouse(String warehouseID){
		stk.removeAllViews();
		CoffeeBag[] coffeeBags = DatabaseAccess.coffeeBagOperationsInterface.GetCoffeeBags();


		TableRow tbrow0 = new TableRow(this);

		TextView tv0 = new TextView(this);
		tv0.setText(" ID SACA ");
		tv0.setTextColor(Color.WHITE);
		tv0.setTextSize(16);
		tbrow0.addView(tv0);

		TextView tv1 = new TextView(this);
		tv1.setText(" ID LOTE ");
		tv1.setTextColor(Color.WHITE);
		tv1.setTextSize(16);
		tbrow0.addView(tv1);

		TextView tv2 = new TextView(this);
		tv2.setText(" ID GALPÃO ");
		tv2.setTextColor(Color.WHITE);
		tv2.setTextSize(16);
		tbrow0.addView(tv2);

		TextView tv3 = new TextView(this);
		tv3.setText(" DATA ARMAZENADO");
		tv3.setTextColor(Color.WHITE);
		tv3.setTextSize(16);
		tbrow0.addView(tv3);

		stk.addView(tbrow0);

		if(coffeeBags != null){
			for (CoffeeBag coffeeBag : coffeeBags) {
				if(coffeeBag.GetWarehouse().GetId().equals(warehouseID)){
					TableRow tbrow = new TableRow(this);

					TextView t1v = new TextView(this);
					t1v.setText(coffeeBag.GetId());
					t1v.setTextColor(Color.WHITE);
					t1v.setGravity(Gravity.CENTER);
					t1v.setTextSize(18);
					tbrow.addView(t1v);

					TextView t2v = new TextView(this);
					t2v.setText(coffeeBag.GetBatch().GetId());
					t2v.setTextColor(Color.WHITE);
					t2v.setGravity(Gravity.CENTER);
					t2v.setTextSize(18);
					tbrow.addView(t2v);

					TextView t3v = new TextView(this);
					t3v.setText(coffeeBag.GetWarehouse().GetId());
					t3v.setTextColor(Color.WHITE);
					t3v.setGravity(Gravity.CENTER);
					t3v.setTextSize(18);
					tbrow.addView(t3v);

					TextView t4v = new TextView(this);
					t4v.setText(coffeeBag.GetStorageDate());
					t4v.setTextColor(Color.WHITE);
					t4v.setGravity(Gravity.CENTER);
					t4v.setTextSize(18);
					tbrow.addView(t4v);

					stk.addView(tbrow);
				}
			}
		}
	}

	public void openActivityCoffeBagRegister(){
		Intent intent = new Intent(this, CoffeeBagRegisterMenu.class);
		startActivity(intent);
	}


}