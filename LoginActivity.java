package com.example.sachinpc.styleomegav10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText etusername;
    private EditText etPassword;
    dbClass db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etusername = (EditText) findViewById(R.id.update_q);
        etPassword = (EditText) findViewById(R.id.password1);

    }
   public void btnLogin(View v){
       db= new dbClass(LoginActivity.this);
        final String username = etusername.getText().toString();
        final String password = etPassword.getText().toString();


    if(db.login(username,password)){
            SharedPrefClass sp= new SharedPrefClass(this);
            sp.createLoginSession(username);
            Toast.makeText(this,"WELCOME!, "+ sp.getUserName().toUpperCase(),Toast.LENGTH_LONG).show();
            Intent in = new Intent(this,Main3Activity.class);
            startActivity(in);




       }
    }
}
