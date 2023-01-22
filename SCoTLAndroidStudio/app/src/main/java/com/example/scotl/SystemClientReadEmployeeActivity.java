package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import Frameworks.Adapters.TableRowGenerator;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.CRUDEmployee;
import Policy.Entity.Employee;

public class SystemClientReadEmployeeActivity extends AppCompatActivity
{
    Button registerButton;
    Button listEmployeeButton;
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_employee_system_client);

        GetReferences();

        DefineRegisterButtonEvents();
        DefineListEmployeeButtonEvents();
    }

    private void GetReferences()
    {
        registerButton = findViewById(R.id.system_client_read_employee_register_button);
        listEmployeeButton = findViewById(R.id.system_client_read_employee_list_button);
        tableLayout = findViewById(R.id.system_client_read_employee_table_layout);
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
                            startActivity(new Intent(this, RegisterEmployeeActivity.class));
                        }
                );
    }

    private void DefineListEmployeeButtonEvents()
    {
        if(listEmployeeButton == null)
        {
            return;
        }

        listEmployeeButton.setOnClickListener
                (
                        view ->
                        {
                            if(tableLayout == null)
                            {
                                return;
                            }

                            tableLayout.removeViews(1, tableLayout.getChildCount() - 1);

                            Employee[] employeeArray = CRUDEmployee.GetEmployees(true);
                            TableRow[] tableRowArray = TableRowGenerator.GetEmployeeTableRows(employeeArray, this);

                            if(tableRowArray == null)
                            {
                                return;
                            }

                            for(TableRow tb : tableRowArray)
                            {
                                if(tb == null)
                                {
                                    continue;
                                }

                                tableLayout.addView(tb);
                            }
                        }
                );
    }
}