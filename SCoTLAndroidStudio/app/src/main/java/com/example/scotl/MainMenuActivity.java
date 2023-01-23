package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import Frameworks.Utility.InterfaceClasses;

public class MainMenuActivity extends AppCompatActivity
{
    private Button m_employeeButton;
    private Button m_propertyButton;
    private Button m_batchButton;
    private Button m_coffeeBagButton;
    private Button m_warehouseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        GetReferences();

        DefineButtonEvent(m_employeeButton, InterfaceClasses.employeeClass);
        DefineButtonEvent(m_propertyButton, InterfaceClasses.propertyClass);
        DefineButtonEvent(m_batchButton, InterfaceClasses.batchClass);
        DefineButtonEvent(m_coffeeBagButton, InterfaceClasses.coffeeBagClass);
        DefineButtonEvent(m_warehouseButton, InterfaceClasses.warehouseClass);
    }

    private void GetReferences()
    {
        m_employeeButton = findViewById(R.id.main_menu_employee_button);
        m_propertyButton = findViewById(R.id.main_menu_property_button);
        m_batchButton = findViewById(R.id.main_menu_batch_button);
        m_coffeeBagButton = findViewById(R.id.main_menu_coffee_bag_button);
        m_warehouseButton = findViewById(R.id.main_menu_coffee_warehouse_button);
    }

    private void DefineButtonEvent(Button button, Class toSwitch)
    {
        if(button == null)
        {
            return;
        }

        button.setOnClickListener
                (
                        view ->
                        {
                            if(toSwitch == null)
                            {
                                System.out.println("Null  menu to switch");
                                return;
                            }

                            startActivity(new Intent(this, toSwitch));
                        }
                );
    }
}