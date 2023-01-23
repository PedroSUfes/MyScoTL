package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Frameworks.Utility.ChainOfResponsability.ChainOfResponsibilityHandler;
import Frameworks.Utility.ChainOfResponsability.CpfValidationHandle;
import Frameworks.Utility.ChainOfResponsability.EmptyDataHandle;
import Frameworks.Utility.InterfaceClasses;
import Frameworks.Utility.Utils;
import Policy.BusinessRules.CRUDEmployee;
import Policy.Entity.Employee;
import Policy.Entity.Property;
import Policy.Entity.Servant;
import Policy.Entity.Warehouse;
import Policy.Entity.WarehouseManager;
import Utility.Func;
import Utility.Func1;

public class UpdateAndRemoveEmployeeActivity extends AppCompatActivity
{
    private static String m_cpf = new String();
    private static String m_name = new String();
    private static String m_cellPhone = new String();
    private static String m_birthDate = new String();
    private static String m_hiringDate = new String();
    private static String m_endDate = new String();
    private static String m_profession = new String();
    private static String m_workLocalId = new String();

    private EditText m_cpfText;
    private EditText m_nameText;
    private EditText m_cellphoneText;
    private TextView m_workLocalEditText;
    private TextView m_birthDateText;
    private TextView m_hiringDateText;
    private TextView m_endDateText;
    private TextView m_professionText;

    private Button m_updateButton;
    private Button m_removeButton;

    ChainOfResponsibilityHandler<Boolean> validationHandler;

    ArrayMap<String, Func<Boolean>> getUpdateFunctionMap = new ArrayMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_remove_employee);

        GetReferences();

        BuildVerificationChain();
        DefineGetUpdateFunctionMap();

        DefineUpdateButtonEvents();
        DefineRemoveButtonEvents();
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        PassStringsArgsToViews();
    }

    public static void SetCpf(String cpf)
    {
        if(cpf == null)
        {
            return;
        }

        m_cpf = String.copyValueOf(cpf.toCharArray());
    }

    public static void SetName(String name)
    {
        if(name == null)
        {
            return;
        }

        m_name = String.copyValueOf(name.toCharArray());
    }

    public static void SetCellphone(String cellphone)
    {
        if(cellphone == null)
        {
            return;
        }

        m_cellPhone = String.copyValueOf(cellphone.toCharArray());
    }

    public static void SetBirthDate(String birthDate)
    {
        if(birthDate == null)
        {
            return;
        }

        m_birthDate = String.copyValueOf(birthDate.toCharArray());
    }

    public static void SetHiringDate(String hiringDate)
    {
        if(hiringDate == null)
        {
            return;
        }

        m_hiringDate = String.copyValueOf(hiringDate.toCharArray());
    }

    public static void SetProfession(String profession)
    {
        if(profession == null)
        {
            return;
        }

        m_profession = String.copyValueOf(profession.toCharArray());
    }

    public static void SetEndDate(String endDate)
    {
        if(endDate == null)
        {
            return;
        }

        m_endDate = String.copyValueOf(endDate.toCharArray());
    }

    public static void SetWorkLocalId(String id)
    {
        if(id == null)
        {
            return;
        }

        m_workLocalId = String.copyValueOf(id.toCharArray());
    }

    private void GetReferences()
    {
        m_cpfText = findViewById(R.id.update_employee_cpf_edit_text);
        m_nameText = findViewById(R.id.update_employee_name_edit_text);
        m_cellphoneText = findViewById(R.id.update_employee_cellphone_edit_text);
        m_birthDateText = findViewById(R.id.update_employee_birth_date_text_view);
        m_hiringDateText = findViewById(R.id.update_employee_hiring_date_text_view);
        m_endDateText = findViewById(R.id.update_employee_end_date_text_view);
        m_workLocalEditText = findViewById(R.id.update_employee_work_local_id_edit_text);
        m_professionText = findViewById(R.id.update_employee_profession_text_view);

        m_updateButton = findViewById(R.id.update_employee_update_button);
        m_removeButton = findViewById(R.id.update_employee_remove_button);
    }

    private void PassStringsArgsToViews()
    {
        if(m_cpfText != null)
        {
            m_cpfText.setText(m_cpf);
        }
        if(m_nameText != null)
        {
            m_nameText.setText(m_name);
        }
        if(m_cellphoneText != null)
        {
            m_cellphoneText.setText(m_cellPhone);
        }
        if(m_birthDateText != null)
        {
            m_birthDateText.setText(m_birthDate);
        }
        if(m_hiringDateText != null)
        {
            m_hiringDateText.setText(m_hiringDate);
        }
        if(m_endDateText != null)
        {
            m_endDateText.setText(m_endDate);
        }
        if(m_professionText != null)
        {
            m_professionText.setText(m_profession);
        }
        if(m_workLocalEditText != null)
        {
            m_workLocalEditText.setText(m_workLocalId);
        }
    }

    private void DefineGetUpdateFunctionMap()
    {
        getUpdateFunctionMap.put
                (
                        "WAREHOUSE_MANAGER",
                        () ->
                        {
                            WarehouseManager toUpdateWarehouseManager = new WarehouseManager
                                    (
                                            m_cpf,
                                            m_nameText.getText().toString(),
                                            m_cellphoneText.getText().toString(),
                                            m_birthDate,
                                            m_hiringDate,
                                            m_endDate,
                                            new Warehouse(m_workLocalEditText.getText().toString())
                                    );

                            return CRUDEmployee.TryUpdateWarehouseManager(toUpdateWarehouseManager, Utils.GetCurrentDate());
                        }
                );

        getUpdateFunctionMap.put
                (
                        "SERVANT",
                        () ->
                        {
                            Servant toUpdateServant = new Servant
                                    (
                                            m_cpf,
                                            m_nameText.getText().toString(),
                                            m_cellphoneText.getText().toString(),
                                            m_birthDate,
                                            m_hiringDate,
                                            m_endDate,
                                            new Property(m_workLocalEditText.getText().toString())
                                    );

                            return CRUDEmployee.TryUpdateServant(toUpdateServant, Utils.GetCurrentDate());
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

                            Func<Boolean> updateFunction = getUpdateFunctionMap.get(m_professionText.getText().toString());
                            if(updateFunction == null)
                            {
                                return;
                            }

                            if(updateFunction.Invoke())
                            {
                                startActivity(new Intent(this, InterfaceClasses.employeeClass));
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
                            if(CRUDEmployee.TryRemoveEmployee(m_cpf))
                            {
                                startActivity(new Intent(this, SystemClientReadEmployeeActivity.class));
                            }
                        }
                );
    }

    private void BuildVerificationChain()
    {
        validationHandler = new ChainOfResponsibilityHandler<Boolean>(new CpfValidationHandle(m_cpfText));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_nameText.getText().toString(), "Inserir nome"));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_workLocalEditText.getText().toString(), "Inserir id do local de trabalho"));
        validationHandler.SetNext(new EmptyDataHandle(() -> m_cellphoneText.getText().toString(), "Inserir telefone"));
    }
}