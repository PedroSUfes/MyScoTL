package Utility;

@FunctionalInterface
public interface Func2<param_type1, param_type2, return_type>
{
    public return_type Invoke(param_type1 p1, param_type2 p2);
}
