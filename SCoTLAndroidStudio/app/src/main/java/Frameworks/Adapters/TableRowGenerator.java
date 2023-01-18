package Frameworks.Adapters;

import android.widget.TableRow;

public class TableRowGenerator {
	private static TableRow[] TableRowGeneratorEmployee;
	private static TableRow[] TableRowGeneratorWarehouse;
	private static TableRow[] TableRowGeneratorBatch;
	private static TableRow[] TableRowGeneratorCoffeeBag;

	public TableRowGenerator(){

	}

	public static TableRow[] getTableRowGeneratorEmployee() {
		return TableRowGeneratorEmployee;
	}

	public static TableRow[] getTableRowGeneratorEmployee(String cpf) {
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

	public static TableRow[] getTableRowGeneratorBatch() {
		return TableRowGeneratorBatch;
	}

	public static TableRow[] getTableRowGeneratorBatch(String id, String creationDate) {
		return TableRowGeneratorBatch;
	}

	public static void setTableRowGeneratorBatch(TableRow[] tableRowGeneratorBatch) {
		TableRowGeneratorBatch = tableRowGeneratorBatch;
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
