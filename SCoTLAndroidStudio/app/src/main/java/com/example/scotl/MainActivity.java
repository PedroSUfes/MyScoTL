package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Person;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}