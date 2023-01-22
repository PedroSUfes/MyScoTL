package Frameworks.Utility;

import java.util.Calendar;

import Utility.MonthsEnum;

public class Utils
{
    public static String GetDateFormat(int day, int month, int year)
    {
        MonthsEnum monthEnumValue = MonthsEnum.values()[month];
        return new String(day+" "+monthEnumValue.toString()+ " "+year);
    }

    public static String GetCurrentDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return GetDateFormat(day, month, year);
    }
}
