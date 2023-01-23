package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;
import Policy.Entity.CoffeeBag;
import Policy.Entity.Warehouse;

public class CoffeeBagRegisterMenu extends AppCompatActivity {

	private EditText id_coffeebag_et;
	private EditText storage_date_et;
	private EditText id_warehouse_et;
	private EditText id_batch_et;
	private Button add_button;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_coffee_bag_register_menu);

		id_coffeebag_et = findViewById(R.id.id_coffeebag_et);
		storage_date_et = findViewById(R.id.date_storage_et);
		id_warehouse_et = findViewById(R.id.id_warehouse_et);
		id_batch_et = findViewById(R.id.id_batch_et);

		add_button = findViewById(R.id.cadastrar_button);


		add_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				CoffeeBag coffeeBag;

				try{

					String id_batch = id_batch_et.getText().toString();
					String storage_date = storage_date_et.getText().toString();
					String id_warehouse = id_warehouse_et.getText().toString();
					String id_coffeebag = id_coffeebag_et.getText().toString();


					if(id_coffeebag.equals("")){
						Toast.makeText(CoffeeBagRegisterMenu.this, "ID Sacas de Café Vazio", Toast.LENGTH_SHORT).show();
					}
					else if(storage_date.equals("")){
						Toast.makeText(CoffeeBagRegisterMenu.this, "Data de Armazenamento Vazia", Toast.LENGTH_SHORT).show();
					}
					else if(id_warehouse.equals("")){
						Toast.makeText(CoffeeBagRegisterMenu.this, "ID Galpão Vazio", Toast.LENGTH_SHORT).show();
					}
					else if(id_batch.equals("")){
						Toast.makeText(CoffeeBagRegisterMenu.this, "ID Lote Vazio", Toast.LENGTH_SHORT).show();
					}
					else if(id_coffeebag.length() != 4){
						Toast.makeText(CoffeeBagRegisterMenu.this, "ID Sacas necessita de 4 dígitos", Toast.LENGTH_SHORT).show();
					}
					else if(id_warehouse.length() != 4){
						Toast.makeText(CoffeeBagRegisterMenu.this, "ID Galpão necessita de 4 dígitos", Toast.LENGTH_SHORT).show();
					}
					else if(id_batch.length() != 4){
						Toast.makeText(CoffeeBagRegisterMenu.this, "ID Lote necessita de 4 dígitos", Toast.LENGTH_SHORT).show();
					}
					else{
						SQLiteDAO db = new SQLiteDAO(CoffeeBagRegisterMenu.this);
						DatabaseAccess.coffeeBagOperationsInterface = db;
						DatabaseAccess.batchOperationsInterface = db;
						DatabaseAccess.warehouseOperationsInterface = db;

						Batch batch = DatabaseAccess.batchOperationsInterface.GetBatch(id_batch);
						Warehouse[] warehouse = DatabaseAccess.warehouseOperationsInterface.GetWarehouse(id_warehouse, false);

						if(batch == null){
							Toast.makeText(CoffeeBagRegisterMenu.this, "ID Lote inválido", Toast.LENGTH_SHORT).show();
						}
						else if(warehouse == null){
							Toast.makeText(CoffeeBagRegisterMenu.this, "ID Galpão inválido", Toast.LENGTH_SHORT).show();
						}
						else{
							coffeeBag = new CoffeeBag(id_coffeebag, batch, warehouse[0], storage_date);

							Boolean result = DatabaseAccess.coffeeBagOperationsInterface.TryRegisterCoffeeBag(coffeeBag);

							Toast.makeText(CoffeeBagRegisterMenu.this, result.toString(), Toast.LENGTH_SHORT).show();

							openActivityCoffeeBagMenu();
						}
					}
				}
				catch(Exception e){
					Toast.makeText(CoffeeBagRegisterMenu.this, "Erro", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	public void openActivityCoffeeBagMenu(){
		Intent intent = new Intent(CoffeeBagRegisterMenu.this, CoffeeBagMenu.class);
		startActivity(intent);
	}
}