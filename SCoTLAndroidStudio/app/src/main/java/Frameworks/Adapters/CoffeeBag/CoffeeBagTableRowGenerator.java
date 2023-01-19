package Frameworks.Adapters.CoffeeBag;

import android.content.Context;
import android.widget.TableRow;

import Policy.Entity.CoffeeBag;

public interface CoffeeBagTableRowGenerator {

	public TableRow[] GeneratorLines(Context context, CoffeeBag[] coffeeBags);
}
