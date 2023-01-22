package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

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

				String id_batch = id_batch_et.getText().toString();
				String storage_date = storage_date_et.getText().toString();
				String id_warehouse = id_warehouse_et.getText().toString();
				String id_coffeebag = id_coffeebag_et.getText().toString();

				

			}
		});


	}
}