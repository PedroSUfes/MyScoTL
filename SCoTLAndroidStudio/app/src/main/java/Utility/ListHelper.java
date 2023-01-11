package Utility;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;

public class ListHelper<T>
{
    public Boolean Exists(T toSearch, List<T> list)
    {
        for(T element : list)
        {
            if(element == toSearch)
            {
                return true;
            }
        }

        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Boolean Exists(List<T> list, Predicate<T> predicate)
    {
        for(T element : list)
        {
            if(predicate.test(element))
            {
                return true;
            }
        }

        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public T Find(List<T> list, Predicate<T> predicate)
    {
        for(T element : list)
        {
            if(predicate.test(element))
            {
                return element;
            }
        }

        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<T> FindAllThat(List<T> list, Predicate<T> predicate)
    {
        ArrayList toReturn = new ArrayList<T>();

        for(T element : list)
        {
            if(predicate.test(element))
            {
                toReturn.add(element);
            }
        }

        return toReturn;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int GetIndexOf(List<T> list, Predicate<T> predicate)
    {
        int currentIndex = 0;
        for(T element : list)
        {
            if(predicate.test(element))
            {
                return currentIndex;
            }

            ++currentIndex;
        }

        return -1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ForAllThatDo(List<T> list, Predicate<T> predicate, Action1<T> action)
    {
        for(T element : list)
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
        for(T element : list)
        {
            if(element == null)
            {
                continue;
            }

            action.Invoke(element);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ReplaceThat(List<T> list, Predicate<T> predicate, T toPlace)
    {
        ListIterator<T> iterator = list.listIterator();
        while(iterator.hasNext())
        {
            if(predicate.test(iterator.next()))
            {
                iterator.set(toPlace);
            }
        }
    }
}
