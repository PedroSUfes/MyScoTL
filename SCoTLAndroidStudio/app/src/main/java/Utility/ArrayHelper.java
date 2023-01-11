package Utility;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.function.Predicate;

public class ArrayHelper<T>
{
    public Boolean Exists(T toSearch, T[] array)
    {
        for(T element : array)
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
        for(T element : array)
        {
            if(predicate.test(element))
            {
                return true;
            }
        }

        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public T Find(T[] array, Predicate<T> predicate)
    {
        for(T element : array)
        {
            if(predicate.test(element))
            {
                return element;
            }
        }

        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<T> FindAll(T[] array, Predicate<T> predicate)
    {
        ArrayList toReturn = new ArrayList<T>();
        for(T element : array)
        {
            if(predicate.test(element))
            {
                toReturn.add(element);
            }
        }

        return toReturn;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ForAllThatDo(ArrayList<T> array, Predicate<T> predicate, Action1<T> action)
    {
        for(T element : array)
        {
            if(!predicate.test(element))
            {
                continue;
            }

            action.Invoke(element);
        }
    }
}
