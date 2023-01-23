package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import Frameworks.Adapters.TableRowGenerator;
import Policy.BusinessRules.CRUDProperty;
import Policy.Entity.Property;
import Utility.Func;

public class SystemClientReadPropertyActivity extends AppCompatActivity
{
    Button m_listButton;
    Button m_registerButton;

    EditText m_idFilterEditText;

    TableLayout m_tableLayout;

    // Mapa que relaciona (filtro está vazio)? em função para recuperar propriedades
    ArrayMap<Boolean, Func<Property[]>> getPropertyMap = new ArrayMap<Boolean, Func<Property[]>>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_property);

        GetReferences();
        DefineGetPropertyMap();

        DefineListButtonEvents();
        DefineRegisterButtonEvents();
    }

    private void GetReferences()
    {
        m_listButton = findViewById(R.id.system_client_read_property_list_button);
        m_registerButton = findViewById(R.id.system_client_read_property_register_button);
        m_idFilterEditText = findViewById(R.id.system_client_read_property_filter_id_edit_text);
        m_tableLayout = findViewById(R.id.system_client_read_property_table_layout);
    }

    private void DefineListButtonEvents()
    {
        if(m_listButton == null)
        {
            return;
        }

        m_listButton.setOnClickListener
                (
                        view ->
                        {
                            if(m_tableLayout == null)
                            {
                                return;
                            }

                            m_tableLayout.removeViews(1, m_tableLayout.getChildCount() - 1);

                            Func<Property[]> getEmployeeFunction = getPropertyMap.get(!m_idFilterEditText.getText().toString().isEmpty());
                            if(getEmployeeFunction == null)
                            {
                                return;
                            }

                            Property[] propertyArray = getEmployeeFunction.Invoke();
                            if(propertyArray == null)
                            {
                                return;
                            }

                            TableRow[] tableRowArray = TableRowGenerator.GetPropertyTableRows(propertyArray, this);

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

                                m_tableLayout.addView(tb);
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
                            startActivity(new Intent(this, RegisterPropertyActivity.class));
                        }
                );
    }

    private void DefineGetPropertyMap()
    {
        getPropertyMap.put
                (
                        false,
                        CRUDProperty::GetProperties
                );

        getPropertyMap.put
                (
                        true,
                        () ->
                        {
                            Property[] toReturn = new Property[1];
                            toReturn[0] = CRUDProperty.GetPropertyById(m_idFilterEditText.getText().toString());
                            return toReturn;
                        }
                );
    }
}