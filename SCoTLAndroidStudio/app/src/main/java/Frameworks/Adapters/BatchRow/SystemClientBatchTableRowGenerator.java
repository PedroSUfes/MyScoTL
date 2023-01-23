package Frameworks.Adapters.BatchRow;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import Frameworks.Adapters.EmployeeRow.SystemClientEmployeeTableRowGenerator;
import Policy.BusinessRules.CRUDBatch;
import Policy.Entity.Batch;

public class SystemClientBatchTableRowGenerator implements BatchTableRowGenerator {

	public SystemClientBatchTableRowGenerator() {}

	@Override
	public TableRow[] GenerateLines(Batch[] batchArray, Context context){

		if(batchArray == null){
			return null;
		}
    
		ArrayList<TableRow> tableRowList = new ArrayList<TableRow>();

		for (Batch batch : batchArray) {

			if(batch == null){
				continue;
			}

			TableRow tbrow = new TableRow(context);

			TextView idTxt = new TextView(context);
			ConfigureTextView(idTxt, batch.GetId());

			TextView creationDateTxt = new TextView(context);
			ConfigureTextView(creationDateTxt, batch.GetCreationDate());

			tbrow.addView(idTxt);
			tbrow.addView(creationDateTxt);

			tableRowList.add(tbrow);
		}


		if(tableRowList.isEmpty()){
			return null;
		}

		TableRow[] tableRows = new TableRow[tableRowList.size()];
		int index = -1;
		for(TableRow t: tableRowList){
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
