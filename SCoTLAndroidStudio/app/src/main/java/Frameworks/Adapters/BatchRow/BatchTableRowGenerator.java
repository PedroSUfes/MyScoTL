package Frameworks.Adapters.BatchRow;
import android.content.Context;
import android.widget.TableRow;

import Policy.Entity.Batch;


public interface BatchTableRowGenerator {

	public TableRow[] GenerateLines(Batch[] batchArray, Context context);
}
