package Utility;

import java.util.List;
import java.util.function.Predicate;

public class ListHelper<T>
{
    public Boolean Exists(T toSearch, List<T> list)
    {
        for(var element : list)
        {
            if(element == toSearch)
            {
                return true;
            }
        }

        return false;
    }

    public Boolean Exists(List<T> list, Predicate<T> predicate)
    {
        for(var element : list)
        {
            if(predicate.test(element))
            {
                return true;
            }
        }

        return false;
    }

    public T Find(List<T> list, Predicate<T> predicate)
    {
        for(var element : list)
        {
            if(predicate.test(element))
            {
                return element;
            }
        }

        return null;
    }

    public int GetIndexOf(List<T> list, Predicate<T> predicate)
    {
        int currentIndex = 0;
        for(var element : list)
        {
            if(predicate.test(element))
            {
                return currentIndex;
            }

            ++currentIndex;
        }

        return -1;
    }

    public void ForAllThatDo(List<T> list, Predicate<T> predicate, Action1<T> action)
    {
        for(var element : list)
        {
            if(!predicate.test(element))
            {
                continue;
            }

            action.Invoke(element);
        }
    }
}
