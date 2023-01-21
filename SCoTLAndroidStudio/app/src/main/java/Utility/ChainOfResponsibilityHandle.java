package Utility;

public abstract class ChainOfResponsibilityHandle<validation_return_type>
{
    protected ChainOfResponsibilityHandle<validation_return_type> m_next;

    public abstract validation_return_type Validate();

    public void SetNext(ChainOfResponsibilityHandle next)
    {
        m_next = next;
    }
}
