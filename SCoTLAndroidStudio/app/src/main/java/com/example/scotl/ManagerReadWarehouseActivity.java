package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import Frameworks.Adapters.TableRowGenerator;
import Policy.BusinessRules.CRUDWarehouse;
import Policy.Entity.Warehouse;

public class ManagerReadWarehouseActivity extends AppCompatActivity {

	private EditText m_idText;
	private EditText m_ownerCpfText;
	private EditText m_stateText;
	private EditText m_cityText;
	private EditText m_streetText;
	private EditText m_residentialNumberText;

	private Button m_listButton;

	private SwitchCompat m_withPastRegisterSwitch;

	private TableLayout m_tableLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager_read_warehouse);

		GetReferences();

		DefineListButtonEvents();
	}

	private void GetReferences()
	{
		m_idText = findViewById(R.id.manager_read_warehouse_id_edit_text);
		m_ownerCpfText = findViewById(R.id.manager_read_warehouse_owner_cpf_edit_text);
		m_stateText = findViewById(R.id.manager_read_warehouse_state_edit_text);
		m_cityText = findViewById(R.id.manager_read_warehouse_city_edit_text);
		m_streetText = findViewById(R.id.manager_read_warehouse_street_edit_text);
		m_residentialNumberText = findViewById(R.id.manager_read_warehouse_number_edit_text);

		m_listButton = findViewById(R.id.manager_read_warehouse_list_button);
		m_withPastRegisterSwitch = findViewById(R.id.manager_read_warehouse_with_past_register_switch);

		m_tableLayout = findViewById(R.id.manager_read_warehouse_table_layout);
	}

	private void DefineListButtonEvents()
	{
		if
		(
					m_listButton == null ||
							m_idText == null ||
							m_ownerCpfText == null ||
							m_stateText == null ||
							m_cityText == null ||
							m_streetText == null ||
							m_residentialNumberText == null ||
							m_tableLayout == null ||
							m_withPastRegisterSwitch == null
		)
		{
			return;
		}

		m_listButton.setOnClickListener
				(
						view ->
						{
							m_tableLayout.removeViews(1, m_tableLayout.getChildCount() - 1);

							Warehouse[] warehouses = null;
							if(!m_idText.getText().toString().isEmpty())
							{
								warehouses = CRUDWarehouse.GetWarehouse(m_idText.getText().toString(), m_withPastRegisterSwitch.isChecked());
							}
							else if(!m_ownerCpfText.getText().toString().isEmpty())
							{
								warehouses = CRUDWarehouse.GetWarehousesByOwnerCpf(m_ownerCpfText.getText().toString(), m_withPastRegisterSwitch.isChecked());
							}
							else if(!m_stateText.getText().toString().isEmpty() && m_cityText.getText().toString().isEmpty())
							{
								warehouses = CRUDWarehouse.GetWarehouses(m_stateText.getText().toString(), m_withPastRegisterSwitch.isChecked());
							}
							else if(!m_stateText.getText().toString().isEmpty() && !m_cityText.getText().toString().isEmpty() && m_streetText.getText().toString().isEmpty())
							{
								warehouses = CRUDWarehouse.GetWarehouses(m_stateText.getText().toString(), m_cityText.getText().toString(), m_withPastRegisterSwitch.isChecked());
							}
							else if(!m_stateText.getText().toString().isEmpty() && !m_cityText.getText().toString().isEmpty() && m_streetText.getText().toString().isEmpty() && !m_residentialNumberText.getText().toString().isEmpty())
							{
								warehouses = CRUDWarehouse.GetWarehouses
										(
												m_stateText.getText().toString(),
												m_cityText.getText().toString(),
												m_streetText.getText().toString(),
												m_withPastRegisterSwitch.isChecked()
										);
							}
							else if(!m_stateText.getText().toString().isEmpty() && !m_cityText.getText().toString().isEmpty() && m_streetText.getText().toString().isEmpty() && m_residentialNumberText.getText().toString().isEmpty())
							{
								warehouses = CRUDWarehouse.GetWarehouses
										(
												m_stateText.getText().toString(),
												m_cityText.getText().toString(),
												m_streetText.getText().toString(),
												Integer.parseInt(m_residentialNumberText.getText().toString()),
												m_withPastRegisterSwitch.isChecked()
										);
							}
							else
							{
								warehouses = CRUDWarehouse.GetWarehouses(m_withPastRegisterSwitch.isChecked());
							}

							TableRow[] tableRows = TableRowGenerator.GetWarehouseTableRows(warehouses, this);
							if(tableRows == null)
							{
								return;
							}

							for(TableRow t : tableRows)
							{
								if(t == null)
								{
									continue;
								}

								m_tableLayout.addView(t);
							}
						}
				);
	}
}