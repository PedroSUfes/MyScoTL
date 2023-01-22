package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import Frameworks.Adapters.TableRowGenerator;
import Frameworks.Utility.ChainOfResponsability.CpfValidationHandle;
import Policy.BusinessRules.CRUDEmployee;
import Policy.Entity.Employee;
import Utility.Func;

public class SystemClientReadEmployeeActivity extends AppCompatActivity
{
    Button registerButton;
    Button listEmployeeButton;
    TableLayout tableLayout;
    EditText cpfText;
    SwitchCompat withPastRegisterSwitch;

    // Relaciona (há algum valor no campo de filtro de cpf?) com o método para recuperar os funcionários
    ArrayMap<Boolean, Func<Employee[]>> getEmployeeMap = new ArrayMap<Boolean, Func<Employee[]>>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_employee_system_client);

        GetReferences();

        DefineGetEmployeeMap();
        DefineRegisterButtonEvents();
        DefineListEmployeeButtonEvents();
    }

    private void GetReferences()
    {
        registerButton = findViewById(R.id.system_client_read_employee_register_button);
        listEmployeeButton = findViewById(R.id.system_client_read_employee_list_button);
        tableLayout = findViewById(R.id.system_client_read_employee_table_layout);
        cpfText = findViewById(R.id.system_client_read_employee_cpf_edit_text);
        withPastRegisterSwitch = findViewById(R.id.system_client_read_employee_with_past_register_switch);
    }

    private void DefineGetEmployeeMap()
    {
        getEmployeeMap.put
                (
                        false,
                        () -> CRUDEmployee.GetEmployees(withPastRegisterSwitch.isChecked())
                );

        getEmployeeMap.put
                (
                        true,
                        () -> CRUDEmployee.GetEmployee(cpfText.getText().toString(), withPastRegisterSwitch.isChecked())
                );
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

                            Func<Employee[]> getEmployeeFunction = getEmployeeMap.get(!cpfText.getText().toString().isEmpty());
                            if(getEmployeeFunction == null)
                            {
                                return;
                            }

                            Employee[] employeeArray = getEmployeeFunction.Invoke();
                            if(employeeArray == null)
                            {
                                return;
                            }

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