package Utility;

@FunctionalInterface
public interface Func<TReturn> 
{
    public TReturn Invoke();     
}
