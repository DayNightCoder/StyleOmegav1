package com.example.sachinpc.styleomegav10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by SachinPC on 7/7/2017.
 */

public class dbClass extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "styleomega";
    // Contacts table name
    private static final String TABLE_NAME = "user_data";
    private static final String user_NAME = "sachin";

    public dbClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE user_data(No INTEGER PRIMARY KEY AUTOINCREMENT,user_Name TEXT,user_FName TEXT,user_LName TEXT,user_Email TEXT,user_PW TEXT)";
        db.execSQL(CREATE_USER_TABLE);
        String CREATE_Cart_TABLE = "CREATE TABLE cart_data(No INTEGER PRIMARY KEY AUTOINCREMENT,user_Name TEXT,item_ID TEXT,item_q TEXT,item_price TEXT)";
        db.execSQL(CREATE_Cart_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS user_data");
        db.execSQL("DROP TABLE IF EXISTS cart_data");
// Creating tables again
        onCreate(db);
    }

    public boolean RegUser(String User_name, String User_Fname, String User_Lname, String User_Email, String User_Pw) {
        boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("No", "null");
        values.put("user_Name", User_name);
        values.put("user_FName", User_Fname);
        values.put("user_LName", User_Lname);
        values.put("user_Email", User_Email);
        values.put("user_PW", User_Pw);

        if (db.insert("user_data", null, values) != -1) {
            flag = true;

        }
        db.close(); // Closing database connection
        return flag;
    }


    public boolean login(String username,String password){
        boolean flag= false;
        String selectQuery = "SELECT * FROM " + TABLE_NAME+" WHERE user_Name='"+username+"' AND user_PW='"+password+"'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        int x=cursor.getCount();
        if (x==1) {
            flag=true;
        }
        return flag;
    }


    public List<User> getAllUsers() {
        List<User> exList = new ArrayList<User>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User Ex = new User();
                Ex.setNo((cursor.getString(0)));
                Ex.setUsername(cursor.getString(1));
                Ex.setFname(cursor.getString(4));
                Ex.setLname(cursor.getString(3));


// Adding contact to list
                exList.add(Ex);
                // Log.d("dsad",Ex.getId()+Ex.getReps()+Ex.getEx_name());
            } while (cursor.moveToNext());
        }
// return contact list
        return exList;
    }

    public List<String> getAllUsersString() {
        List<String> Userlist1 = new ArrayList<String>();
// Select All Query
        String selectQuery = "SELECT *  FROM user_data";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Userlist1.add(cursor.getString(2));


// Adding contact to list

                // Log.d("dsad",Ex.getId()+Ex.getReps()+Ex.getEx_name());
            } while (cursor.moveToNext());
        }
// return contact list
        return Userlist1;
    }

    public List<String> getUpdateUserInfo(String username) {
        List<String> Userlist1 = new ArrayList<String>();
// Select All Query
        String selectQuery = "SELECT *  FROM user_data WHERE user_Name='" + username + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Userlist1.add(cursor.getString(1));
                Userlist1.add(cursor.getString(2));
                Userlist1.add(cursor.getString(3));
                Userlist1.add(cursor.getString(4));
                Userlist1.add(cursor.getString(5));

// Adding contact to list

                // Log.d("dsad",Ex.getId()+Ex.getReps()+Ex.getEx_name());
            } while (cursor.moveToNext());
        }
// return contact list
        return Userlist1;
    }

    public boolean updateUserInfo(String user_NAME, String user_Fname, String user_Lname, String user_Email, String user_Pw) {
    boolean flag= false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("No", "null");

        values.put("user_FName", user_Fname);
        values.put("user_LName", user_Lname);
        values.put("user_Email", user_Email); // dgdfgName
        values.put("user_PW", user_Pw);
if(db.update(TABLE_NAME,values,"user_Name=?",new String[] { user_NAME })==1){
    //"WHERE user_Name=?",new String[] { user_NAME }
    flag=true;
}


    return flag;
    }

    //---------------------------------------------cart-----------------------------------------------------------------------
    public boolean addToCart(String User_name, String itemID, String quantity) {
        boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("No", "null");
        values.put("user_Name", User_name);
        values.put("item_ID", itemID);
        //values.put("item_quantity", quantity);
        values.put("item_q", quantity);
        values.put("item_price", "2000");
        if (db.insert("cart_data", null, values) != -1) {
            flag = true;

        }
        db.close(); // Closing database connection
        return flag;
    }

    public Hashtable<String,String> ViewCart(String user){
       Hashtable<String, String> Cart = new Hashtable<>();
    //ArrayList<String> Cart = new ArrayList<>();

        String selectQuery = "SELECT *  FROM cart_data WHERE user_Name='" + user + "'";

    SQLiteDatabase db = this.getWritableDatabase();

    Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
    if (cursor.moveToFirst()) {
        do {
            Cart.put(cursor.getString(2),cursor.getString(3));

// Adding contact to list

            // Log.d("dsad",Ex.getId()+Ex.getReps()+Ex.getEx_name());
        } while (cursor.moveToNext());
    }
// return contact list
    return Cart;
}
    public ArrayList<Cart_Items> getCartDetails(String user) {
        ArrayList<Cart_Items> Cart_list = new ArrayList<Cart_Items>();
// Select All Query
        String selectQuery =  "SELECT *  FROM cart_data WHERE user_Name='" + user + "'";;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Cart_list.add(new Cart_Items(cursor.getString(2),cursor.getString(0),cursor.getString(3),cursor.getString(4)));


// Adding contact to list

                // Log.d("dsad",Ex.getId()+Ex.getReps()+Ex.getEx_name());
            } while (cursor.moveToNext());
        }
// return contact list
        return Cart_list;
    }
    public boolean deleteItem(String ID){
        boolean flag = false;
        String selectQuery =  "DELETE  FROM cart_data WHERE No=" + ID + "";

        SQLiteDatabase db = this.getWritableDatabase();
         db.execSQL(selectQuery);

       // if(c.getCount()==1){
            flag= true;
        //}
       // db.
              //  db.delete("cart_data","No=?",new String[] { ID });


//}
        return flag;
    }
    public boolean updateCartInfo(String No, String quantity) {
        boolean flag= false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("No", "null");

      //  values.put("item_ID", ID);
        values.put("item_q", quantity);

        if(db.update("cart_data",values,"No=?",new String[] { No})==1){
            //"WHERE user_Name=?",new String[] { user_NAME }
            flag=true;
        }


        return flag;
    }
    public ArrayList<String> getCartPrices(String user) {
        ArrayList<String> Price_list = new ArrayList<>();
// Select All Query
        String selectQuery =  "SELECT *  FROM cart_data WHERE user_Name='" + user + "'";;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
Double Price = Double.parseDouble(cursor.getString(3))*Double.parseDouble(cursor.getString(4));
                Price_list.add(String.valueOf(Price));


// Adding contact to list

                // Log.d("dsad",Ex.getId()+Ex.getReps()+Ex.getEx_name());
            } while (cursor.moveToNext());
        }
// return contact list
        return Price_list;
    }












}
