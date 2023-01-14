package Utility;

@FunctionalInterface
public interface Func3<param_type1, param_type2, param_type3, return_type>
{
    public return_type Invoke(param_type1 p1, param_type2 p2, param_type3 p3);
}
