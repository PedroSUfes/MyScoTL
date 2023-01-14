package Utility;

@FunctionalInterface
public interface Action2<param_type1, param_type2>
{
    public void Invoke(param_type1 p1, param_type2 p2);
}
