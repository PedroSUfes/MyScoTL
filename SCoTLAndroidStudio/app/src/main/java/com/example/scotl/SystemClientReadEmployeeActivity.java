package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import Policy.Adapters.MyLog;

public class SystemClientReadEmployeeActivity extends AppCompatActivity
{
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_employee_system_client);

        // Apagar
        MyLog.SetLogAction((message)-> Toast.makeText(this, (CharSequence) message, Toast.LENGTH_LONG).show());
    }

    private void GetReferences()
    {
        registerButton = findViewById(R.id.system_client_register_employee_register_button);
    }

    private void DefineRegisterButtonEvents()
    {
        if(registerButton == null)
        {
            return;
        }

        registerButton.setOnClickListener
                (
                        view ->
                        {

                        }
                );
    }
}