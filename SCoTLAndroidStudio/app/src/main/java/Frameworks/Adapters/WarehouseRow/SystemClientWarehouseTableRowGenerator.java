package Frameworks.Adapters.WarehouseRow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.scotl.UpdateAndRemoveWarehouseActivity;

import java.util.ArrayList;
import Policy.Entity.Warehouse;


public class SystemClientWarehouseTableRowGenerator
    implements
        WarehouseTableRowGenerator
{
    public SystemClientWarehouseTableRowGenerator() {}

    @Override
    public TableRow[] GenerateLines(Warehouse[] warehouseArray, Context context)
    {
        if (warehouseArray == null)
        {
            return null;
        }

        ArrayList<TableRow> tableRowList = new ArrayList<TableRow>();


        for(Warehouse e : warehouseArray)
        {
            if(e == null)
            {
                continue;
            }
            TableRow tableRow = new TableRow(context);

            TextView IdWarehouseText = new TextView(context);
            ConfigureTextView(IdWarehouseText, e.GetId());



            TextView dataText = new TextView(context);
            ConfigureTextView(dataText, e.GetBeginDate());

            TextView cpfPropText = new TextView(context);
            ConfigureTextView(cpfPropText, e.GetOwner().GetCpf());

            tableRow.addView(IdWarehouseText);
            tableRow.addView(cpfPropText);

            //Adicionar evento de Update
            tableRow.setOnClickListener
                    (
                            view ->
                            {
                                //UpdateAndRemoveWarehouseActivity;
                                //UpdateAndRemoveWarehouseActivity.SetName(e.GetName());
                                //UpdateAndRemoveWarehouseActivity.SetBirthDate(e.GetBirthDate());
                                //UpdateAndRemoveWarehouseActivity.SetCellphone(e.GetCellphone());
                                //UpdateAndRemoveWarehouseActivity.SetHiringDate(e.GetHiringDate());
                                //UpdateAndRemoveWarehouseActivity.SetEndDate(e.GetEndDate());
                                //UpdateAndRemoveWarehouseActivity.SetProfession(e.GetEmployeeType().toString());

                                //context.startActivity(new Intent(context, UpdateAndRemoveEmployeeActivity.class));
                            }
                    );

            tableRowList.add(tableRow);
        }

        if(tableRowList.isEmpty())
        {
            return null;
        }

        TableRow[] toReturn = new TableRow[tableRowList.size()];
        int index = -1;
        for(TableRow tb : tableRowList)
        {
            ++index;
            if(tb == null)
            {
                continue;
            }

            toReturn[index] = tb;
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