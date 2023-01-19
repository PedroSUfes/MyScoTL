package Frameworks.Adapters.Batch;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import Policy.BusinessRules.CRUDBatch;
import Policy.Entity.Batch;

public class WarehouseManagerBatchTableRowGenerator implements BatchTableRowGenerator{

	public TableRow[] GeneratorLines(Context context, Batch[] batches){

		ArrayList<TableRow> tableRowList = new ArrayList<TableRow>();

		if(batches != null){
			for(int i = 0; i < batches.length; i++){
				TableRow tbrow = new TableRow(context);

				TextView t1v = new TextView(context);
				t1v.setText(batches[i].GetId());
				t1v.setTextColor(Color.BLACK);
				t1v.setGravity(Gravity.CENTER);
				t1v.setTextSize(18);
				tbrow.addView(t1v);

				TextView t2v = new TextView(context);
				t2v.setText(batches[i].GetCreationDate());
				t2v.setTextColor(Color.BLACK);
				t2v.setGravity(Gravity.CENTER);
				t2v.setTextSize(18);
				tbrow.addView(t2v);

				tableRowList.add(tbrow);

			}
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
