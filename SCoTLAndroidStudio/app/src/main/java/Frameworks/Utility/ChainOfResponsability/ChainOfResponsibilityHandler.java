package Frameworks.Utility.ChainOfResponsability;

import java.util.ArrayList;

import Utility.ChainOfResponsibilityHandle;

public class ChainOfResponsibilityHandler<T>
{
    private final ArrayList<ChainOfResponsibilityHandle<T>> m_handleList = new ArrayList<>();

    public ChainOfResponsibilityHandler(ChainOfResponsibilityHandle<T> first)
    {
        m_handleList.add(first);
    }

    public void SetNext(ChainOfResponsibilityHandle<T> next)
    {
        ChainOfResponsibilityHandle<T> last = m_handleList.get(m_handleList.size() - 1);
        last.SetNext(next);
        m_handleList.add(next);
    }

    public T Validate()
    {
        if(m_handleList.isEmpty())
        {
            return null;
        }

        ChainOfResponsibilityHandle<T> first = m_handleList.get(0);
        return first.Validate();
    }
}
