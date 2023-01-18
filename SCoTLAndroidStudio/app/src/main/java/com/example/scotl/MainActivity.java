package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.SQLSyntaxErrorException;

import Frameworks.Database.SQLite.SQLiteDAO;
import Policy.BusinessRules.DatabaseAccess;
import Policy.Entity.Person;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.buttonLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "AOBA BOBA", Toast.LENGTH_LONG).show();
                openActivity2();
            }
        });

        SQLiteDAO database = new SQLiteDAO(this);
        System.out.println(database.getDatabaseName());
    }

    public void openActivity2(){
        Intent intent = new Intent(this, Teste.class);
        startActivity(intent);
    }
}