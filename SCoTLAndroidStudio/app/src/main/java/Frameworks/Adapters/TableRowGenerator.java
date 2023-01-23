package Frameworks.Adapters;

import android.content.Context;
import android.widget.TableRow;

import Frameworks.Adapters.BatchRow.BatchTableRowGenerator;
import Frameworks.Adapters.CoffeeBagRow.CoffeeBagTableRowGenerator;
import Frameworks.Adapters.EmployeeRow.EmployeeTableRowGenerator;
import Frameworks.Adapters.PropertyRow.PropertyTableRowGenerator;
import Policy.Entity.Batch;
import Policy.Entity.CoffeeBag;
import Policy.Entity.Employee;
import Policy.Entity.Property;

public class TableRowGenerator {
	private static EmployeeTableRowGenerator m_employeeTableRowGenerator;
	private static BatchTableRowGenerator m_batchTableRowGenerator;
	private static CoffeeBagTableRowGenerator m_coffeeBagTableRowGenerator;
//	private static TableRow[] TableRowGeneratorWarehouse;
	private static PropertyTableRowGenerator m_propertyTableRowGenerator;

	public static TableRow[] GetEmployeeTableRows(Employee[] employeeArray, Context context)
	{
		if(m_employeeTableRowGenerator == null)
		{
			return null;
		}

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
			System.out.println("Null property table row generator");
			return;
		}

		m_propertyTableRowGenerator = propertyTableRowGenerator;
	}

	public static void SetBatchTableRowGenerator(BatchTableRowGenerator batchTableRowGenerator){

		if(batchTableRowGenerator == null){
			System.out.println("Null batch table row generator");
			return;
		}

		m_batchTableRowGenerator = batchTableRowGenerator;
	}

	public static void SetCoffeeBagTableRowGenerator(CoffeeBagTableRowGenerator coffeeBagTableRowGenerator){
		if(coffeeBagTableRowGenerator == null){
			System.out.printf("Null coffeeBag table row generator");
			return;
		}

		m_coffeeBagTableRowGenerator = coffeeBagTableRowGenerator;
	}

	public static TableRow[] GetPropertyTableRows(Property[] propertyArray, Context context)
	{
		return m_propertyTableRowGenerator.GenerateLines(propertyArray, context);
	}


	public static TableRow[] GetBatchTableRows(Batch[] batchArray, Context context) {

		return m_batchTableRowGenerator.GenerateLines(batchArray, context);
	}

	public static TableRow[] GetCoffeeBagTableRows(CoffeeBag[] coffeeBagArray, Context context) {

		return m_coffeeBagTableRowGenerator.GenerateLines(coffeeBagArray, context);
	}
}