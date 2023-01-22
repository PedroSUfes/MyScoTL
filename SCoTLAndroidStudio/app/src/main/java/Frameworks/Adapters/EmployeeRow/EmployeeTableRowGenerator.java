package Frameworks.Adapters.EmployeeRow;

import android.content.Context;
import android.widget.TableRow;

import Policy.Entity.Employee;

public interface EmployeeTableRowGenerator
{
    public TableRow[] GenerateLines(Employee[] employeeArray, Context context);
}
