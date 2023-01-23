package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import Frameworks.Utility.ChainOfResponsability.ChainOfResponsibilityHandler;
import Frameworks.Utility.ChainOfResponsability.CpfValidationHandle;
import Frameworks.Utility.ChainOfResponsability.EmptyDataHandle;
import Frameworks.Utility.ChainOfResponsability.IdValidationHandle;
import Frameworks.Utility.InterfaceClasses;
import Frameworks.Utility.MyDialog;
import Frameworks.Utility.Utils;
import Policy.BusinessRules.CRUDWarehouse;
import Policy.Entity.Person;
import Policy.Entity.Warehouse;
import Utility.Action;

public class RegisterWarehouse extends AppCompatActivity
{
    private EditText m_idText;
    private EditText m_stateNameText;
    private EditText m_cityNameText;
    private EditText m_streetNameText;
    private EditText m_residentialNumberText;
    private EditText m_ownerCpfText;
    private EditText m_ownerNameText;
    private EditText m_ownerCellphoneText;

    private Button m_clearButton;
    private Button m_registerButton;

    private Button m_birthDateButton;

    private DatePickerDialog m_birthDatePickerDialog;

    ChainOfResponsibilityHandler<Boolean> validationHandler;

    ArrayList<Action> toClearActionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_warehouse);

        GetReferences();
        DefineValidationHandler();
        DefineClearActionList();

        DefineClearButtonEvents();
        DefineRegisterButtonEvents();

        m_birthDatePickerDialog = MyDialog.GetDatePickerDialog(this, m_birthDateButton, true);

        m_birthDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        DefineDatePickerDialogEvents(m_birthDateButton, m_birthDatePickerDialog);
    }

    private void DefineValidationHandler()
    {
        validationHandler = new ChainOfResponsibilityHandler<Boolean>(new IdValidationHandle(() -> m_idText.getText().toString()));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_stateNameText.getText().toString(), "Nenhum nome foi informado"));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_cityNameText.getText().toString(), "Nenhuma cidade foi informada"));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_streetNameText.getText().toString(), "Nenhuma rua foi informada"));
        validationHandler.SetNext(new CpfValidationHandle(m_ownerCpfText));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_ownerNameText.getText().toString(), "Nenhum nome foi informado para o dono"));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_ownerCellphoneText.getText().toString(), "Nenhum telefone foi informado para o dono"));
    }

    private void DefineDatePickerDialogEvents(Button button, DatePickerDialog datePickerDialog)
    {
        if(button == null || datePickerDialog == null)
        {
            return;
        }

        button.setOnClickListener
                (
                        view -> datePickerDialog.show()
                );
    }

    private void DefineClearActionList()
    {
        toClearActionList.add(() -> m_idText.getText().clear());
        toClearActionList.add(() -> m_stateNameText.getText().clear());
        toClearActionList.add(() -> m_cityNameText.getText().clear());
        toClearActionList.add(() -> m_streetNameText.getText().clear());
        toClearActionList.add(() -> m_ownerCpfText.getText().clear());
        toClearActionList.add(() -> m_ownerNameText.getText().clear());
        toClearActionList.add(() -> m_ownerCellphoneText.getText().clear());
    }

    private void GetReferences()
    {
        m_idText = findViewById(R.id.system_client_register_warehouse_id_edit_text);
        m_stateNameText = findViewById(R.id.system_client_register_warehouse_state_name_edit_text);
        m_cityNameText = findViewById(R.id.system_client_register_warehouse_city_name_edit_text);
        m_streetNameText = findViewById(R.id.system_client_register_warehouse_street_name_edit_text);
        m_residentialNumberText = findViewById(R.id.system_client_register_warehouse_number_edit_text);
        m_ownerCpfText = findViewById(R.id.system_client_register_warehouse_owner_cpf_edit_text);
        m_ownerNameText = findViewById(R.id.system_client_register_warehouse_owner_name_edit_text);
        m_ownerCellphoneText = findViewById(R.id.system_client_register_warehouse_cellphone_edit_text);

        m_clearButton = findViewById(R.id.system_client_register_warehouse_clear_button);
        m_registerButton = findViewById(R.id.system_client_register_warehouse_register_button);

        m_birthDateButton = findViewById(R.id.system_client_register_warehouse_pick_date_button);
    }

    private void DefineClearButtonEvents()
    {
        if(m_clearButton == null)
        {
            return;
        }

        m_clearButton.setOnClickListener
                (
                        view ->
                        {
                            for(Action a : toClearActionList)
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

    private void DefineRegisterButtonEvents()
    {
        if(m_registerButton == null)
        {
            return;
        }

        m_registerButton.setOnClickListener
                (
                        view ->
                        {
                            if(!validationHandler.Validate())
                            {
                                return;
                            }

                            Person owner = new Person
                                    (
                                            m_ownerCpfText.getText().toString(),
                                            m_ownerNameText.getText().toString(),
                                            m_ownerCellphoneText.getText().toString(),
                                            m_birthDateButton.getText().toString()
                                    );

                            Warehouse warehouse = new Warehouse
                                    (
                                            m_idText.getText().toString(),
                                            m_stateNameText.getText().toString(),
                                            m_cityNameText.getText().toString(),
                                            m_streetNameText.getText().toString(),
                                            Integer.parseInt(m_residentialNumberText.getText().toString()),
                                            Utils.GetCurrentDate(),
                                            null,
                                            owner
                                    );

                            if(CRUDWarehouse.TryRegisterWarehouse(warehouse))
                            {
                                startActivity(new Intent(this, InterfaceClasses.warehouseClass));
                            }
                        }
                );
    }
}