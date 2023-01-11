package Utility;

@FunctionalInterface
public interface Func1<param_type, TReturn> 
{
    public TReturn Invoke(param_type param);
}
