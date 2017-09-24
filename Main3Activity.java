package com.example.sachinpc.styleomegav10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);



    }

    protected void onReg(View V){
        Intent in = new Intent(this,RegisterActivity.class);
        startActivity(in);
    }
    protected void onLogin(View V){
        Intent in = new Intent(this,LoginActivity.class);
        startActivity(in);
    }
}
