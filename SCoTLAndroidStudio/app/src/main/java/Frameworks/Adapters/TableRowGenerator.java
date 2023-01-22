package Frameworks.Adapters;

import android.content.Context;
import android.widget.TableRow;

import Frameworks.Adapters.BatchRow.BatchTableRowGenerator;
import Frameworks.Adapters.EmployeeRow.EmployeeTableRowGenerator;
import Policy.Entity.Employee;

public class TableRowGenerator {
	private static EmployeeTableRowGenerator m_employeeTableRowGenerator;
//	private static TableRow[] TableRowGeneratorWarehouse;
	private static BatchTableRowGenerator tableRowGeneratorBatch;
//	private static TableRow[] TableRowGeneratorCoffeeBag;

	public static TableRow[] GetEmployeeTableRows(Employee[] employeeArray, Context context)
	{
		if(m_employeeTableRowGenerator == null)
		{
			return null;
		}

		return m_employeeTableRowGenerator.GenerateLines(employeeArray, context);
	}
//
//	public static TableRow[] getTableRowGeneratorEmployeeByName(String nome) {
//		return TableRowGeneratorEmployee;
//	}
//
//	public static TableRow[] getTableRowGeneratorEmployeeByCpf(String cpf) {
//		return TableRowGeneratorEmployee;
//	}

	public static void SetEmployeeTableRowGenerator(EmployeeTableRowGenerator employeeTableRowGenerator)
	{
		m_employeeTableRowGenerator = employeeTableRowGenerator;
	}

//	public static TableRow[] getTableRowGeneratorWarehouse(String id, String state, String city, String street, String number) {
//		return TableRowGeneratorWarehouse;
//	}
//
//	public static void setTableRowGeneratorWarehouse(TableRow[] tableRowGeneratorWarehouse) {
//		TableRowGeneratorWarehouse = tableRowGeneratorWarehouse;
//	}

	public static TableRow[] GeneratorBatchLines(Context context) {

		if(tableRowGeneratorBatch == null){
			System.out.println("table Row Generator Batch null");
			return null;
		}
		return tableRowGeneratorBatch.GenerateLines(context);
	}

	public static BatchTableRowGenerator GeneratorBatchLines(String id, String creationDate) {
		return tableRowGeneratorBatch;
	}

	public static void setTableRowGeneratorBatch(BatchTableRowGenerator tableRowGeneratorBatch) {
		//TableRowGeneratorBatch = tableRowGeneratorBatch;
	}

//	public static TableRow[] getTableRowGeneratorCoffeeBag() {
//		return TableRowGeneratorCoffeeBag;
//	}
//
//	public static TableRow[] getTableRowGeneratorCoffeeBag(String id, String batch_Id, String warehouse_State, String warehouse_Street, String warehouse_Num){
//		return TableRowGeneratorCoffeeBag;
//	}
//
//	public static void setTableRowGeneratorCoffeeBag(TableRow[] tableRowGeneratorCoffeeBag) {
//		TableRowGeneratorCoffeeBag = tableRowGeneratorCoffeeBag;
//	}
}
