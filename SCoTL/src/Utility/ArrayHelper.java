package Utility;

import java.util.function.Predicate;

public class ArrayHelper<T>
{
    public Boolean Exists(T toSearch, T[] array)
    {
        for(var element : array)
        {
            if(element == toSearch)
            {
                return true;
            }
        }

        return false;
    }

    public Boolean Exists(T[] array, Predicate<T> predicate)
    {
        for(var element : array)
        {
            if(predicate.test(element))
            {
                return true;
            }
        }

        return false;
    }

    public T Find(T[] array, Predicate<T> predicate)
    {
        for(var element : array)
        {
            if(predicate.test(element))
            {
                return element;
            }
        }

        return null;
    }
}
