package Frameworks.Adapters.PropertyRow;

import android.content.Context;
import android.widget.TableRow;

import Policy.Entity.Property;

public interface PropertyTableRowGenerator
{
    public TableRow[] GenerateLines(Property[] propertyArray , Context context);
}
