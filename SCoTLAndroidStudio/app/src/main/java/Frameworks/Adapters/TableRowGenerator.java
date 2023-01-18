package Frameworks.Adapters;

import android.content.Context;
import android.widget.TableRow;

import Frameworks.Adapters.Batch.BatchTableRowGenerator;
import Frameworks.Adapters.CoffeeBag.CoffeeBagTableRowGenerator;
import Frameworks.Adapters.Employee.EmployeeTableRowGenerator;
import Frameworks.Adapters.Warehouse.WarehouseTableRowGenerator;

public class TableRowGenerator {
	private static EmployeeTableRowGenerator tableRowGeneratorEmployee;
	private static WarehouseTableRowGenerator tableRowGeneratorWarehouse;
	private static BatchTableRowGenerator tableRowGeneratorBatch;
	private static CoffeeBagTableRowGenerator tableRowGeneratorCoffeeBag;


	public TableRowGenerator(){

	}

	//----------------------Employee Lines-----------------------------

	//Teoricamente Feita
	public static TableRow[] getTableRowGeneratorEmployee(Context context) {

		if(tableRowGeneratorEmployee == null){
			System.out.println("table Row Generator Employee null");
			return null;
		}
		return tableRowGeneratorEmployee.GeneratorLines(context);
	}

	//Implementar
	public static TableRow[] getTableRowGeneratorEmployeeByName(Context context, String nome) {
		return tableRowGeneratorEmployee.GeneratorLines(context);
	}

	//Implementar
	public static TableRow[] getTableRowGeneratorEmployeeByCpf(Context context, String cpf) {
		return tableRowGeneratorEmployee.GeneratorLines(context);
	}

	//Implementar
	public static void setTableRowGeneratorEmployee(EmployeeTableRowGenerator m_tableRowGeneratorEmployee) {
		tableRowGeneratorEmployee = m_tableRowGeneratorEmployee;
	}

	//----------------------Warehouse Lines-----------------------------

	//Implementar
	public static TableRow[] getTableRowGeneratorWarehouse(Context context, String owner_cpf, String state, String city, String street, String number) {
		return tableRowGeneratorWarehouse.GeneratorLines(context);
	}

	//Obs olhar essa função sobre os parametros.
	public static TableRow[] getTableRowGeneratorWarehouse(Context context) {
		return tableRowGeneratorWarehouse.GeneratorLines(context);
	}
	//Implementar
	public static void setTableRowGeneratorWarehouse(WarehouseTableRowGenerator m_tableRowGeneratorWarehouse) {
		tableRowGeneratorWarehouse = m_tableRowGeneratorWarehouse;
	}

	//----------------------Batch Lines-----------------------------

	//Teoricamente feita
	public static TableRow[] GeneratorBatchLines(Context context) {

		if(tableRowGeneratorBatch == null){
			System.out.println("table Row Generator Batch null");
			return null;
		}
		return tableRowGeneratorBatch.GeneratorLines(context);
	}

	//Implementar
	public static TableRow[] GeneratorBatchLines(Context context,String id, String creationDate) {

		return tableRowGeneratorBatch.GeneratorLines(context);
	}

	//Implementar
	public static void setTableRowGeneratorBatch(BatchTableRowGenerator m_tableRowGeneratorBatch) {

		tableRowGeneratorBatch = m_tableRowGeneratorBatch;
	}

	//----------------------Coffee Bags Lines-----------------------------

	//Teoricamente feita
	public static TableRow[] GeneratorCoffeeBagLines(Context context) {

		if(tableRowGeneratorCoffeeBag == null){
			System.out.println("table row generator coffee bag null");
			return null;
		}
		return tableRowGeneratorCoffeeBag.GeneratorLines(context);
	}

	//Implementar
	public static TableRow[] getTableRowGeneratorCoffeeBag(Context context, String id, String batch_Id, String warehouse_State, String warehouse_Street, String warehouse_Num){
		return tableRowGeneratorCoffeeBag.GeneratorLines(context);
	}

	//Implementar
	public static void setTableRowGeneratorCoffeeBag(CoffeeBagTableRowGenerator m_tableRowGeneratorCoffeeBag) {
		tableRowGeneratorCoffeeBag = m_tableRowGeneratorCoffeeBag;
	}
}
