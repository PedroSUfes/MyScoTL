package Frameworks.Adapters.BatchRow;
import android.content.Context;
import android.widget.TableRow;


public interface BatchTableRowGenerator {

	public TableRow[] GenerateLines(Context context);
}
