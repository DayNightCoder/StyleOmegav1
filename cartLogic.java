package com.example.sachinpc.styleomegav10;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.andremion.counterfab.CounterFab;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

import static android.content.ContentValues.TAG;

/**
 * Created by SachinPC on 7/18/2017.
 */

public class cartLogic {
    SharedPrefClass sp;
    dbClass db;

    public boolean addToCart(View v, String item_ID, String item_Q) {
        boolean flag = false;
        sp = new SharedPrefClass(v.getContext());
        db = dbClass.getInstance(v.getContext());
        sp.checkLogin();
        sp.CartSave(item_ID);
        if (db.addToCart(sp.getUserName(), item_ID, item_Q)) {

            Toast.makeText(v.getContext(), "Added succcessfully", Toast.LENGTH_LONG).show();
flag=true;
        }
        return flag;

    }

    public void viewCart(View v, String u) {

        //Hashtable<String,String> Cart= new Hashtable<>();
        ArrayList<Cart_Items> cart = new ArrayList();
        //Cart.putAll(sp.Cart());
        cart.addAll(db.getCartDetails(u));
        ArrayList ar = new ArrayList();
        // ar.addAll(sp.Cart().keySet());
        //  String items=  sp.Cart().toArray().toString();
        int I = db.getCartDetails(u).size();
//Cart.putAll(db.ViewCart(u));
//if(db.ViewCart(u).size()>0){
        //   Toast.makeText(v.getContext(),Cart.toString(), Toast.LENGTH_LONG).show();

//}
        // String items=ar.toArray().toString();
        Toast.makeText(v.getContext(), cart.toString(), Toast.LENGTH_LONG).show();

        Log.d(TAG, "viewCart: " + I);

    }

    public double getTotal(ArrayList<String> price) {
        double total = 0;
        Iterator<String> i = price.iterator();
        while (i.hasNext()) {
            String amount = i.next();
            total =total+ Double.parseDouble(amount);
        }
        return total;
    }

    public void cf(int count) {
        CounterFab cf = (CounterFab) new Main2Activity().findViewById(R.id.counter_fab);
        cf.setCount(count);

    }

    public boolean UpdateQuantities(View v, int cartQ1, int cartQ2, int productsQ, String proID) {
        boolean flag = false;
        int newProQ = 0;
       // Toast.makeText(v.getContext(), "inventory updated to"+newProQ, Toast.LENGTH_SHORT).show();
        db = dbClass.getInstance(v.getContext());
        if (cartQ1 < cartQ2) {
            newProQ = productsQ - (cartQ2 - cartQ1);
            db.updatProductQuantity(proID, String.valueOf(newProQ));
           // Toast.makeText(v.getContext(), "inventory updated to"+newProQ, Toast.LENGTH_SHORT).show();
            flag = true;
        }

        if (cartQ2 < cartQ1) {
            newProQ = productsQ + (cartQ1 - cartQ2);
            db.updatProductQuantity(proID, String.valueOf(newProQ));
            flag = true;
        }
        return flag;
    }
}
