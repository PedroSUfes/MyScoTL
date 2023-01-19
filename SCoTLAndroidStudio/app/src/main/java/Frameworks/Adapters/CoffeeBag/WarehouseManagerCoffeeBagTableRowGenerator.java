package Frameworks.Adapters.CoffeeBag;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import Policy.BusinessRules.CRUDCoffeeBag;
import Policy.Entity.CoffeeBag;

public class WarehouseManagerCoffeeBagTableRowGenerator implements CoffeeBagTableRowGenerator{

	public TableRow[] GeneratorLines(Context context, CoffeeBag[] coffeeBags){

		ArrayList<TableRow> tableRowList = new ArrayList<TableRow>();

		if(coffeeBags == null) {
			return null;
		}

		for(int i = 0; i < coffeeBags.length; i++){
			TableRow tbrow = new TableRow(context);

			//Coffee ID
			TextView t1v = new TextView(context);
			t1v.setText(coffeeBags[i].GetId());
			t1v.setTextColor(Color.BLACK);
			t1v.setGravity(Gravity.CENTER);
			t1v.setTextSize(18);
			tbrow.addView(t1v);

			//Coffee Batch ID
			TextView t2v = new TextView(context);
			t2v.setText(coffeeBags[i].GetBatch().GetId());
			t2v.setTextColor(Color.BLACK);
			t2v.setGravity(Gravity.CENTER);
			t2v.setTextSize(18);
			tbrow.addView(t1v);

			//Coffee Warehouse ID
			TextView t3v = new TextView(context);
			t3v.setText(coffeeBags[i].GetWarehouse().GetId());
			t3v.setTextColor(Color.BLACK);
			t3v.setGravity(Gravity.CENTER);
			t3v.setTextSize(18);
			tbrow.addView(t3v);

			//Coffee StorageDate
			TextView t4v = new TextView(context);
			t4v.setText(coffeeBags[i].GetStorageDate());
			t4v.setTextColor(Color.BLACK);
			t4v.setGravity(Gravity.CENTER);
			t4v.setTextSize(18);
			tbrow.addView(t4v);

			tableRowList.add(tbrow);

		}

		if(tableRowList.isEmpty()){
			return null;
		}

		TableRow[] tableRows = new TableRow[tableRowList.size()];
		int index = -1;
		for(TableRow t: tableRowList){
			if(t != null){
				continue;
			}
			tableRows[index] = t;
		}
		return tableRows;
	}
}
