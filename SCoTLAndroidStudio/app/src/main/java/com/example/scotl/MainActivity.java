package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import Frameworks.Database.SQLite.SQLiteDAO;
import Frameworks.Utility.InterfaceClassDefiner;
import Frameworks.Utility.SystemClientInterfaceClassDefiner;
import Frameworks.Utility.TableRowDefiner.ManagerTableRowGeneratorDefiner;
import Frameworks.Utility.TableRowDefiner.SystemClientTableRowGeneratorDefiner;
import Frameworks.Utility.TableRowDefiner.TableRowGeneratorDefiner;
import Frameworks.Utility.WarehouseManagerInterfaceClassDefiner;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.BusinessRules.LoginManager;
import Policy.BusinessRules.UserType;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText user;
    private EditText password;

    ArrayList<InterfaceClassDefiner> interfaceClassesList = new ArrayList<InterfaceClassDefiner>();
    ArrayList<TableRowGeneratorDefiner> tableRowGeneratorDefinerList = new ArrayList<TableRowGeneratorDefiner>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyLog.SetLogAction((message)->Toast.makeText(MainActivity.this, (CharSequence) message, Toast.LENGTH_LONG).show());
        AddMainMenuInterfaces();
        AddTableRowGeneratorDefiners();
        InjectDatabase();

        user = (EditText) findViewById(R.id.login_User);
        password = (EditText) findViewById(R.id.login_password);

        button = (Button) findViewById(R.id.buttonLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userInput = user.getText().toString();
                String passwordInput = password.getText().toString();

                if(userInput.isEmpty()){
                    MyLog.LogMessage("User is Empty");
                    return;
                }
                if(passwordInput.isEmpty()){
                    MyLog.LogMessage("Password is Empty");
                    return;
                }

                if(!LoginManager.TryLogin(userInput, passwordInput)){
                    return;
                }

                UserType userType = LoginManager.GetUserType();

                for(InterfaceClassDefiner i: interfaceClassesList)
                {
                    if(i == null){
                        continue;
                    }

                    i.DefineMenuClassPrincipal(userType);
                }
                for(TableRowGeneratorDefiner t : tableRowGeneratorDefinerList)
                {
                    if(t == null)
                    {
                        continue;
                    }

                    t.DefineTableRowGenerator();
                }

                //Toast.makeText(MainActivity.this, passwordInput, Toast.LENGTH_LONG).show();
                openActivity2();
            }
        });


        //System.out.println(database.getDatabaseName());
    }

    public void openActivity2(){
//        Intent intent = new Intent(this, InterfaceClasses.mainMenuClass);
        Intent intent = new Intent(this, ManagerReadEmployeeActivity.class);
        startActivity(intent);
    }

    public void AddMainMenuInterfaces(){

        InterfaceClassDefiner warehouseClass = new WarehouseManagerInterfaceClassDefiner();
        interfaceClassesList.add(warehouseClass);

        InterfaceClassDefiner systemClientClass = new SystemClientInterfaceClassDefiner();
        interfaceClassesList.add(systemClientClass);
    }

    public void InjectDatabase(){
        SQLiteDAO database = new SQLiteDAO(this);
        DatabaseAccess.batchOperationsInterface = database;
        DatabaseAccess.coffeeBagOperationsInterface = database;
        DatabaseAccess.employeeOperationsInterface = database;
        DatabaseAccess.propertyOperationsInterface = database;
        DatabaseAccess.warehouseOperationsInterface = database;
        DatabaseAccess.loginInterface = database;

    }


    private void AddTableRowGeneratorDefiners()
    {
        tableRowGeneratorDefinerList.add(new SystemClientTableRowGeneratorDefiner());
        tableRowGeneratorDefinerList.add(new ManagerTableRowGeneratorDefiner());
    }
}