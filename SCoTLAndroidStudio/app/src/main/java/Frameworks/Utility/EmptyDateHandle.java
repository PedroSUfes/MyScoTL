package Frameworks.Utility;

import android.widget.EditText;

import Policy.Adapters.MyLog;
import Utility.ChainOfResponsibilityHandle;
import Utility.Func;

public class EmptyDateHandle
    extends
        ChainOfResponsibilityHandle<Boolean>
{
    private Func<String> m_getStringMethod;
    private String m_erroMessage;

    public EmptyDateHandle(Func<String> getStringMethod, String errorMessage)
    {
        if(getStringMethod != null)
        {
            m_getStringMethod = getStringMethod;
        }
        if(errorMessage != null)
        {
            m_erroMessage = new String(errorMessage);
        }
    }

    @Override
    public Boolean Validate()
    {
        if(m_getStringMethod == null || m_erroMessage == null)
        {
            return false;
        }

        String text = m_getStringMethod.Invoke();
        if(text == null || text.isEmpty())
        {
            MyLog.LogMessage(m_erroMessage);
            return false;
        }

        if(m_next != null)
        {
            return m_next.Validate();
        }

        return true;
    }
}
