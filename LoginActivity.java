package com.example.sachinpc.styleomegav10;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    private EditText etusername;
    private EditText etPassword;
    dbClass db;
    input_verifications verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db= new dbClass(LoginActivity.this);
        etusername = (EditText) findViewById(R.id.u_name);
        etPassword = (EditText) findViewById(R.id.u_pw);

 verify= new input_verifications();
        TextView forgotPw= (TextView) findViewById(R.id.forgot_pw);
        /* forgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Dialog d = new Dialog(view.getContext());
                    d.requestWindowFeature(Window.FEATURE_NO_TITLE);

                    d.setContentView(R.layout.dialog_res_pw);
                    TextView emailTv= (TextView) d.findViewById(R.id.emailText);
                    emailTv.setText(db.getEmail(etusername.getText().toString()));


                }

        });
        */
    }
   public void btnLogin(View v){

        final String username = etusername.getText().toString();
        final String password = etPassword.getText().toString();

if(!verify.isEmpty(v,new String[]{username,password},new String[]{"username","password"})) {
    if (username.equalsIgnoreCase("Admin") && (password.equalsIgnoreCase("123"))) {
        Toast.makeText(this, "WELCOME!, " + username, Toast.LENGTH_LONG).show();
        Intent in = new Intent(this, AdminConsole.class);
        startActivity(in);

    }
    if (db.login(username, password)) {
        SharedPrefClass sp = new SharedPrefClass(this);
        sp.createLoginSession(username);

        Toast.makeText(this, "WELCOME!, " + sp.getUserName().toUpperCase(), Toast.LENGTH_LONG).show();
        Intent in = new Intent(this, Main2Activity.class);
        startActivity(in);


    }
    else
    {
        Toast.makeText(this, "Please check the username and password again!", Toast.LENGTH_SHORT).show();

    }
}
    }
    public void forgotPW(View v) {
        Dialog d = new Dialog(v.getContext());
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);

        d.setContentView(R.layout.dialog_res_pw);
        TextView emailTv = (TextView) d.findViewById(R.id.emailText);
        emailTv.setText(db.getEmail(etusername.getText().toString()));
        d.show();


        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    String msgBody= " Hey "+ etusername.getText().toString()+ ","+
                            " \n\n This is your password for styleOmega : "+db.getPassword(etusername.getText().toString())+"\n\n Have a good day! \n team StyleOmega :)" ;

                    GMailSender sender = new GMailSender("sachinwick@gmail.com",
                            "mafaithisinracing");
                    sender.sendMail("Your Password for StyleOmega", msgBody,
                            "noreply@styleOmega.com",db.getEmail(etusername.getText().toString()));
                    Log.d("success","Email sent");
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }

        }).start();
    }
}
