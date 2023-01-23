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

import Policy.BusinessRules.CRUDBatch;
import Policy.Entity.Batch;

public class BatchRemoveActivity extends AppCompatActivity {

	private EditText id_batch_txt;
	private Button remove_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_batch_remove);

		GetReferences();

		remove_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				try{
					String id = id_batch_txt.getText().toString();
					if(id.equals("")){
						Toast.makeText(BatchRemoveActivity.this, "ID Lote Vazio", Toast.LENGTH_SHORT).show();
					}
					else if(id.length() != 4){
						Toast.makeText(BatchRemoveActivity.this, "ID Lote necessita de 4 dígitos", Toast.LENGTH_SHORT).show();
					}
					else{

						Boolean result = CRUDBatch.TryRemoveBatch(id);

						openActivityBatchList();
					}

				}catch(Exception e){
					Toast.makeText(BatchRemoveActivity.this, "Erro ", Toast.LENGTH_SHORT).show();
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
		remove_button = findViewById(R.id.cadastrar_button);
	}
}