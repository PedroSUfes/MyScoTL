package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.CRUDBatch;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;

public class BatchRegisterMenu extends AppCompatActivity {

	private EditText id_batch_txt;
	private EditText creation_date_txt;
	private Button add_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_batch_register_menu);

		GetReferences();


		add_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Batch batch;

				try{

					String id = id_batch_txt.getText().toString();
					String date = creation_date_txt.getText().toString();

					if(id.equals("")){
						Toast.makeText(BatchRegisterMenu.this, "ID Lote Vazio", Toast.LENGTH_SHORT).show();
					}
					else if(date.equals("")){
						Toast.makeText(BatchRegisterMenu.this, "Data de Cadastro Vazia", Toast.LENGTH_SHORT).show();
					}
					else if(id.length() != 4){
						Toast.makeText(BatchRegisterMenu.this, "ID Lote necessita de 4 dígitos", Toast.LENGTH_SHORT).show();
					}
					else{
						batch = new Batch(id, date);

						//Adicionando o novo Lote no banco
						Boolean result = CRUDBatch.TryRegisterBatch(batch);

						//Toast.makeText(BatchRegisterMenu.this, result.toString(), Toast.LENGTH_SHORT).show();

						openActivityBatchList();

					}

				}catch(Exception e){
					Toast.makeText(BatchRegisterMenu.this, "Erro ", Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	public void openActivityBatchList(){
		Intent intent = new Intent(this, BatchListMenu.class);
		startActivity(intent);
	}

	private void GetReferences(){
		id_batch_txt = findViewById(R.id.id_batch_editView);
		creation_date_txt = findViewById(R.id.creationDate_editView);
		add_button = findViewById(R.id.cadastrar_button);
	}
}