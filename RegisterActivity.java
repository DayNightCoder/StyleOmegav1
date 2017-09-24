package com.example.sachinpc.styleomegav10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private EditText etusername;
    private EditText etEmail;
    private EditText etFName;
    private EditText etLName;
    private EditText etPassword1;
    private EditText etPassword2;
    dbClass db = new dbClass(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Get Reference to variables
        etusername = (EditText) findViewById(R.id.update_q);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword1 = (EditText) findViewById(R.id.password1);
        etPassword2 = (EditText) findViewById(R.id.Password2);
        etFName = (EditText) findViewById(R.id.fname);
        etLName = (EditText) findViewById(R.id.lname);


    }


    // Triggers when LOGIN Button clicked
    public void checkSignUp(View arg0) {

        // Get text from email and passord field
        final String username = etusername.getText().toString();
        final String FName = etFName.getText().toString();
        final String LName = etLName.getText().toString();
        final String email = etEmail.getText().toString();
        final String password1 = etPassword1.getText().toString();
        final String password2 = etPassword2.getText().toString();
        // Initialize  AsyncLogin() class with email and password
      if(db.RegUser(username,FName,LName,email,password1)){
          Intent in = new Intent(this,RegSuccess.class);
          startActivity(in);

      }

    }

    public void loginPage(View v){

        Intent in = new Intent(this,Main2Activity.class);
        startActivity(in);


    }

}
