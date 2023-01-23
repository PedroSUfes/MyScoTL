package Frameworks.Adapters;

import android.content.Context;
import android.widget.TableRow;

import java.lang.reflect.Array;

import Frameworks.Adapters.WarehouseRow.WarehouseTableRowGenerator;
import Policy.Entity.Batch;
import Policy.Entity.CoffeeBag;
import Frameworks.Adapters.BatchRow.BatchTableRowGenerator;
import Frameworks.Adapters.EmployeeRow.EmployeeTableRowGenerator;
import Frameworks.Adapters.PropertyRow.PropertyTableRowGenerator;
import Policy.Entity.Employee;
import Policy.Entity.Property;

public class TableRowGenerator {
	private static BatchTableRowGenerator tableRowGeneratorBatch;
	private static WarehouseTableRowGenerator m_warehouseTableRowGenerator;
	private static EmployeeTableRowGenerator m_employeeTableRowGenerator;
	private static BatchTableRowGenerator m_tableRowGeneratorBatch;
	private static PropertyTableRowGenerator m_propertyTableRowGenerator;

	public static TableRow[] GetEmployeeTableRows(Employee[] employeeArray, Context context)
	{
		if(m_employeeTableRowGenerator == null)
		{
			return null;
		}

	public static void SetWarehouseTableRowGenerator(WarehouseTableRowGenerator warehouseTableRowGenerator) {
		if (warehouseTableRowGenerator == null)
		{
			return;
		}
		m_warehouseTableRowGenerator = warehouseTableRowGenerator;
	}

	public static TableRow[] GetWarehouseTableRows(Warehouse[] warehouses, Context context) {
		return m_warehouseTableRowGenerator.GenerateLines(warehouses, context);
		return m_employeeTableRowGenerator.GenerateLines(employeeArray, context);
	}

	public static void SetEmployeeTableRowGenerator(EmployeeTableRowGenerator employeeTableRowGenerator)
	{
		if(employeeTableRowGenerator == null)
		{
			return;
		}

		m_employeeTableRowGenerator = employeeTableRowGenerator;
	}

	public static void SetPropertyTableRowGenerator(PropertyTableRowGenerator propertyTableRowGenerator)
	{
		if(propertyTableRowGenerator == null)
		{
			System.out.println("Null property table roww generator");
		}

		m_propertyTableRowGenerator = propertyTableRowGenerator;
	}

	public static TableRow[] GetPropertyTableRows(Property[] propertyArray, Context context)
	{
		return m_propertyTableRowGenerator.GenerateLines(propertyArray, context);
	}

	public static TableRow[] GeneratorBatchLines(Context context) {

		if(m_tableRowGeneratorBatch == null){
			System.out.println("table Row Generator Batch null");
			return null;
		}
		return m_tableRowGeneratorBatch.GenerateLines(context);
	}

	public static BatchTableRowGenerator GeneratorBatchLines(String id, String creationDate) {
		return m_tableRowGeneratorBatch;
	}

	public static void setM_tableRowGeneratorBatch(BatchTableRowGenerator m_tableRowGeneratorBatch) {
		//TableRowGeneratorBatch = tableRowGeneratorBatch;
	}
}
