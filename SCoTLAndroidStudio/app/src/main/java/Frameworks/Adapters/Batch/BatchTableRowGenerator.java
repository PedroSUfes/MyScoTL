package Frameworks.Adapters.Batch;
import android.content.Context;
import android.widget.TableRow;

import Policy.Entity.Batch;


public interface BatchTableRowGenerator {

	public TableRow[] GeneratorLines(Context context, Batch[] batches);
}
