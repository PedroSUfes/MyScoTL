package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.CRUDBatch;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Batch;

public class BatchListMenu extends AppCompatActivity {

	private TableLayout stk;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setContentView(R.layout.activity_batch_list_menu);

		Table();
	}

	public void Table(){
		stk = (TableLayout) findViewById(R.id.table_list);
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

		//List/Array com classe

		//SQLiteDAO db = new SQLiteDAO(this);
		//DatabaseAccess.batchOperationsInterface = db;
		//Batch[] teste = CRUDBatch.GetBatches();

		//Teste para encher a tabela e verificar o scroll
		Batch[] teste = new Batch[34];
		for(int i = 0; i < teste.length; i++){
			teste[i] = new Batch(Integer.toString(i), "18/01/2023");
		}

		//Batch[] teste = TableRowGenerator.getTableRowGeneratorBatch();*/

		if(teste != null){
			for(int i = 0; i < teste.length; i++){
				TableRow tbrow = new TableRow(this);

				TextView t1v = new TextView(this);
				t1v.setText(teste[i].GetId());
				t1v.setTextColor(Color.parseColor("#FF765138"));
				t1v.setGravity(Gravity.CENTER);
				t1v.setTextSize(18);
				tbrow.addView(t1v);

				TextView t2v = new TextView(this);
				t2v.setText(teste[i].GetCreationDate());
				t2v.setTextColor(Color.parseColor("#FF765138"));
				t2v.setGravity(Gravity.CENTER);
				t2v.setTextSize(18);
				tbrow.addView(t2v);

				stk.addView(tbrow);
			}

		}

	}
}