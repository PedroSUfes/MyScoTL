package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import Frameworks.Utility.ChainOfResponsability.ChainOfResponsibilityHandler;
import Frameworks.Utility.ChainOfResponsability.CpfValidationHandle;
import Frameworks.Utility.ChainOfResponsability.EmptyDataHandle;
import Frameworks.Utility.InterfaceClasses;
import Frameworks.Utility.Utils;
import Policy.BusinessRules.CRUDWarehouse;
import Policy.Entity.Person;
import Policy.Entity.Warehouse;
import Utility.Action;

public class UpdateAndRemoveWarehouseActivity extends AppCompatActivity
{
    private static Warehouse m_warehouse;

    private EditText m_idText;
    private EditText m_stateNameText;
    private EditText m_cityNameText;
    private EditText m_streetNameText;
    private EditText m_residentialNumberText;
    private EditText m_ownerCpfText;
    private EditText m_ownerNameText;
    private EditText m_ownerCellphoneText;
    private EditText m_birthDateText;
    private EditText m_endDateText;

    private Button m_clearButton;
    private Button m_updateButton;
    private Button m_removeButton;

    ArrayList<Action> toClearActionList = new ArrayList<>();

    ChainOfResponsibilityHandler<Boolean> validationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_remove_warehouse);

        GetReferences();
        DefineClearActionList();
        DefineValidationHandler();

        DefineClearButtonEvents();
        DefineUpdateButtonEvents();
        DefineRemoveButtonEvents();
    }

    @Override
    protected void onStart() {
        super.onStart();

        SetWarehouseAttributesToView();
    }

    public static void SetWarehouse(Warehouse warehouse)
    {
        if(warehouse == null)
        {
            System.out.println("Null warehouse");
            return;
        }

        m_warehouse = warehouse;
    }

    public void SetWarehouseAttributesToView()
    {
        if(m_warehouse == null)
        {
            return;
        }

        m_idText.setText(m_warehouse.GetId());
        m_stateNameText.setText(m_warehouse.GetStateName());
        m_cityNameText.setText(m_warehouse.GetCityName());
        m_streetNameText.setText(m_warehouse.GetStateName());
        m_residentialNumberText.setText(((Integer) m_warehouse.GetNumber()).toString());
        m_ownerCpfText.setText(m_warehouse.GetOwner().GetCpf());
        m_ownerNameText.setText(m_warehouse.GetOwner().GetName());
        m_ownerCellphoneText.setText(m_warehouse.GetOwner().GetCellphone());
        m_birthDateText.setText(m_warehouse.GetOwner().GetBirthDate());
        m_endDateText.setText(m_warehouse.GetEndDate());
    }

    private void DefineValidationHandler()
    {
        validationHandler = new ChainOfResponsibilityHandler<>(new EmptyDataHandle(() -> m_stateNameText.getText().toString(), "Nenhum nome foi informado"));
        validationHandler.SetNext(new CpfValidationHandle(m_ownerCpfText));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_cityNameText.getText().toString(), "Nenhuma cidade foi informada"));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_streetNameText.getText().toString(), "Nenhuma rua foi informada"));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_ownerNameText.getText().toString(), "Nenhum nome foi informado para o dono"));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_ownerCellphoneText.getText().toString(), "Nenhum telefone foi informado para o dono"));
    }

    private void GetReferences()
    {
        m_idText = findViewById(R.id.system_client_update_warehouse_id_edit_text);
        m_stateNameText = findViewById(R.id.system_client_update_warehouse_state_name_edit_text);
        m_cityNameText = findViewById(R.id.system_client_update_warehouse_city_name_edit_text);
        m_streetNameText = findViewById(R.id.system_client_update_warehouse_street_name_edit_text);
        m_residentialNumberText = findViewById(R.id.system_client_update_warehouse_number_edit_text);
        m_ownerCpfText = findViewById(R.id.system_client_update_warehouse_owner_cpf_edit_text);
        m_ownerNameText = findViewById(R.id.system_client_update_warehouse_owner_name_edit_text);
        m_ownerCellphoneText = findViewById(R.id.system_client_update_warehouse_cellphone_edit_text);
        m_birthDateText = findViewById(R.id.system_client_update_warehouse_birth_date_edit_text);
        m_endDateText = findViewById(R.id.system_client_update_warehouse_end_date_edit_text);

        m_clearButton = findViewById(R.id.system_client_update_warehouse_clear_button);
        m_updateButton = findViewById(R.id.system_client_update_warehouse_update_button);
        m_removeButton = findViewById(R.id.system_client_update_warehouse_remove_button);
    }

    private void DefineClearActionList()
    {
        toClearActionList.add(() -> m_stateNameText.getText().clear());
        toClearActionList.add(() -> m_cityNameText.getText().clear());
        toClearActionList.add(() -> m_streetNameText.getText().clear());
        toClearActionList.add(() -> m_ownerNameText.getText().clear());
        toClearActionList.add(() -> m_ownerCellphoneText.getText().clear());
        toClearActionList.add(() -> m_residentialNumberText.getText().clear());
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

    private void DefineUpdateButtonEvents()
    {
        if(m_updateButton == null)
        {
            return;
        }

        m_updateButton.setOnClickListener
                (
                        view ->
                        {
                            if(!validationHandler.Validate())
                            {
                                return;
                            }

                            if
                            (
                                    CRUDWarehouse.TryUpdateWarehouse
                                            (
                                                    new Warehouse
                                                            (
                                                                    m_idText.getText().toString(),
                                                                    m_stateNameText.getText().toString(),
                                                                    m_cityNameText.getText().toString(),
                                                                    m_streetNameText.getText().toString(),
                                                                    Integer.parseInt(m_residentialNumberText.getText().toString()),
                                                                    Utils.GetCurrentDate(),
                                                                    null,
                                                                    new Person
                                                                            (
                                                                                    m_ownerCpfText.getText().toString(),
                                                                                    m_ownerNameText.getText().toString(),
                                                                                    m_ownerCellphoneText.getText().toString(),
                                                                                    m_birthDateText.getText().toString()
                                                                            )
                                                            )
                                            )
                            )
                            {
                                startActivity(new Intent(this, InterfaceClasses.warehouseClass));
                            }
                        }
                );
    }

    private void DefineRemoveButtonEvents()
    {
        if(m_removeButton == null)
        {
            return;
        }

        m_removeButton.setOnClickListener
                (
                        view ->
                        {
                            if(CRUDWarehouse.TryRemoveWarehouse(m_idText.getText().toString()))
                            {
                                startActivity(new Intent(this, InterfaceClasses.warehouseClass));
                            }
                        }
                );
    }
}