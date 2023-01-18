package Frameworks.Adapters.Employee;

import android.content.Context;
import android.widget.TableRow;

public interface EmployeeTableRowGenerator {
	public TableRow[] GeneratorLines(Context context);
}
