package Frameworks.Utility.ChainOfResponsability;

import Policy.Adapters.MyLog;
import Utility.ChainOfResponsibilityHandle;
import Utility.Func;

public class IdValidationHandle
    extends
        ChainOfResponsibilityHandle<Boolean>
{
    Func<String> m_getTextFunction;

    EmptyDataHandle m_emptyDataHandle;

    public IdValidationHandle(Func<String> getTextFunction)
    {
        m_getTextFunction = getTextFunction;
        m_emptyDataHandle = new EmptyDataHandle(m_getTextFunction, "Id não preenchido");
    }

    @Override
    public Boolean Validate()
    {
        if(m_emptyDataHandle == null || m_getTextFunction == null)
        {
            return false;
        }

        if(!m_emptyDataHandle.Validate())
        {
            return false;
        }

        boolean result = m_getTextFunction.Invoke().length() == 4;
        if(!result)
        {
            MyLog.LogMessage("O ID precisa ter 4 caracteres numéricos");
            return false;
        }

        if(m_next != null)
        {
            return m_next.Validate();
        }

        return true;
    }
}
