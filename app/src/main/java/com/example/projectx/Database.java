package com.example.projectx;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ProjectX";
    private static final String TABLE_NAME = "User_Details";
    private static final int VERSION_NUMBER = 10;
    private static final String USER_NAME = "Name";
    private static final String USER_EMAIL = "_Email";
    private static final String PHONE_NUMBER = "Number";
    private static final String USER_COUNTRY = "Country";
    private static final String USER_PASSWORD = "Password";
    private static final String USER_GENDER = "Gender";
    private static final String ACTIVATE = "Activation";
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"(" + USER_EMAIL + " VARCHAR PRIMARY KEY  ," + USER_NAME + " VARCHAR," + PHONE_NUMBER + " VARCHAR," + USER_COUNTRY + " VARCHAR," + USER_PASSWORD + " VARCHAR," + USER_GENDER + " VARCHAR,"+ACTIVATE+" VARCHER );";
    private Context context;
    private static final String DROP_TABLE = "DROP TABLE IF  EXISTS " + TABLE_NAME;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            Toast.makeText(context, "onCreate is called ", Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            Toast.makeText(context, "EXCEPTION : " + e, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            Toast.makeText(context, "onUpgrade is called ", Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        } catch (Exception e) {
            Toast.makeText(context, "EXCEPTION : " + e, Toast.LENGTH_LONG).show();
        }

    }
    public void INSERT_DATA(User_Dtails user_dtails)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(USER_NAME,user_dtails.getName());
        contentValues.put(USER_EMAIL,user_dtails.getEmail());
        contentValues.put(USER_PASSWORD,user_dtails.getPassword());
        contentValues.put(PHONE_NUMBER,user_dtails.getPhone());
        contentValues.put(USER_GENDER,user_dtails.getGender());
        contentValues.put(USER_COUNTRY,user_dtails.getCountry());
        contentValues.put(ACTIVATE,user_dtails.getActive());
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    public boolean FIND_PASSWORD(String uEmail, String uPass)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        Boolean res=false;

        while (cursor.moveToNext())
        {
            String userEmail=cursor.getString(0);
            String userPassword=cursor.getString(4);

            if(userEmail.equals(uEmail) && userPassword.equals(uPass))
            {

                res =true;
                break;
            }
        }
        return res;

    }
    public boolean EMAIL(String mail)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        Boolean res=false;

        while (cursor.moveToNext())
        {
            String userEmail=cursor.getString(0);

            if(userEmail.equals(mail))
            {
                res=true;

                break;
            }
        }
        return res;

    }

    public boolean PASSWORD(String pass)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        Boolean res=false;

        while (cursor.moveToNext())
        {
            String userPassword=cursor.getString(4);

            if(userPassword.equals(pass))
            {
                res=true;

                break;
            }
        }
        return res;

    }

    public String[]DETAILS(String mail,String pass)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        String [] delaits=new String[10];
        while (cursor.moveToNext())
        {
            String userEmail=cursor.getString(0);
            String userPassword=cursor.getString(4);
            if(userEmail.equals(mail) && userPassword.equals(pass))
            {

                delaits[0]=cursor.getString(0);
                delaits[1]=cursor.getString(1);
                delaits[2]=cursor.getString(2);
                delaits[3]=cursor.getString(3);
                delaits[4]=cursor.getString(4);
                delaits[5]=cursor.getString(5);
                break;
            }
        }
        return delaits;

    }

    public String FIND_ACTIVATION(String uEmail, String uPass)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        String active=new String();

        while (cursor.moveToNext())
        {
            String userEmail=cursor.getString(0);
            String userPassword=cursor.getString(4);
            if(userEmail.equals(uEmail) && userPassword.equals(uPass))
            {

                active=cursor.getString(6);
                break;
            }
        }
        return active;


    }


    public boolean UPDATE(String uEmail,String active,String name,String passs,String nmumber,String gender,String country)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(USER_NAME,name);
        contentValues.put(USER_EMAIL,uEmail);
        contentValues.put(USER_PASSWORD,passs);
        contentValues.put(PHONE_NUMBER,nmumber);
        contentValues.put(USER_GENDER,gender);
        contentValues.put(USER_COUNTRY,country);
        contentValues.put(ACTIVATE,active);


        sqLiteDatabase.update(TABLE_NAME,contentValues,USER_EMAIL+"=?",new String[]{uEmail});
        return true;



    }







}



