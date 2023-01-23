package Frameworks.Adapters.CoffeeBagRow;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import Policy.Entity.CoffeeBag;

public class SystemClientCoffeeBagTableRowGenerator implements CoffeeBagTableRowGenerator{

	public SystemClientCoffeeBagTableRowGenerator(){}

	@Override
	public TableRow[] GenerateLines(CoffeeBag[] coffeeBagArray, Context context){

		if(coffeeBagArray == null){
			return null;
		}

		ArrayList<TableRow> tableRowsList = new ArrayList<TableRow>();

		for(CoffeeBag coffee : coffeeBagArray){

			if(coffee == null){
				continue;
			}

			TableRow tbrow = new TableRow(context);

			TextView idCoffee = new TextView(context);
			ConfigureTextView(idCoffee, coffee.GetId());

			TextView data = new TextView(context);
			ConfigureTextView(data, coffee.GetStorageDate());

			TextView idWarehouse = new TextView(context);
			ConfigureTextView(idWarehouse, coffee.GetWarehouse().GetId());

			TextView idBatch = new TextView(context);
			ConfigureTextView(idBatch, coffee.GetBatch().GetId());

			tbrow.addView(idCoffee);
			tbrow.addView(data);
			tbrow.addView(idWarehouse);
			tbrow.addView(idBatch);

			tableRowsList.add(tbrow);

		}

		if(tableRowsList.isEmpty()){
			return null;
		}

		TableRow[] tableRows = new TableRow[tableRowsList.size()];
		int index = -1;
		for(TableRow t: tableRowsList){
			++index;
			if(t == null){
				continue;
			}
			tableRows[index] = t;
		}

		return tableRows;

	}

	private void ConfigureTextView(TextView textView, String content)
	{
		textView.setText(content);
		textView.setTextColor(Color.parseColor("#2F4F4F"));
		textView.setGravity(Gravity.CENTER_HORIZONTAL);
		textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
		textView.setVisibility(View.VISIBLE);
		textView.setPadding(30, 30, 30, 30);
		textView.setTextSize(15);
	}
}
