package Frameworks.Adapters.EmployeeRow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;


import com.example.scotl.UpdateAndRemoveEmployeeActivity;

import java.util.ArrayList;

import Policy.Entity.Employee;

public class SystemClientEmployeeTableRowGenerator
		implements
		EmployeeTableRowGenerator
{

	public SystemClientEmployeeTableRowGenerator() {}

	@Override
	public TableRow[] GenerateLines(Employee[] employeeArray, Context context)
	{
		if(employeeArray == null)
		{
			return null;
		}

		ArrayList<TableRow> tableRowList = new ArrayList<TableRow>();

		for(Employee e : employeeArray)
		{
			if(e == null)
			{
				continue;
			}
			TableRow tableRow = new TableRow(context);

			TextView cpfText = new TextView(context);
			ConfigureTextView(cpfText, e.GetCpf());

			TextView nameText = new TextView(context);
			ConfigureTextView(nameText, e.GetName());

			TextView professionText = new TextView(context);
			ConfigureTextView(professionText, e.GetEmployeeType().toString());

			tableRow.addView(cpfText);
			tableRow.addView(nameText);
			tableRow.addView(professionText);

			// Adicionar evento de update
			tableRow.setOnClickListener
					(
							view ->
							{
								UpdateAndRemoveEmployeeActivity.SetCpf(e.GetCpf());
								UpdateAndRemoveEmployeeActivity.SetName(e.GetName());
								UpdateAndRemoveEmployeeActivity.SetBirthDate(e.GetBirthDate());
								UpdateAndRemoveEmployeeActivity.SetCellphone(e.GetCellphone());
								UpdateAndRemoveEmployeeActivity.SetHiringDate(e.GetHiringDate());
								UpdateAndRemoveEmployeeActivity.SetEndDate(e.GetEndDate());
								UpdateAndRemoveEmployeeActivity.SetProfession(e.GetEmployeeType().toString());
								UpdateAndRemoveEmployeeActivity.SetWorkLocalId(e.GetWorkLocalId());

								context.startActivity(new Intent(context, UpdateAndRemoveEmployeeActivity.class));
							}
					);

			tableRowList.add(tableRow);
		}

		if(tableRowList.isEmpty())
		{
			return null;
		}

		TableRow[] toReturn = new TableRow[tableRowList.size()];
		int index = -1;
		for(TableRow tb : tableRowList)
		{
			++index;
			if(tb == null)
			{
				continue;
			}

			toReturn[index] = tb;
		}

		return toReturn;
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