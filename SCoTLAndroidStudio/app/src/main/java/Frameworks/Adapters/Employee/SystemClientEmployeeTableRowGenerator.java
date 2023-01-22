package Frameworks.Adapters.Employee;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import Policy.BusinessRules.CRUDEmployee;
import Policy.Entity.Employee;

public class SystemClientEmployeeTableRowGenerator implements EmployeeTableRowGenerator{

	public TableRow[] GeneratorLines(Context context, Employee[] employees){

		ArrayList<TableRow> tableRowArrayList = new ArrayList<TableRow>();

		if(employees == null) {
			return null;
		}

		for(int i = 0; i < employees.length; i++){
			TableRow tbrow = new TableRow(context);

			//Employee CPF
			TextView t1v = new TextView(context);
			t1v.setText(employees[i].GetCpf());
			t1v.setTextColor(Color.BLACK);
			t1v.setGravity(Gravity.CENTER);
			t1v.setTextSize(18);
			tbrow.addView(t1v);

			//Employee Name
			TextView t2v = new TextView(context);
			t2v.setText(employees[i].GetName());
			t2v.setTextColor(Color.BLACK);
			t2v.setGravity(Gravity.CENTER);
			t2v.setTextSize(18);
			tbrow.addView(t2v);

			//Employee Cellphone
			TextView t3v = new TextView(context);
			t3v.setText(employees[i].GetCellphone());
			t3v.setTextColor(Color.BLACK);
			t3v.setGravity(Gravity.CENTER);
			t3v.setTextSize(18);
			tbrow.addView(t3v);

			//Employee Birthdate
			TextView t4v = new TextView(context);
			t4v.setText(employees[i].GetBirthDate());
			t4v.setTextColor(Color.BLACK);
			t4v.setGravity(Gravity.CENTER);
			t4v.setTextSize(18);
			tbrow.addView(t4v);

			//Employee hiring Date
			TextView t5v = new TextView(context);
			t5v.setText(employees[i].GetHiringDate());
			t5v.setTextColor(Color.BLACK);
			t5v.setGravity(Gravity.CENTER);
			t5v.setTextSize(18);
			tbrow.addView(t5v);

			//Employee end Date
			TextView t6v = new TextView(context);
			t6v.setText(employees[i].GetEndDate());
			t6v.setTextColor(Color.BLACK);
			t6v.setGravity(Gravity.CENTER);
			t6v.setTextSize(18);
			tbrow.addView(t6v);

			//Employee Profession
			TextView t7v = new TextView(context);
			t7v.setText(employees[i].GetEmployeeType().toString());
			t7v.setTextColor(Color.BLACK);
			t7v.setGravity(Gravity.CENTER);
			t7v.setTextSize(18);
			tbrow.addView(t7v);

			tbrow.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					//Abrir uma Activity para a edição da TableRow
					OpenActivityEditEmployee(context);
				}
			});

			tableRowArrayList.add(tbrow);

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

	public void OpenActivityEditEmployee(Context context){

		//Intent intent = new Intent(context, /*EditEmployee_Activity*/);
		//startActivity(intent);
	}

}
