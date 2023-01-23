package com.example.scotl;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TableLayout;


public class UpdateAndRemoveWarehouseActivity extends AppCompatActivity{

    private static String m_ownerCpf = new String();
    private static String m_beginDate = new String();
    private TableLayout m_referenceTableText;

    private EditText m_ownerCpfText;
    private EditText m_switchCompatText;

    private Button m_registerButton;
    private Button m_listButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_menu);

        GetReferences();
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        PassStringsArgsToViews();
    }


    public static void SetOwnerCpf(String owner_cpf) {
        if(owner_cpf == null)
        {
            return;
        }
        m_ownerCpf = String.copyValueOf(owner_cpf.toCharArray());
    }

    private void GetReferences()
    {
        m_ownerCpfText = findViewById(R.id.system_client_read_warehouse_cpf_edit_text);
        m_switchCompatText = findViewById(R.id.system_client_read_warehouse_with_past_register_switch);
        m_referenceTableText = findViewById(R.id.system_client_read_warehouse_table_layout);

        m_registerButton = findViewById(R.id.system_client_read_warehouse_register_button);
        m_listButton = findViewById(R.id.system_client_read_employee_list_button);
    }

    private void PassStringsArgsToViews()
    {
        
    }
}