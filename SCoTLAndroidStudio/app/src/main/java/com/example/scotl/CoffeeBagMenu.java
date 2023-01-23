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

import java.util.ArrayList;

import Frameworks.Adapters.TableRowGenerator;
import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.CRUDCoffeeBag;
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
	private Button remove_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_coffee_bag_menu);

		GetReferences();

		Table();

		add_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				openActivityCoffeBagRegister();
			}
		});

		remove_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				openActivityCoffeBagRemove();
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

		stk.removeViews(1, stk.getChildCount() - 1);

		CoffeeBag[] coffeeBags = CRUDCoffeeBag.GetCoffeeBags();

		if(coffeeBags != null){

			TableRow[] tableRows = TableRowGenerator.GetCoffeeBagTableRows(coffeeBags, this);

			if(tableRows == null){
				return;
			}

			for (TableRow tableRow : tableRows) {
				if(tableRow == null){
					continue;
				}

				stk.addView(tableRow);
			}
		}
	}

	public void TableSortByCoffee(String coffeeID){

		stk.removeViews(1, stk.getChildCount() - 1);

		CoffeeBag[] coffeeBags = CRUDCoffeeBag.GetCoffeeBags();

		ArrayList<CoffeeBag> coffeeBagTableRows = new ArrayList<CoffeeBag>();

		for(CoffeeBag c : coffeeBags){
			if(c == null){
				continue;
			}

			if(c.GetId().equals(coffeeID)){
				coffeeBagTableRows.add(c);
			}
		}

		CoffeeBag[] coffeeBagFilter = new CoffeeBag[coffeeBagTableRows.size()];
		coffeeBagTableRows.toArray(coffeeBagFilter);

		if(coffeeBagFilter != null){
			TableRow[] tableRows = TableRowGenerator.GetCoffeeBagTableRows(coffeeBagFilter, this);

			if(tableRows == null){
				return;
			}

			for(TableRow tablerow : tableRows){
				if(tablerow == null){
					continue;
				}

				stk.addView(tablerow);
			}
		}
	}

	public void TableSortByBatch(String batchID){

		stk.removeViews(1, stk.getChildCount() - 1);

		CoffeeBag[] coffeeBags = CRUDCoffeeBag.GetCoffeeBags();

		ArrayList<CoffeeBag> coffeeBagTableRows = new ArrayList<CoffeeBag>();

		for(CoffeeBag c : coffeeBags){
			if(c == null){
				continue;
			}

			if(c.GetBatch().GetId().equals(batchID)){
				coffeeBagTableRows.add(c);
			}
		}

		CoffeeBag[] coffeeBagFilter = new CoffeeBag[coffeeBagTableRows.size()];
		coffeeBagTableRows.toArray(coffeeBagFilter);

		if(coffeeBagFilter != null){
			TableRow[] tableRows = TableRowGenerator.GetCoffeeBagTableRows(coffeeBagFilter, this);

			if(tableRows == null){
				return;
			}

			for(TableRow tablerow : tableRows){
				if(tablerow == null){
					continue;
				}

				stk.addView(tablerow);
			}
		}
	}

	public void TableSortByWarehouse(String warehouseID){

		stk.removeViews(1, stk.getChildCount() - 1);

		CoffeeBag[] coffeeBags = CRUDCoffeeBag.GetCoffeeBags();

		ArrayList<CoffeeBag> coffeeBagTableRows = new ArrayList<CoffeeBag>();

		for(CoffeeBag c : coffeeBags){
			if(c == null){
				continue;
			}

			if(c.GetWarehouse().GetId().equals(warehouseID)){
				coffeeBagTableRows.add(c);
			}
		}

		CoffeeBag[] coffeeBagFilter = new CoffeeBag[coffeeBagTableRows.size()];
		coffeeBagTableRows.toArray(coffeeBagFilter);

		if(coffeeBagFilter != null){
			TableRow[] tableRows = TableRowGenerator.GetCoffeeBagTableRows(coffeeBagFilter, this);

			if(tableRows == null){
				return;
			}

			for(TableRow tablerow : tableRows){
				if(tablerow == null){
					continue;
				}

				stk.addView(tablerow);
			}
		}
	}

	private void GetReferences(){
		id_coffeeBag_txt = findViewById(R.id.Id_coffebag_text);
		id_batch_txt = findViewById(R.id.id_batch_text);
		id_warehouse_txt = findViewById(R.id.id_warehouse_text);
		add_button = findViewById(R.id.add_button);
		list_button = findViewById(R.id.list_button);
		remove_button = findViewById(R.id.remove_button);
		stk = (TableLayout) findViewById(R.id.table_list);
	}

	public void openActivityCoffeBagRegister(){
		Intent intent = new Intent(this, CoffeeBagRegisterMenu.class);
		startActivity(intent);
	}

	public void openActivityCoffeBagRemove(){
		Intent intent = new Intent(this, CoffeeBagRemoveMenu.class);
		startActivity(intent);
	}


}