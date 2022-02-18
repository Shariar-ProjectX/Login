package com.example.projectx;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button login,signup;
    EditText email,password;
    Database database;
    User_Dtails user_dtails;

    String name1,email1,phone1,gender1,country1,password1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(Button) findViewById(R.id.login);
        signup=(Button) findViewById(R.id.signup);
        email=(EditText) findViewById(R.id.Email_login);
        password=(EditText) findViewById(R.id.password_login);
        database=new Database(this);
        user_dtails=new User_Dtails();
        SQLiteDatabase sqLiteDatabase=database.getWritableDatabase();

        login.setOnClickListener(this);
        signup.setOnClickListener(this);









        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Boolean res1=database.EMAIL(email.getText().toString());
                if (!res1) {

                    email.setError("Invalid email");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Boolean res2=database.PASSWORD(password.getText().toString());
                if (!res2) {

                    password.setError("Invalid password");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });





    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    private  void showDioalogmassagelogin()
    {
        AlertDialog.Builder alert_dialog =new AlertDialog.Builder(this);

        alert_dialog.setIcon(R.drawable.ic_baseline_help_24);

        alert_dialog.setTitle("Deactivation");
        alert_dialog.setMessage("Your Account is deactivated.Do you want to activate?");
        alert_dialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert_dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String [] DETAILS=new String[10];
                DETAILS=database.DETAILS(email.getText().toString(),password.getText().toString());
                email1=DETAILS[0];
                name1=DETAILS[1];
                phone1=DETAILS[2];
                country1=DETAILS[3];
                password1=DETAILS[4];
                gender1=DETAILS[5];
                String active1="true";


                boolean update=  database.UPDATE(email1,active1,name1,password1,phone1,gender1,country1);

                if(update)
                {

                    Toast.makeText(MainActivity.this, "Successfully Reactivate", Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("USER_NAME",name1);
                    bundle.putString("USER_EMAIL",email1);
                    bundle.putString("USER_PHONE",phone1);
                    bundle.putString("USER_PASSWORD",password1);
                    bundle.putString("USER_COUNTRY",country1);
                    bundle.putString("USER_GENDER",gender1);
                    Intent intent = new Intent(MainActivity.this, Welcome.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }







            }
        });
        alert_dialog.show();


    }







    @Override
    public void onClick(View view)
    {

        try {


            if (view.getId() == R.id.signup)
            {
                Intent intent = new Intent(MainActivity.this, Second.class);
                startActivity(intent);
            }
            Boolean result=database.FIND_PASSWORD(email.getText().toString(),password.getText().toString());
            String active=database.FIND_ACTIVATION(email.getText().toString(),password.getText().toString());
            if (view.getId() == R.id.login) {



                if (result && active.equals("true")) {

                    Toast.makeText(MainActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();

                    Bundle bundle = new Bundle();
                    String [] DETAILS=new String[10];
                    DETAILS=database.DETAILS(email.getText().toString(),password.getText().toString());
                    email1=DETAILS[0];
                    name1=DETAILS[1];
                    phone1=DETAILS[2];
                    country1=DETAILS[3];
                    password1=DETAILS[4];
                    gender1=DETAILS[5];



                    bundle.putString("USER_NAME",name1);
                    bundle.putString("USER_EMAIL",email1);
                    bundle.putString("USER_PHONE",phone1);
                    bundle.putString("USER_PASSWORD",password1);
                    bundle.putString("USER_COUNTRY",country1);
                    bundle.putString("USER_GENDER",gender1);
                    Intent intent = new Intent(MainActivity.this, Welcome.class);

                    intent.putExtras(bundle);

                    startActivity(intent);
                }
                else if (active.equals("false"))
                {
                    showDioalogmassagelogin();
                }

                else
                {
                    Toast.makeText(MainActivity.this, "EMAIL AND PASSWORD DOESN'T MATCH", Toast.LENGTH_SHORT).show();
                }


            }
        }catch (Exception e)
        {
            Toast.makeText(MainActivity.this, "Validation InComplete", Toast.LENGTH_SHORT).show();
        }

    }
}