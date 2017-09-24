package com.example.sachinpc.styleomegav10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText etusername;
    private EditText etEmail;
    private EditText etFName;
    private EditText etLName;
    private EditText etPassword1;
    private EditText etPassword2;
    dbClass db = dbClass.getInstance(this);
    input_verifications verifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Get Reference to variables
        etusername = (EditText) findViewById(R.id.u_name);
        etEmail = (EditText) findViewById(R.id.email_reg);
        etPassword1 = (EditText) findViewById(R.id.u_pw);
        etPassword2 = (EditText) findViewById(R.id.confirm_password);
        etFName = (EditText) findViewById(R.id.fname);
        etLName = (EditText) findViewById(R.id.last_name);
 verifications = new input_verifications();

    }


    // Triggers when LOGIN Button clicked
    public void checkSignUp(View v) {

        // Get text from email and passord field
        final String username = etusername.getText().toString();
        final String FName = etFName.getText().toString();
        final String LName = etLName.getText().toString();
        final String email = etEmail.getText().toString();
        final String password1 = etPassword1.getText().toString();
        final String password2 = etPassword2.getText().toString();
        // Initialize  AsyncLogin() class with email and password
        if(!verifications.isEmpty(v,new String[]{username,FName,LName,email,password1,password2},new String[]{"Username","First Name","Last Name","Email","Password","Password Confirmation"})) {
           if(!verifications.UsernameAlreadyExists(v,username)){
                if(!verifications.passwordsMisMatch(v,password1,password2)){
            if (db.RegUser(username, FName, LName, email, password1)) {
                Toast.makeText(this, "Succefully registered, you may now Login", Toast.LENGTH_SHORT).show();
                loadDatabase ld= new loadDatabase();
                ld.loadDatabase(v.getContext());
                Intent in = new Intent(this, LoginActivity.class);
                startActivity(in);
            }
            }
            }
        }
    }



}
