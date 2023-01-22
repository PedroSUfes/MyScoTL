package com.example.scotl;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;


public class UpdateAndRemoveWarehouseActivity extends AppCompatActivity{

    private static String m_id = new String();
    private static String m_ownerCpf = new String();
    private static String m_state = new String();
    private static String m_city = new String();
    private static String m_street = new String();
    private static String m_beginDate = new String();
    private static String m_endDate = new String();
    private static int m_namber = 0;
    
    private EditText m_idText;
    private EditText m_ownerCpfText;
    private EditText m_stateText;
    private EditText m_cityText;
    private EditText m_streetText;
    private EditText m_beginDateText;
    private EditText m_endDateText;
    private EditText m_namberText;

    private Button updateButton;
    private Button removeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_employee_system_client);

        GetReferences();
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        PassStringsArgsToViews();
    }

    public static void setId(String id) {
        if(id == null)
        {
            return;
        }
        m_id = String.copyValueOf(id.toCharArray());;
    }

    public static void setOwnerCpf(String owner_cpf) {
        if(owner_cpf == null)
        {
            return;
        }
        m_ownerCpf = String.copyValueOf(owner_cpf.toCharArray());
    }

    public static void setState(String state) {
        if(state == null)
        {
            return;
        }
        m_state = String.copyValueOf(state.toCharArray());
    }

    public static void setCity(String city) {
        if(city == null)
        {
            return;
        }
        m_city = String.copyValueOf(city.toCharArray());
    }

    public static void setStreet(String street) {
        if(street == null)
        {
            return;
        }
        m_street = String.copyValueOf(street.toCharArray());
    }

    public static void setBeginDate(String beginDate) {
        if(beginDate == null)
        {
            return;
        }
        m_beginDate = String.copyValueOf(beginDate.toCharArray());
    }

    public static void setEndDate(String endDate) {
        if(endDate == null)
        {
            return;
        }
        m_endDate = String.copyValueOf(endDate.toCharArray());
    }

    public static void setNamber(int _namber) {
        m_namber = _namber;
    }

    private void GetReferences()
    {
        m_ownerCpfText = findViewById(R.id.editTextCPFOwner);
        m_stateText = findViewById(R.id.editTextState);
        m_cityText = findViewById(R.id.editTextCity);
        m_streetText = findViewById(R.id.editTextStreet);
        m_namberText = findViewById(R.id.editTextNum);
    }

    private void PassStringsArgsToViews()
    {
        if(m_id != null)
        {
            m_idText.setText(m_id);
        }
    }
}


//package com.example.scotl;
//
//import androidx.appcompat.app.AppCompatActivity;
//


//public class UpdateAndRemoveEmployeeActivity extends AppCompatActivity
//{
//    private static String m_cpf = new String();
//    private static String m_name = new String();
//    private static String m_cellPhone = new String();
//    private static String m_birthDate = new String();
//    private static String m_hiringDate = new String();
//    private static String m_endDate = new String();
//    private static String m_profession = new String();
//    private static String m_workLocalId = new String();

//    private EditText m_cpfText;
//    private EditText m_nameText;
//    private EditText m_cellphoneText;
//    private TextView m_workLocalEditText;
//    private TextView m_birthDateText;
//    private TextView m_hiringDateText;
//    private TextView m_endDateText;
//    private TextView m_professionText;

//    private Button updateButton;
//    private Button removeButton;

//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_update_and_remove_employee);
//
//        GetReferences();
//    }
//
//    @Override
//    protected void onStart()
//    {
//        super.onStart();
//
//        PassStringsArgsToViews();
//    }
//
//    public static void SetCpf(String cpf)
//    {
//        if(cpf == null)
//        {
//            return;
//        }
//
//        m_cpf = String.copyValueOf(cpf.toCharArray());
//    }
//
//    public static void SetName(String name)
//    {
//        if(name == null)
//        {
//            return;
//        }
//
//        m_name = String.copyValueOf(name.toCharArray());
//    }
//
//    public static void SetCellphone(String cellphone)
//    {
//        if(cellphone == null)
//        {
//            return;
//        }
//
//        m_cellPhone = String.copyValueOf(cellphone.toCharArray());
//    }
//
//    public static void SetBirthDate(String birthDate)
//    {
//        if(birthDate == null)
//        {
//            return;
//        }
//
//        m_birthDate = String.copyValueOf(birthDate.toCharArray());
//    }
//
//    public static void SetHiringDate(String hiringDate)
//    {
//        if(hiringDate == null)
//        {
//            return;
//        }
//
//        m_hiringDate = String.copyValueOf(hiringDate.toCharArray());
//    }
//
//    public static void SetProfession(String profession)
//    {
//        if(profession == null)
//        {
//            return;
//        }
//
//        m_profession = String.copyValueOf(profession.toCharArray());
//    }
//
//    public static void SetEndDate(String endDate)
//    {
//        if(endDate == null)
//        {
//            return;
//        }
//
//        m_endDate = String.copyValueOf(endDate.toCharArray());
//    }
//
//    public static void SetWorkLocalId(String id)
//    {
//        if(id == null)
//        {
//            return;
//        }
//
//        m_workLocalId = String.copyValueOf(id.toCharArray());
//    }
//
//    private void GetReferences()
//    {
//        m_cpfText = findViewById(R.id.update_employee_cpf_edit_text);
//        m_nameText = findViewById(R.id.update_employee_name_edit_text);
//        m_cellphoneText = findViewById(R.id.update_employee_cellphone_edit_text);
//        m_birthDateText = findViewById(R.id.update_employee_birth_date_text_view);
//        m_hiringDateText = findViewById(R.id.update_employee_hiring_date_text_view);
//        m_endDateText = findViewById(R.id.update_employee_end_date_text_view);
//        m_workLocalEditText = findViewById(R.id.update_employee_work_local_id_edit_text);
//        m_professionText = findViewById(R.id.update_employee_profession_text_view);
//    }
//
//    private void PassStringsArgsToViews()
//    {
//        if(m_cpfText != null)
//        {
//            m_cpfText.setText(m_cpf);
//        }
//        if(m_nameText != null)
//        {
//            m_nameText.setText(m_name);
//        }
//        if(m_cellphoneText != null)
//        {
//            m_cellphoneText.setText(m_cellPhone);
//        }
//        if(m_birthDateText != null)
//        {
//            m_birthDateText.setText(m_birthDate);
//        }
//        if(m_hiringDateText != null)
//        {
//            m_hiringDateText.setText(m_hiringDate);
//        }
//        if(m_endDateText != null)
//        {
//            m_endDateText.setText(m_endDate);
//        }
//        if(m_professionText != null)
//        {
//            m_professionText.setText(m_profession);
//        }
//        if(m_workLocalEditText != null)
//        {
//            m_workLocalEditText.setText(m_workLocalId);
//        }
//    }
//}