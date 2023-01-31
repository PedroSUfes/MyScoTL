package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Frameworks.Utility.ChainOfResponsability.ChainOfResponsibilityHandler;
import Frameworks.Utility.ChainOfResponsability.CpfValidationHandle;
import Frameworks.Utility.ChainOfResponsability.EmptyDataHandle;
import Frameworks.Utility.ChainOfResponsability.WorkLocalIdValidationHandle;
import Frameworks.Utility.MyDialog;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.CRUDEmployee;
import Policy.Entity.Employee;
import Policy.Entity.Property;
import Policy.Entity.Servant;
import Policy.Entity.Warehouse;
import Policy.Entity.WarehouseManager;
import Utility.Action;
import Utility.ChainOfResponsibilityHandle;
import Utility.Func;
import Utility.Func1;

public class RegisterEmployeeActivity extends AppCompatActivity
{
    Button registerButton;
    Button clearButton;
    Button birthDatePickerButton;
    Button hiringDatePickerButton;
    EditText cpfText;
    EditText nameText;
    EditText cellphoneText;
    Spinner spinner;
    TextView workLocalTextView;
    EditText workLocalEditText;

    DatePickerDialog birthDatePickerDialog;
    DatePickerDialog hiringDatePickerDialog;

    ArrayList<Action> toClearEditTextList = new ArrayList<>();

    ChainOfResponsibilityHandler<Boolean> verificationHandler;

    // Mapa que relaciona o conteúdo do sippner ao texto que precisa ser exibido em workLocalTextView
    ArrayMap<String, String> workLocalMap = new ArrayMap<>();

    // Mapa que relaciona o conteúdo do spinner à função que será usada para registro
    ArrayMap<String, Func1<Employee, Boolean>> registerMap = new ArrayMap<>();

