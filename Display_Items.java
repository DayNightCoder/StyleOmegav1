package com.example.sachinpc.styleomegav10;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SachinPC on 8/6/2017.
 */

public class Display_Items{

    String item_Name, item_Type, item_quantity, item_price, item_img, Item_ID, item_desc;

    //protected Display_Items(Parcel in) {
      //  Item_ID = in.readString();
        //item_Name = in.readString();
        //item_Type = in.readString();
        //item_quantity = in.readString();
        //item_price = in.readString();
        //item_img = in.readString();

    //}

   /* public static final Creator<Display_Items> CREATOR = new Creator<Display_Items>() {
        @Override
        public Display_Items createFromParcel(Parcel in) {
            return new Display_Items(in);
        }

        @Override
        public Display_Items[] newArray(int size) {
            return new Display_Items[size];
        }
    };
*/
    public String getItem_ID() {
        return Item_ID;
    }

    public Display_Items(String item_ID, String item_Name, String item_Type, String item_quantity, String item_price, String item_img,String item_desc) {
        this.Item_ID= item_ID;

        this.item_Name = item_Name;
        this.item_Type = item_Type;
        this.item_quantity = item_quantity;
        this.item_price = item_price;
        this.item_img = item_img;
        this.item_desc=item_desc;
    }

    public String getItem_Name() {
        return item_Name;
    }

    public void setItem_Name(String item_Name) {
        this.item_Name = item_Name;
    }

    public String getItem_Type() {
        return item_Type;
    }

    public void setItem_Type(String item_Type) {
        this.item_Type = item_Type;
    }

    public String getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(String item_quantity) {
        this.item_quantity = item_quantity;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_img() {
        return item_img;
    }

    public void setItem_img(String item_img) {
        this.item_img = item_img;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }
/*
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.getItem_ID());
parcel.writeString(this.getItem_Name());

        parcel.writeString(this.getItem_Type());
        parcel.writeString(this.getItem_quantity());
        parcel.writeString(this.getItem_price());
        parcel.writeString(this.getItem_img());
    }
*/
}
