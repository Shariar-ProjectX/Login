package com.example.projectx;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Welcome extends AppCompatActivity implements View.OnClickListener  {

    private Button logout;
    private TextView name,email,phone,gender,country,deactive;
    private  String email1,name1,gender1,country1,password1,phone1,active1;
    Database database;
    User_Dtails user_dtails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        logout=(Button) findViewById(R.id.Log_out);
        logout.setOnClickListener(this);
        name=(TextView) findViewById(R.id.welcome_name);
        email=(TextView) findViewById(R.id.welcome_email);
        phone=(TextView) findViewById(R.id.welcome_phone);
       gender=(TextView) findViewById(R.id.welcome_gender);
        country=(TextView) findViewById(R.id.welcome_country);
        deactive=(TextView)findViewById(R.id.Deactivate) ;
        Bundle bundle = getIntent().getExtras();
        name1=bundle.getString("USER_NAME");
        email1=bundle.getString("USER_EMAIL");
        phone1=bundle.getString("USER_PHONE");
        country1=bundle.getString("USER_COUNTRY");
        password1=bundle.getString("USER_PASSWORD");
        gender1=bundle.getString("USER_GENDER");
        name.setText(name.getText()+" : "+name1);
        email.setText(email.getText()+" : "+email1);
        phone.setText(phone.getText()+" : "+phone1);
        gender.setText(gender.getText()+" : "+gender1);
        country.setText(country.getText()+" : "+country1);

        SpannableString content = new SpannableString("Deactivate Account?");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        deactive.setText(content);
        user_dtails=new User_Dtails();
        database=new Database(this);
        SQLiteDatabase sqLiteDatabase=database.getWritableDatabase();

        deactive.setOnClickListener(this);





















    }

private  void showDioalogmassage()
{
    AlertDialog.Builder alert_dialog =new AlertDialog.Builder(this);
    alert_dialog.setIcon(R.drawable.ic_baseline_help_24);
    alert_dialog.setTitle("Deactivation");
    alert_dialog.setMessage("Are You Sure?");
    alert_dialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

        }
    });
    alert_dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            active1="false";
          boolean update=  database.UPDATE(email1,active1,name1,password1,phone1,gender1,country1);

          if(update)
          {
              Toast.makeText(Welcome.this, "Successfully Deactivated", Toast.LENGTH_SHORT).show();
              Intent intent = new Intent(Welcome.this, MainActivity.class);
              startActivity(intent);
          }
          else
          {
              Toast.makeText(Welcome.this, "Error", Toast.LENGTH_SHORT).show();
          }






        }
    });
    alert_dialog.show();


}



    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.Log_out)
        {
            Intent intent = new Intent(Welcome.this, MainActivity.class);
            startActivity(intent);
        }
        if(view.getId()== R.id.Deactivate)
        {
            showDioalogmassage();


        }


    }


}