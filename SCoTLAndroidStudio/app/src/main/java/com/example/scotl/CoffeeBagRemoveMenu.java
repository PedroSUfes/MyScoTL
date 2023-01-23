package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import Policy.BusinessRules.CRUDBatch;
import Policy.BusinessRules.CRUDCoffeeBag;
import Policy.Entity.Batch;

public class CoffeeBagRemoveMenu extends AppCompatActivity {

	private EditText id_coffeeBag_txt;
	private EditText id_batch_txt;
	private Button remove_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_coffee_bag_remove_menu);

		GetReferences();

		remove_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				try{
					String id = id_coffeeBag_txt.getText().toString();
					String idBatch = id_batch_txt.getText().toString();
					if(id.equals("")){
						Toast.makeText(CoffeeBagRemoveMenu.this, "ID Saca Vazio", Toast.LENGTH_SHORT).show();
					}
					else if(id.length() > 4){
						Toast.makeText(CoffeeBagRemoveMenu.this, "ID Saca necessita de 4 dígitos", Toast.LENGTH_SHORT).show();
					}
					else if(idBatch.equals("")){
						Toast.makeText(CoffeeBagRemoveMenu.this, "ID Lote Vazio", Toast.LENGTH_SHORT).show();
					}
					else if(idBatch.length() > 4){
						Toast.makeText(CoffeeBagRemoveMenu.this, "ID Lote necessita de 4 dígitos", Toast.LENGTH_SHORT).show();
					}
					else{

						Batch batch = CRUDBatch.GetBatch(idBatch);

						if(batch == null){
							return;
						}

						Boolean result = CRUDCoffeeBag.TryRemoveCoffeeBag(batch.GetId(), id);

						openActivityCoffeeBagList();

					}

				}catch(Exception e){
					Toast.makeText(CoffeeBagRemoveMenu.this, "Erro ", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	private void GetReferences(){
		id_coffeeBag_txt = findViewById(R.id.id_coffeebag_et);
		id_batch_txt = findViewById(R.id.id_batch_et);
		remove_button = findViewById(R.id.remover_button);

	}

	public void openActivityCoffeeBagList(){
		Intent intent = new Intent(this, CoffeeBagMenu.class);
		startActivity(intent);
	}
}