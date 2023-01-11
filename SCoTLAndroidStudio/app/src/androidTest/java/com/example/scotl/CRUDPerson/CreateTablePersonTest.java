package com.example.scotl.CRUDPerson;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.scotl.MainActivity;

import org.junit.Test;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.Entity.Person;

public class CreateTablePersonTest
{
    private SQLiteDAO database = null;

    @Test
    public void Main()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        database = new SQLiteDAO(appContext);

//        database.Test();
        Person[] persons = database.GetAllPersons();
        for(Person p : persons)
        {
            if (p == null)
            {
                continue;
            }

            System.out.println(p);
        }
    }
}
