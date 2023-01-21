package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import Frameworks.Utility.ChainOfResponsibilityHandler;
import Frameworks.Utility.CpfValidationHandle;
import Frameworks.Utility.EmptyDateHandle;
import Frameworks.Utility.MyDialog;
import Policy.Adapters.MyLog;
import Utility.Action;
import Utility.ChainOfResponsibilityHandle;

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

    DatePickerDialog birthDatePickerDialog;
    DatePickerDialog hiringDatePickerDialog;

    ArrayList<Action> toClearEditTextList = new ArrayList<>();

    ChainOfResponsibilityHandler<Boolean> verificationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employee);

        MyLog.SetLogAction((message)-> Toast.makeText(this, (CharSequence) message, Toast.LENGTH_LONG).show());

        GetReferences();
        BuildValidationChain();
        InitSpinner();

        DefineClearButtonEvents();
        DefineRegisterButtonEvents();
        DefineBirthDateButtonEvents();
        DefineHiringDateButtonEvents();

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

    private void BuildValidationChain()
    {
        ChainOfResponsibilityHandle<Boolean> cpfVerification = new CpfValidationHandle(cpfText);
        ChainOfResponsibilityHandle<Boolean> nameVerification = new EmptyDateHandle(nameText.getText()::toString, "Nenhum nome foi inserido");
        ChainOfResponsibilityHandle<Boolean> dateVerification = new EmptyDateHandle(birthDatePickerButton.getText()::toString, "Nenhuma data de aniversario foi inserida");
        ChainOfResponsibilityHandle<Boolean> cellphoneVerification = new EmptyDateHandle(cellphoneText.getText()::toString, "Nenhum telefone para contato inserido");

        verificationHandler = new ChainOfResponsibilityHandler<>(cpfVerification);
        verificationHandler.SetNext(nameVerification);
        verificationHandler.SetNext(dateVerification);
        verificationHandler.SetNext(cellphoneVerification);
    }
}