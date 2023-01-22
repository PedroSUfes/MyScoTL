package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Frameworks.Utility.ChainOfResponsability.ChainOfResponsibilityHandler;
import Frameworks.Utility.ChainOfResponsability.EmptyDataHandle;
import Frameworks.Utility.ChainOfResponsability.IdValidationHandle;
import Policy.BusinessRules.CRUDProperty;
import Policy.Entity.Property;

public class UpdateAndRemovePropertyActivity extends AppCompatActivity
{
    private TextView m_idText;
    private EditText m_nameText;
    private EditText m_stateText;
    private EditText m_cityText;
    private EditText m_streetText;
    private EditText m_residentialNumberText;

    private Button m_updateButton;
    private Button m_removeButton;

    private ChainOfResponsibilityHandler<Boolean> validationHandler;

    private static Property m_property;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_remove_property);

        GetReferences();
        DefineValidationHandler();

        DefineUpdateButtonEvents();
        DefineRemoveButtonEvents();
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        PassPropertyAttributesToViews();
    }

    private void GetReferences()
    {
        m_idText = findViewById(R.id.system_client_update_property_id_edit_text);
        m_nameText = findViewById(R.id.system_client_update_property_name_edit_text);
        m_stateText = findViewById(R.id.system_client_update_property_state_edit_text);
        m_cityText = findViewById(R.id.system_client_update_property_city_edit_text);
        m_streetText = findViewById(R.id.system_client_update_property_street_edit_text);
        m_residentialNumberText = findViewById(R.id.system_client_update_property_number_edit_text);

        m_updateButton = findViewById(R.id.system_client_update_property_update_button);
        m_removeButton = findViewById(R.id.system_client_update_property_remove_button);
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

    public static void SetPropertyAttributes(Property property)
    {
        if(property == null)
        {
            return;
        }

        m_property = property;
    }

    private void PassPropertyAttributesToViews()
    {
        m_idText.setText(m_property.GetId());
        m_nameText.setText(m_property.GetPropertyName());
        m_stateText.setText(m_property.GetStateName());
        m_cityText.setText(m_property.GetCityName());
        m_streetText.setText(m_property.GetStreetName());
        m_residentialNumberText.setText(((Integer) m_property.GetNumber()).toString());
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

                             if(
                                     CRUDProperty.TryUpdateProperty
                                            (
                                                    new Property
                                                            (
                                                                    m_idText.getText().toString(),
                                                                    m_nameText.getText().toString(),
                                                                    m_stateText.getText().toString(),
                                                                    m_cityText.getText().toString(),
                                                                    m_streetText.getText().toString(),
                                                                    Integer.parseInt(m_residentialNumberText.getText().toString())
                                                            )
                                            )
                             )
                             {
                                 startActivity(new Intent(this, SystemClientReadPropertyActivity.class));
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
                            if(CRUDProperty.TryRemoveProperty(m_idText.getText().toString()))
                            {
                                startActivity(new Intent(this, SystemClientReadPropertyActivity.class));
                            }
                        }
                );
    }
}