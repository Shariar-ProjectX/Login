package com.example.projectx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Second extends AppCompatActivity implements View.OnClickListener
{
    private Button male,female;
    String a1 ;
    EditText name,email,phone,password,confirm_password;
    TextView gender,country;
    private Spinner spinner;
    String [] CountryName;
    private Button create_account;
    private String selected_country;
    private String n1,n2,n3,n4;
    User_Dtails user_dtails;
    Database database;
    private boolean ac;
    private ImageView email_r8,name_r8,phone_r8,pass_r8,conpass_r8;










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        CountryName=(String[]) getResources().getStringArray(R.array.countries_array);
        spinner=(Spinner) findViewById(R.id.country_spinner);
        create_account=(Button) findViewById(R.id.Create_account);
        create_account.setOnClickListener(this);
        name=(EditText) findViewById(R.id.name);
        gender=(TextView) findViewById(R.id.Gender);
        email=(EditText) findViewById(R.id.Email);
        phone=(EditText) findViewById(R.id.phone);

        country=(TextView) findViewById(R.id.Country);
        male=(Button)findViewById(R.id.Male);
        female=(Button)findViewById(R.id.Female);
        email_r8=(ImageView)findViewById(R.id.email_r8) ;
        name_r8=(ImageView)findViewById(R.id.name_r8) ;
        phone_r8=(ImageView)findViewById(R.id.phone_r8) ;
        pass_r8=(ImageView)findViewById(R.id.pass_r8) ;
        conpass_r8=(ImageView)findViewById(R.id.conpass_r8) ;
        confirm_password=(EditText)findViewById(R.id.confirm_password) ;
        user_dtails=new User_Dtails();
        database=new Database(this);






        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.sample_view, R.id.Text_view_sample,CountryName);
        spinner.setAdapter(adapter);









        password=(EditText) findViewById(R.id.password);
        male.setOnClickListener(this);
        female.setOnClickListener(this);



        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!Validate_name(name.getText().toString()))
                {
                    name.setError("Invalid Name");
                    name_r8.setVisibility(View.INVISIBLE);
                }
                else
                {
                    name_r8.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                ac=database.EMAIL(email.getText().toString());
                if(!Validate_email(email.getText().toString()) || ac )
                {
                    email_r8.setVisibility(View.INVISIBLE);
                    if (ac) {

                        email.setError("Email already used");



                    } else {

                        email.setError("Invalid email");

                    }

                }
                else
                {
                    email_r8.setVisibility(View.VISIBLE);
                }




            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!Validate_phone(phone.getText().toString()))
                {
                    phone_r8.setVisibility(View.INVISIBLE);
                    phone.setError("Invalid phone");
                }
                else
                {
                    phone_r8.setVisibility(View.VISIBLE);
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
                if(!Validate_password(password.getText().toString()))
                {
                    pass_r8.setVisibility(View.INVISIBLE);
                    password.setError("Invalid password");
                }
                else
                {
                    pass_r8.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        confirm_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!Validate_con_password(confirm_password.getText().toString()) || confirm_password.getText().toString().isEmpty())
                {
                    conpass_r8.setVisibility(View.INVISIBLE);
                    confirm_password.setError("Password doest match");
                }
                else
                {
                    conpass_r8.setVisibility(View.VISIBLE);
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

        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        confirm_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });







    }
    boolean Validate_name(String input)
    {
        Pattern p=Pattern.compile("(?i)[a-z]([- ',.a-z]{0,23}[a-z])?");
        Matcher m=p.matcher(input);
        return m.matches();
    }
    boolean Validate_email(String input)
    {
        Pattern p=Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        Matcher m=p.matcher(input);
        return m.matches();
    }
    boolean Validate_phone(String input)
    {
        Pattern p=Pattern.compile("[0]{1}[1]{1}[3-9]{1}[0-9]{8}$");
        Matcher m=p.matcher(input);
        return m.matches();
    }
    boolean Validate_password(String input)
    {
        Pattern p=Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}");
        Matcher m=p.matcher(input);
        return m.matches();
    }
    boolean Validate_con_password(String input)
    {
        Pattern p=Pattern.compile(password.getText().toString());
        Matcher m=p.matcher(input);
        return m.matches();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    public void onClick(View view) {


        try {


            if(view.getId()== R.id.Male) {
                a1= male.getText().toString();
            }
            if(view.getId()== R.id.Female) {
                a1= female.getText().toString();
            }









             n1= name.getText().toString();
             n2= email.getText().toString();
             n3= phone.getText().toString();
             n4= password.getText().toString();










            if (view.getId() == R.id.Create_account)
            {


                selected_country=spinner.getSelectedItem().toString();
                user_dtails.setName(n1);
                user_dtails.setCountry(selected_country);
                user_dtails.setEmail(n2);
                user_dtails.setGender(a1);
                user_dtails.setPhone(n3);
                user_dtails.setPassword(n4);
                user_dtails.setActive("true");






                if (!a1.isEmpty() && country.getText()!="Country" && Validate_name(name.getText().toString())  && Validate_email(email.getText().toString()) && Validate_phone(phone.getText().toString()) && Validate_password(password.getText().toString()) && !ac && Validate_con_password(confirm_password.getText().toString()))
                {
                    Toast.makeText(Second.this, "Signup Successfully Done", Toast.LENGTH_SHORT).show();

                    database.INSERT_DATA(user_dtails);





                    Intent intent = new Intent(Second.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Second.this, "Validation Incomplete", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e)
        {
            Toast.makeText(Second.this, "Validation Incomplete", Toast.LENGTH_SHORT).show();
        }







    }
}