package Utility;

import java.util.ArrayList;
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

    public ArrayList<T> FindAllThat(List<T> list, Predicate<T> predicate)
    {
        var toReturn = new ArrayList<T>();

        for(var element : list)
        {
            if(predicate.test(element))
            {
                toReturn.add(element);
            }
        }

        return toReturn;
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

    public void ForAllDo(List<T> list, Action1<T> action)
    {
        for(var element : list)
        {
            if(element == null)
            {
                continue;
            }

            action.Invoke(element);
        }
    }

    public void ReplaceThat(List<T> list, Predicate<T> predicate, T toPlace)
    {
        var iterator = list.listIterator();
        while(iterator.hasNext())
        {
            var next = iterator.next();
            if(predicate.test(next))
            {
                iterator.set(toPlace);
            }
        }
    }
}
