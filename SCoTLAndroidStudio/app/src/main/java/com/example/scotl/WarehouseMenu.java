package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

public class WarehouseMenu extends AppCompatActivity {

	private EditText m_cpfText;
	private SwitchCompat m_switchCompatText;
	private TableLayout m_referenceTableText;

	private Button m_registerButton;
	private Button m_listButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_warehouse_menu);

		GetReferences();
	}

	private void GetReferences()
	{
		m_cpfText = findViewById(R.id.system_client_read_warehouse_cpf_edit_text);
		m_switchCompatText = findViewById(R.id.system_client_read_warehouse_with_past_register_switch);
		m_referenceTableText = findViewById(R.id.system_client_read_warehouse_table_layout);

		m_registerButton = findViewById(R.id.system_client_read_warehouse_register_button);
		m_listButton = findViewById(R.id.system_client_read_employee_list_button);
	}
}

//	String id,
//	String beginDate,
//	Person owner.cpf