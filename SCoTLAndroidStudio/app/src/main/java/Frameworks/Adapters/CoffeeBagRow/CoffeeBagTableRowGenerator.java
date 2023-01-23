package Frameworks.Adapters.CoffeeBagRow;
import android.content.Context;
import android.widget.TableRow;

import Policy.Entity.CoffeeBag;


public interface CoffeeBagTableRowGenerator {

	public TableRow[] GenerateLines(CoffeeBag[] coffeeBags, Context context);
}
