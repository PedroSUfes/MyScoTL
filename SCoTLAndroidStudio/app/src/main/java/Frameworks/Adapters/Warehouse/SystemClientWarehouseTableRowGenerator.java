package Frameworks.Adapters.Warehouse;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import Policy.BusinessRules.CRUDWarehouse;
import Policy.Entity.Warehouse;

public class SystemClientWarehouseTableRowGenerator implements WarehouseTableRowGenerator{

	public TableRow[] GeneratorLines(Context context){
		Warehouse[] warehouses = CRUDWarehouse.GetWarehouses(true);
		ArrayList<TableRow> tableRowArrayList = new ArrayList<TableRow>();

		if(warehouses != null){
			for(int i = 0; i < warehouses.length; i++){
				TableRow tbrow = new TableRow(context);

				//Warehouse ID
				TextView t1v = new TextView(context);
				t1v.setText(warehouses[i].GetId());
				t1v.setTextColor(Color.BLACK);
				t1v.setGravity(Gravity.CENTER);
				t1v.setTextSize(18);
				tbrow.addView(t1v);

				//Warehouse Complete Address
				/*
				TextView t2v = new TextView(context);
				t2v.setText(batches[i].GetId());
				t2v.setTextColor(Color.BLACK);
				t2v.setGravity(Gravity.CENTER);
				t2v.setTextSize(18);
				tbrow.addView(t2v);
				*/
				//Warehouse State
				TextView t2v = new TextView(context);
				t2v.setText(warehouses[i].GetStateName());
				t2v.setTextColor(Color.BLACK);
				t2v.setGravity(Gravity.CENTER);
				t2v.setTextSize(18);
				tbrow.addView(t2v);

				//Warehouse City
				TextView t3v = new TextView(context);
				t3v.setText(warehouses[i].GetCityName());
				t3v.setTextColor(Color.BLACK);
				t3v.setGravity(Gravity.CENTER);
				t3v.setTextSize(18);
				tbrow.addView(t3v);

				//Warehouse Street
				TextView t4v = new TextView(context);
				t4v.setText(warehouses[i].GetStreetName());
				t4v.setTextColor(Color.BLACK);
				t4v.setGravity(Gravity.CENTER);
				t4v.setTextSize(18);
				tbrow.addView(t4v);

				//Warehouse Number
				TextView t5v = new TextView(context);
				t5v.setText(warehouses[i].GetNumber());
				t5v.setTextColor(Color.BLACK);
				t5v.setGravity(Gravity.CENTER);
				t5v.setTextSize(18);
				tbrow.addView(t5v);

				//Warehouse Begin Date
				TextView t6v = new TextView(context);
				t6v.setText(warehouses[i].GetBeginDate());
				t6v.setTextColor(Color.BLACK);
				t6v.setGravity(Gravity.CENTER);
				t6v.setTextSize(18);
				tbrow.addView(t6v);

				//Warehouse End Date
				TextView t7v = new TextView(context);
				t7v.setText(warehouses[i].GetEndDate());
				t7v.setTextColor(Color.BLACK);
				t7v.setGravity(Gravity.CENTER);
				t7v.setTextSize(18);
				tbrow.addView(t7v);

				//Warehouse Owner's Name
				TextView t8v = new TextView(context);
				t8v.setText(warehouses[i].GetOwner().GetName());
				t8v.setTextColor(Color.BLACK);
				t8v.setGravity(Gravity.CENTER);
				t8v.setTextSize(18);
				tbrow.addView(t8v);

				tableRowArrayList.add(tbrow);
			}
		}

		if(tableRowArrayList.isEmpty()){
			return null;
		}

		TableRow[] tableRows = new TableRow[tableRowArrayList.size()];
		int index = -1;
		for(TableRow t: tableRowArrayList){
			if(t != null){
				continue;
			}
			tableRows[index] = t;
		}

		return tableRows;
	}
}
