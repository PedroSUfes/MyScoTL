package com.example.scotl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Vector;

import Frameworks.Database.SQLite.SQLiteDAO;
import Frameworks.Utility.InterfaceClassDefiner;
import Frameworks.Utility.InterfaceClasses;
import Frameworks.Utility.SystemClientInterfaceClassDefiner;
import Frameworks.Utility.WarehouseManagerInterfaceClassDefiner;
import Policy.Adapters.MyLog;
import Policy.BusinessRules.DatabaseAccess;
import Policy.BusinessRules.LoginManager;
import Policy.BusinessRules.UserType;
import Policy.Entity.Person;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText user;
    private EditText password;

    ArrayList<InterfaceClassDefiner> interfaceClassesList = new ArrayList<InterfaceClassDefiner>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyLog.SetLogAction((message)->Toast.makeText(MainActivity.this, (CharSequence) message, Toast.LENGTH_LONG).show());
        AddMainMenuInterfaces();
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

                for(InterfaceClassDefiner i: interfaceClassesList){
                    if(i == null){
                        continue;
                    }

                    i.DefineMenuClassPrincipal(userType);
                }
                //Toast.makeText(MainActivity.this, passwordInput, Toast.LENGTH_LONG).show();
                openActivity3();
                //openActivity2();
            }
        });


        //System.out.println(database.getDatabaseName());
    }

    public void openActivity2(){
        Intent intent = new Intent(this, InterfaceClasses.mainMenuClass);
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

    public void openActivity3(){
        Intent intent = new Intent(this, BatchListMenu.class);
        startActivity(intent);
    }
}