package Frameworks.Adapters.WarehouseRow;

import android.content.Context;
import android.widget.TableRow;

import Policy.Entity.Warehouse;


public interface WarehouseTableRowGenerator
{
    public TableRow[] GenerateLines(Warehouse[] warehouseArray, Context context);
}
