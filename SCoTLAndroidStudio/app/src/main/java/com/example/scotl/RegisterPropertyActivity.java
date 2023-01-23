package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import Frameworks.Utility.ChainOfResponsability.ChainOfResponsibilityHandler;
import Frameworks.Utility.ChainOfResponsability.EmptyDataHandle;
import Frameworks.Utility.ChainOfResponsability.IdValidationHandle;
import Policy.BusinessRules.CRUDProperty;
import Policy.Entity.Property;
import Utility.Action;

public class RegisterPropertyActivity extends AppCompatActivity
{
    private EditText m_idText;
    private EditText m_nameText;
    private EditText m_stateText;
    private EditText m_cityText;
    private EditText m_streetText;
    private EditText m_residentialNumberText;

    private Button m_clearButton;
    private Button m_registerButton;

    private final ArrayList<Action> toClearActionList = new ArrayList<>();

    private ChainOfResponsibilityHandler<Boolean> validationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_property);

        GetReferences();
        DefineToClearActions();
        DefineValidationHandler();

        DefineClearButtonEvents();
        DefineRegisterButtonEvents();
    }

    private void GetReferences()
    {
        m_idText = findViewById(R.id.system_client_register_property_id_edit_text);
        m_nameText = findViewById(R.id.system_client_register_property_name_edit_text);
        m_stateText = findViewById(R.id.system_client_register_property_state_edit_text);
        m_cityText = findViewById(R.id.system_client_register_property_city_edit_text);
        m_streetText = findViewById(R.id.system_client_register_property_street_edit_text);
        m_residentialNumberText = findViewById(R.id.system_client_register_property_number_edit_text);

        m_clearButton = findViewById(R.id.system_client_register_property_clear_button);
        m_registerButton = findViewById(R.id.system_client_register_property_register_button);
    }

    private void DefineToClearActions()
    {
        toClearActionList.add(() -> m_idText.getText().clear());
        toClearActionList.add(() -> m_nameText.getText().clear());
        toClearActionList.add(() -> m_stateText.getText().clear());
        toClearActionList.add(() -> m_cityText.getText().clear());
        toClearActionList.add(() -> m_streetText.getText().clear());
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

                            Property property = new Property
                                    (
                                            m_idText.getText().toString(),
                                            m_nameText.getText().toString(),
                                            m_stateText.getText().toString(),
                                            m_cityText.getText().toString(),
                                            m_streetText.getText().toString(),
                                            Integer.parseInt(m_residentialNumberText.getText().toString())

                                    );

                            if(CRUDProperty.TryRegisterProperty(property))
                            {
                                startActivity(new Intent(this, SystemClientReadPropertyActivity.class));
                            }
                        }
                );
    }

    private void DefineValidationHandler()
    {
        validationHandler = new ChainOfResponsibilityHandler<>(new IdValidationHandle(() -> m_idText.getText().toString()));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_nameText.getText().toString(), "Nome da propriedade não definido"));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_stateText.getText().toString(), "Nome do estado não definido"));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_cityText.getText().toString(), "Nome da cidade não definido"));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_streetText.getText().toString(), "Nome da rua não definido"));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_streetText.getText().toString(), "Nome da rua não definido"));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_residentialNumberText.getText().toString(), "Número de residência não definido"));
    }
}