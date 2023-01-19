package Frameworks.Adapters;

import android.content.Context;
import android.widget.TableRow;

import Frameworks.Adapters.Batch.BatchTableRowGenerator;
import Frameworks.Adapters.CoffeeBag.CoffeeBagTableRowGenerator;
import Frameworks.Adapters.Employee.EmployeeTableRowGenerator;
import Frameworks.Adapters.Warehouse.WarehouseTableRowGenerator;
import Policy.Entity.Batch;
import Policy.Entity.CoffeeBag;
import Policy.Entity.Employee;
import Policy.Entity.Warehouse;

public class TableRowGenerator {
	private static EmployeeTableRowGenerator tableRowGeneratorEmployee;
	private static WarehouseTableRowGenerator tableRowGeneratorWarehouse;
	private static BatchTableRowGenerator tableRowGeneratorBatch;
	private static CoffeeBagTableRowGenerator tableRowGeneratorCoffeeBag;


	public TableRowGenerator(){

	}

	//----------------------Employee Lines-----------------------------

	//Teoricamente Feita
	public static TableRow[] GeneratorEmployeeLines(Context context, Employee[] employees) {

		if(tableRowGeneratorEmployee == null){
			System.out.println("table Row Generator Employee null");
			return null;
		}
		return tableRowGeneratorEmployee.GeneratorLines(context, employees);
	}

	//Implementar
	public static void setTableRowGeneratorEmployee(EmployeeTableRowGenerator m_tableRowGeneratorEmployee) {
		tableRowGeneratorEmployee = m_tableRowGeneratorEmployee;
	}

	//----------------------Warehouse Lines-----------------------------

	//Implementar
	public static TableRow[] GeneratorWarehouseLines(Context context, Warehouse[] warehouses) {
		if(tableRowGeneratorWarehouse == null){
			System.out.printf("table row generator warehouse null");
			return null;
		}

		return tableRowGeneratorWarehouse.GeneratorLines(context, warehouses);
	}

	//Implementar
	public static void setTableRowGeneratorWarehouse(WarehouseTableRowGenerator m_tableRowGeneratorWarehouse) {
		tableRowGeneratorWarehouse = m_tableRowGeneratorWarehouse;
	}

	//----------------------Batch Lines-----------------------------

	//Teoricamente feita
	public static TableRow[] GeneratorBatchLines(Context context, Batch[] batches) {

		if(tableRowGeneratorBatch == null){
			System.out.println("table Row Generator Batch null");
			return null;
		}
		return tableRowGeneratorBatch.GeneratorLines(context, batches);
	}

	//Implementar
	public static void setTableRowGeneratorBatch(BatchTableRowGenerator m_tableRowGeneratorBatch) {

		tableRowGeneratorBatch = m_tableRowGeneratorBatch;
	}

	//----------------------Coffee Bags Lines-----------------------------

	//Teoricamente feita
	public static TableRow[] GeneratorCoffeeBagLines(Context context, CoffeeBag[] coffeeBags) {

		if(tableRowGeneratorCoffeeBag == null){
			System.out.println("table row generator coffee bag null");
			return null;
		}
		return tableRowGeneratorCoffeeBag.GeneratorLines(context, coffeeBags);
	}

	//Implementar
	public static void setTableRowGeneratorCoffeeBag(CoffeeBagTableRowGenerator m_tableRowGeneratorCoffeeBag) {
		tableRowGeneratorCoffeeBag = m_tableRowGeneratorCoffeeBag;
	}
}
