package com.example.sachinpc.styleomegav10;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;

/**
 * Created by SachinPC on 8/12/2017.
 */

public class FragmentSetter {
    ItemFragment fragment;
    dbClass db;
    public Fragment getFragment(String type, Context context){
        db= dbClass.getInstance(context);
        if(type=="WMN"){
          ItemFragment fragment= new ItemFragment();
            fragment.setType("WMN");
            fragment.setItemList(db.getProductsFor(type));
            // /Bundle b = new Bundle();
          //  ArrayList<Display_Items> display_itemsArrayList = new ArrayList<>();
          //  display_itemsArrayList.
        //  b.putString("Type",type);
            // b.putParcelableArrayList("ItemsList",(ArrayList<? extends Parcelable>) db.getProducts());
            //fragment.setArguments(b);

            //fragment.setItemList(db.getProductsFor("WMN"));
      return fragment;
        }
        if(type=="MEN"){
          ItemFragment fragment1= new ItemFragment();
            fragment1.setItemList(db.getProductsFor(type));
//fragment1.setType("MEN");

       return fragment1;
        }
        if(type=="KID") {
            ItemFragment fragment1 = new ItemFragment();
            fragment1.setItemList(db.getProductsFor(type));
         //   fragment1.setType("KID");
            return fragment1;
        }
        if(type=="ACC") {
            ItemFragment fragment1 = new ItemFragment();
            fragment1.setItemList(db.getProductsFor(type));
            //fragment1.setType("ACC");
            return fragment1;
        }



else return null;
     //  return fragment;
    }






    public Fragment giveFrag(String type,Context context){
        db= dbClass.getInstance(context);
        ItemFragment items = new ItemFragment();

        items.setItemList(null);
        return items;

    }
    public int getFragcount(){

        return 4;
    }
}
