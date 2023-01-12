package Policy.Adapters;

import Utility.Action1;

public class MyLog
{
    private static Action1<String> log = (message) -> System.out.println(message+"\n\n");

    public static void LogMessage(String message)
    {
        if(message == null || message.isEmpty())
        {
            return;
        }

        log.Invoke(message);
    }
    public static void SetLogAction(Action1 ac)
    {
        if(ac == null)
        {
            return;
        }

        log = ac;
    }
}
