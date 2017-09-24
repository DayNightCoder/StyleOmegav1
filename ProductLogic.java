package com.example.sachinpc.styleomegav10;

import android.view.View;

/**
 * Created by SachinPC on 8/6/2017.
 */

public class ProductLogic {



    public void loadProducts(View v){

        dbClass db= new dbClass(v.getContext());
        loadDatabase ld= new loadDatabase();
        ld.loadDatabase(v.getContext());
        //db.addProduct("Sachin","Men","100","https://image.ibb.co/nkhDaQ/mens10.jpg","2000","MEN");
       // db.addProduct("Sachin1","Men","100","https://image.ibb.co/kBLMUk/mens9.jpg","2000","MEN");

    }
}
