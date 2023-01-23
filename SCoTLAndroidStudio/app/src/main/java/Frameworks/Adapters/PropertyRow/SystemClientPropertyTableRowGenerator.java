package Frameworks.Adapters.PropertyRow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.scotl.UpdateAndRemovePropertyActivity;

import java.util.ArrayList;

import Policy.Entity.Property;

public class SystemClientPropertyTableRowGenerator
    implements
        PropertyTableRowGenerator
{
    public SystemClientPropertyTableRowGenerator() {}

    @Override
    public TableRow[] GenerateLines(Property[] propertyArray, Context context)
    {
        if(propertyArray == null)
        {
            return null;
        }

        ArrayList<TableRow> tableRowsArrayList = new ArrayList<TableRow>();
        for(Property p : propertyArray)
        {
            if(p == null)
            {
                continue;
            }

            TableRow tableRow = new TableRow(context);

            TextView idText = new TextView(context);
            ConfigureTextView(idText, p.GetId());

            TextView nameText = new TextView(context);
            ConfigureTextView(nameText, p.GetPropertyName());

            TextView stateText = new TextView(context);
            ConfigureTextView(stateText, p.GetStateName());

            tableRow.addView(idText);
            tableRow.addView(nameText);
            tableRow.addView(stateText);

            tableRow.setOnClickListener
                    (
                            view ->
                            {

                                UpdateAndRemovePropertyActivity.SetPropertyAttributes(p);
                                context.startActivity(new Intent(context, UpdateAndRemovePropertyActivity.class));
                            }
                    );

            tableRowsArrayList.add(tableRow);
        }

        if(tableRowsArrayList.isEmpty())
        {
            return null;
        }

        TableRow[] toReturn = new TableRow[tableRowsArrayList.size()];
        int index = -1;
        for(TableRow t : tableRowsArrayList)
        {
            ++index;
            if(t == null)
            {
                continue;
            }

            toReturn[index] = t;
        }

        return toReturn;
    }

    private void ConfigureTextView(TextView textView, String content)
    {
        textView.setText(content);
        textView.setTextColor(Color.parseColor("#2F4F4F"));
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        textView.setVisibility(View.VISIBLE);
        textView.setPadding(30, 30, 30, 30);
        textView.setTextSize(15);
    }
}
