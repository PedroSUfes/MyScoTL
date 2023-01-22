package Frameworks.Utility.ChainOfResponsability;

import Policy.Adapters.MyLog;
import Utility.ChainOfResponsibilityHandle;
import Utility.Func;

public class EmptyDataHandle
    extends
        ChainOfResponsibilityHandle<Boolean>
{
    private Func<String> m_getStringMethod;
    private String m_errorMessage;

    public EmptyDataHandle(Func<String> getStringMethod, String errorMessage)
    {
        if(getStringMethod != null)
        {
            m_getStringMethod = getStringMethod;
        }
        if(errorMessage != null)
        {
            m_errorMessage = new String(errorMessage);
        }
    }

    @Override
    public Boolean Validate()
    {
        if(m_getStringMethod == null || m_errorMessage == null)
        {
            return false;
        }

        String text = m_getStringMethod.Invoke();
        if(text == null || text.isEmpty())
        {
            MyLog.LogMessage(m_errorMessage);
            return false;
        }

        if(m_next != null)
        {
            return m_next.Validate();
        }

        return true;
    }
}
