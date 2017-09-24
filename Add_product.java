package com.example.sachinpc.styleomegav10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Add_product extends AppCompatActivity {
    dbClass db= dbClass.getInstance(this.getBaseContext());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


    }


    public void addProduct(View v){
        EditText nameEt= (EditText) findViewById(R.id.item_name);
        EditText typeEt= (EditText) findViewById(R.id.item_type);
        EditText genderEt= (EditText) findViewById(R.id.item_gender);
        EditText qntyEt= (EditText) findViewById(R.id.u_pw);
        EditText priceEt= (EditText) findViewById(R.id.item_price);
        EditText imageEt= (EditText) findViewById(R.id.item_img);
        EditText detailsEt= (EditText) findViewById(R.id.item_details);
        String name= nameEt.getText().toString();
        String type= typeEt.getText().toString();
        String gender=genderEt.getText().toString();
        String qty=qntyEt.getText().toString();
        String price= priceEt.getText().toString();
        String image= imageEt.getText().toString();
        String details= detailsEt.getText().toString();
       if( db.addProduct(name,type,qty,image,price,gender,details)){

           Toast.makeText(this, "Item Added!", Toast.LENGTH_SHORT).show();
       };


    }
}
