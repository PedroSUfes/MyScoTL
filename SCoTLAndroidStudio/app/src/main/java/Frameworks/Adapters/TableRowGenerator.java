package Frameworks.Adapters;

import static Policy.BusinessRules.CRUDBatch.GetBatches;

import android.content.Context;
import android.widget.TableRow;

import Policy.Entity.Batch;
import Policy.Entity.CoffeeBag;
import Policy.Entity.Employee;
import Policy.Entity.Warehouse;

public class TableRowGenerator {
	private static TableRow[] TableRowGeneratorEmployee;
	private static TableRow[] TableRowGeneratorWarehouse;
	private static BatchTableRowGenerator tableRowGeneratorBatch;
	private static TableRow[] TableRowGeneratorCoffeeBag;


	public TableRowGenerator(){

	}

	public static TableRow[] getTableRowGeneratorEmployee() {

		return TableRowGeneratorEmployee;
	}

	public static TableRow[] getTableRowGeneratorEmployeeByName(String nome) {
		return TableRowGeneratorEmployee;
	}

	public static TableRow[] getTableRowGeneratorEmployeeByCpf(String cpf) {
		return TableRowGeneratorEmployee;
	}

	public static void setTableRowGeneratorEmployee(TableRow[] tableRowGeneratorEmployee) {
		TableRowGeneratorEmployee = tableRowGeneratorEmployee;
	}

	public static TableRow[] getTableRowGeneratorWarehouse(String id, String state, String city, String street, String number) {
		return TableRowGeneratorWarehouse;
	}

	public static void setTableRowGeneratorWarehouse(TableRow[] tableRowGeneratorWarehouse) {
		TableRowGeneratorWarehouse = tableRowGeneratorWarehouse;
	}

	public static TableRow[] GeneratorBatchLines(Context context) {

		if(tableRowGeneratorBatch == null){
			System.out.println("table Row Generator Batch null");
			return null;
		}
		return tableRowGeneratorBatch.GeneratorLines(context);
	}

	public static BatchTableRowGenerator GeneratorBatchLines(String id, String creationDate) {
		return tableRowGeneratorBatch;
	}

	public static void setTableRowGeneratorBatch(BatchTableRowGenerator tableRowGeneratorBatch) {
		//TableRowGeneratorBatch = tableRowGeneratorBatch;
	}

	public static TableRow[] getTableRowGeneratorCoffeeBag() {
		return TableRowGeneratorCoffeeBag;
	}

	public static TableRow[] getTableRowGeneratorCoffeeBag(String id, String batch_Id, String warehouse_State, String warehouse_Street, String warehouse_Num){
		return TableRowGeneratorCoffeeBag;
	}

	public static void setTableRowGeneratorCoffeeBag(TableRow[] tableRowGeneratorCoffeeBag) {
		TableRowGeneratorCoffeeBag = tableRowGeneratorCoffeeBag;
	}
}
