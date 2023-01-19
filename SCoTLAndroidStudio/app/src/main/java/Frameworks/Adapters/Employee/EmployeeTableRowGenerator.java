package Frameworks.Adapters.Employee;

import android.content.Context;
import android.widget.TableRow;

import Policy.Entity.Employee;

public interface EmployeeTableRowGenerator {
	public TableRow[] GeneratorLines(Context context, Employee[] employees);
}
