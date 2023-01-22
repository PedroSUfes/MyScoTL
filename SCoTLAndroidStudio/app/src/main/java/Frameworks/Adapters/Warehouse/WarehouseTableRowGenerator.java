package Frameworks.Adapters.Warehouse;

import android.content.Context;
import android.widget.TableRow;

import Policy.Entity.Warehouse;

public interface WarehouseTableRowGenerator {

	public TableRow[] GeneratorLines(Context context, Warehouse[] warehouses);
}