    // Mapa que relaciona o conteúdo do spinner ao funcionário que precisa ser instanciado
    ArrayMap<String, Func<Employee>> getEmployeeMap = new ArrayMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employee);

        GetReferences();
        BuildValidationChain();
        InitSpinner();

        DefineClearButtonEvents();
        DefineRegisterButtonEvents();
        DefineBirthDateButtonEvents();
        DefineHiringDateButtonEvents();
        DefineSpinnerEvents();

        birthDatePickerDialog = MyDialog.GetDatePickerDialog(this, birthDatePickerButton, true);
        hiringDatePickerDialog = MyDialog.GetDatePickerDialog(this, hiringDatePickerButton, true);

        birthDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        hiringDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private void GetReferences()
    {
        registerButton = findViewById(R.id.system_client_register_employee_register_button);
        clearButton = findViewById(R.id.system_client_register_employee_clear_button);
        birthDatePickerButton = findViewById(R.id.system_client_register_employee_pick_date_button);
        hiringDatePickerButton = findViewById(R.id.system_client_register_employee_pick_hiring_date_button);

        cpfText = findViewById(R.id.system_client_register_employee_cpf_edit_text);
        nameText = findViewById(R.id.system_client_register_employee_name_edit_text);
        cellphoneText = findViewById(R.id.system_client_register_employee_cellphone_edit_text);

        spinner = findViewById(R.id.system_client_register_employee_spinner);

        workLocalTextView = findViewById(R.id.system_client_register_employee_work_local_text_view);
        workLocalEditText = findViewById(R.id.system_client_register_employee_work_local_id_edit_text);

        toClearEditTextList.add(cpfText.getText()::clear);
        toClearEditTextList.add(nameText.getText()::clear);
        toClearEditTextList.add(cellphoneText.getText()::clear);
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
                            if(!verificationHandler.Validate())
                            {
                                return;
                            }
                            String selectedItem = (String) spinner.getSelectedItem();
                            Func<Employee> getEmployeeFunc = getEmployeeMap.get(selectedItem);
                            if(getEmployeeFunc == null)
                            {
                                System.out.println("Fail to retrieve get employee function");
                                return;
                            }

                            Employee employee = getEmployeeFunc.Invoke();

                            Func1<Employee, Boolean> registerFunc = registerMap.get(selectedItem);
                            if(registerFunc == null)
                            {
                                System.out.println("Fail to retrieve register function");
                                return;
                            }

                            if(registerFunc.Invoke(employee))
                            {
                                startActivity(new Intent(this, SystemClientReadEmployeeActivity.class));
                            }
                        }
                );
    }

    private void InitSpinner()
    {
        if(spinner == null)
        {
            return;
        }

        String[] professions = getResources().getStringArray(R.array.professions);
        ArrayAdapter arrayAdapter = new ArrayAdapter
                (
                        this,
                        R.layout.spinner_item,
                        professions
                );
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        workLocalMap.put(professions[0], "Id do galpão");
        workLocalMap.put(professions[1], "Id da propriedade");

        registerMap.put(professions[0], (employee) -> CRUDEmployee.TryRegisterWarehouseManager((WarehouseManager) employee));
        registerMap.put(professions[1], (employee) -> CRUDEmployee.TryRegisterServant((Servant) employee));

        getEmployeeMap.put
                (
                        professions[0],
                        () ->
                            new WarehouseManager
                                    (
                                            cpfText.getText().toString(),
                                            nameText.getText().toString(),
                                            cellphoneText.getText().toString(),
                                            birthDatePickerButton.getText().toString(),
                                            hiringDatePickerButton.getText().toString(),
                                            null,
                                            new Warehouse(workLocalEditText.getText().toString())
                                    )

                );

        getEmployeeMap.put
                (
                        professions[1],
                        () ->
                            new Servant
                                    (
                                            cpfText.getText().toString(),
                                            nameText.getText().toString(),
                                            cellphoneText.getText().toString(),
                                            birthDatePickerButton.getText().toString(),
                                            hiringDatePickerButton.getText().toString(),
                                            null,
                                            new Property(workLocalEditText.getText().toString())
                                    )
                );
    }

    private void DefineClearButtonEvents()
    {
        if(clearButton == null)
        {
            return;
        }

        clearButton.setOnClickListener
                (
                        View ->
                        {
                            for(Action a : toClearEditTextList)
                            {
                                if(a == null)
                                {
                                    continue;
                                }

                                a.Invoke();
                            }
                        }
                );
    }

    private void DefineBirthDateButtonEvents()
    {
        if(birthDatePickerButton == null)
        {
            return;
        }

        birthDatePickerButton.setOnClickListener
                (
                        view ->
                        {
                            if(birthDatePickerDialog == null)
                            {
                                return;
                            }

                            birthDatePickerDialog.show();
                        }
                );
    }

    private void DefineHiringDateButtonEvents()
    {
        if(hiringDatePickerButton == null)
        {
            return;
        }

        hiringDatePickerButton.setOnClickListener
                (
                        view ->
                        {
                            if(birthDatePickerDialog == null)
                            {
                                return;
                            }

                            birthDatePickerDialog.show();
                        }
                );
    }

    private void DefineSpinnerEvents()
    {
        if(spinner == null)
        {
            return;
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                workLocalTextView.setText(workLocalMap.get((String) spinner.getSelectedItem()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    private void BuildValidationChain()
    {
        ChainOfResponsibilityHandle<Boolean> cpfVerification = new CpfValidationHandle(cpfText);
        ChainOfResponsibilityHandle<Boolean> nameVerification = new EmptyDataHandle(() -> nameText.getText().toString(), "Nenhum nome foi inserido");
        ChainOfResponsibilityHandle<Boolean> dateVerification = new EmptyDataHandle(() -> birthDatePickerButton.getText().toString(), "Nenhuma data de aniversario foi inserida");
        ChainOfResponsibilityHandle<Boolean> cellphoneVerification = new EmptyDataHandle(() -> cellphoneText.getText().toString(), "Nenhum telefone para contato inserido");
        ChainOfResponsibilityHandle<Boolean> workLocalIdVerification = new WorkLocalIdValidationHandle(() -> workLocalEditText.getText().toString());

        verificationHandler = new ChainOfResponsibilityHandler<>(cpfVerification);
        verificationHandler.SetNext(nameVerification);
        verificationHandler.SetNext(dateVerification);
        verificationHandler.SetNext(cellphoneVerification);
        verificationHandler.SetNext(workLocalIdVerification);
    }
}