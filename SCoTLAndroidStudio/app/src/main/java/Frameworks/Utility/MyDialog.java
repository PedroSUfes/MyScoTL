package Frameworks.Utility;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class MyDialog
{
    public static DatePickerDialog GetDatePickerDialog(Context context, Button dateButton, Boolean insertCurrentDate)
    {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) ->
        {
            String date = Utils.GetDateFormat(day, month, year);
            dateButton.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        if(insertCurrentDate)
        {
            dateButton.setText(Utils.GetDateFormat(day, month, year));
        }

        int style = AlertDialog.THEME_HOLO_LIGHT;

        return new DatePickerDialog(context, style, dateSetListener, year, month, day);
    }
}
