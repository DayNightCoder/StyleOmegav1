package com.example.sachinpc.styleomegav10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_update);
        etusername = (EditText) findViewById(R.id.update_q);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword1 = (EditText) findViewById(R.id.password1);
        etPassword2 = (EditText) findViewById(R.id.Password2);
        etFName = (EditText) findViewById(R.id.fname);
        etLName = (EditText) findViewById(R.id.lname);
        // Get Reference to variables
        setValues();


    }

    public String getLoggedUser() {
        SharedPrefClass sp = new SharedPrefClass(this);
        return sp.getUserName();

    }

    public void setValues() {
        dbClass db = new dbClass(this);
        ArrayList<String> ar = new ArrayList<String>();

        ar.addAll(db.getUpdateUserInfo(getLoggedUser()));
       // Toast.makeText(this, ar.get(0).toString(), Toast.LENGTH_LONG).show();
        etusername.setText("UserID : " +ar.get(0).toString());

        etFName.setText(ar.get(1).toString());
        etLName.setText(ar.get(2).toString());
        etEmail.setText(ar.get(3).toString());
        etPassword1.setText(ar.get(4).toString());
        etPassword2.setText(ar.get(4).toString());
    }

    public void getValues(View view) {
        dbClass db = new dbClass(this);
        ArrayList<String> ar = new ArrayList<String>();

       // ar.addAll(db.getUpdateUserInfo(getLoggedUser()));
       // Toast.makeText(this, ar.get(0).toString(), Toast.LENGTH_LONG).show();
        String username = etusername.getText().toString();
        String user_Fname = etFName.getText().toString();
        String user_Lname = etLName.getText().toString();
        String user_Email = etEmail.getText().toString();
        if (passwordsmatch()) {
            String user_PW1 = etPassword1.getText().toString();
            if(db.updateUserInfo(getLoggedUser(),user_Fname,user_Lname,user_Email,user_PW1)){

                Toast.makeText(this,R.string.successful_update,Toast.LENGTH_LONG).show();

                Intent in = new Intent(this,Main2Activity.class);
                startActivity(in);

            }else{
                Toast.makeText(this,R.string.Unable_Update,Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,R.string.Password_mismatch,Toast.LENGTH_LONG).show();
        }

    }


    public boolean passwordsmatch() {
        boolean flag = false;
        if (etPassword1.getText().toString().equals(etPassword2.getText().toString())) {
            flag = true;
        }
        return flag;
    }
}