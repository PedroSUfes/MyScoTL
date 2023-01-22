package Frameworks.Utility;

import Utility.MonthsEnum;

public class Utils
{
    public static String GetDateFormat(int day, int month, int year)
    {
        MonthsEnum monthEnumValue = MonthsEnum.values()[month];
        return new String(day+" "+monthEnumValue.toString()+ " "+year);
    }
}
