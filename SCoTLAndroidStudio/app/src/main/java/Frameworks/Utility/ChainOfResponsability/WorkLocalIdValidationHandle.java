package Frameworks.Utility.ChainOfResponsability;

import Policy.Adapters.MyLog;
import Utility.ChainOfResponsibilityHandle;
import Utility.Func;

public class WorkLocalIdValidationHandle
        extends
            ChainOfResponsibilityHandle<Boolean>
{
    Func<String> m_getStringFunc;
    EmptyDataHandle m_emptyDataHandle;

    public WorkLocalIdValidationHandle(Func<String> getStringFunc)
    {
        m_getStringFunc = getStringFunc;
        m_emptyDataHandle = new EmptyDataHandle(m_getStringFunc, "Id do local de trabalho não informado");
    }

    @Override
    public Boolean Validate()
    {
        Boolean resultEmpty = m_emptyDataHandle.Validate();
        if(!resultEmpty)
        {
            return false;
        }

        if(m_getStringFunc.Invoke().length() != 4)
        {
            MyLog.LogMessage("ID de local de trabalho inválido - 4 caracteres numéricos");
            return false;
        }

        if(m_next != null)
        {
            return m_next.Validate();
        }

        return true;
    }
}
