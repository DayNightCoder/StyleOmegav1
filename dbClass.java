package com.example.sachinpc.styleomegav10;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import com.andremion.counterfab.CounterFab;

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
private static dbClass dbInstance;
   static Context thisContext = null;
    public dbClass(Context context)
    {  // ;
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
     //   thisContext= context;
      //  loadDatabase ld= new loadDatabase();
        //ld.loadDatabase(context);
    }

   public static dbClass getInstance(Context context){
    if(dbInstance==null) {
        dbInstance = new dbClass(context);

    }else {
        thisContext = context;
        if(dbInstance.thisContext!=context){
           dbInstance= new dbClass(context);

        }
    }
    return dbInstance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE user_data(user_Name TEXT,user_FName TEXT,user_LName TEXT,user_Email TEXT,user_PW TEXT)";
        db.execSQL(CREATE_USER_TABLE);
        String CREATE_Cart_TABLE = "CREATE TABLE cart_data(No INTEGER PRIMARY KEY AUTOINCREMENT,user_Name TEXT,item_ID TEXT,item_q TEXT,item_price TEXT, FOREIGN KEY (item_ID) REFERENCES product_data(No))";
        db.execSQL(CREATE_Cart_TABLE);
        String CREATE_Item_TABLE = "CREATE TABLE product_data(No INTEGER PRIMARY KEY AUTOINCREMENT,item_Name TEXT,item_type TEXT, item_q TEXT,item_price TEXT, item_img TEXT, item_gender TEXT,item_desc TEXT)";
        db.execSQL(CREATE_Item_TABLE);
        String CREATE_Msg_TABLE = "CREATE TABLE msg_data(No INTEGER PRIMARY KEY AUTOINCREMENT,user_ID TEXT,itemId TEXT,msg TEXT,reply TEXT, msg_Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(CREATE_Msg_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS user_data");
        db.execSQL("DROP TABLE IF EXISTS cart_data");
        db.execSQL("DROP TABLE IF EXISTS product_data");
        db.execSQL("DROP TABLE IF EXISTS msg_data");
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
    public String getEmail(String username){
       String email = null;
        String selectQuery = "SELECT * FROM " + TABLE_NAME+" WHERE user_Name='"+username+"' ";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            email= cursor.getString(3);
        }
        return email;
    }
    public String getPassword(String username){
        String pw = null;
        String selectQuery = "SELECT * FROM " + TABLE_NAME+" WHERE user_Name='"+username+"' ";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            pw= cursor.getString(4);
        }
        return pw;
    }
    public boolean userExists(String username){
       boolean b=false;
        String selectQuery = "SELECT * FROM " + TABLE_NAME+" WHERE user_Name='"+username+"' ";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
int x= cursor.getCount();
        if (x==1) {
         b=true;
        }
        return b;
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
                //Ex.setNo((cursor.getString(0)));
                Ex.setUsername(cursor.getString(0));
                Ex.setFname(cursor.getString(3));
                Ex.setLname(cursor.getString(2));


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

                Userlist1.add(cursor.getString(1));


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
                Userlist1.add(cursor.getString(0));
                Userlist1.add(cursor.getString(1));
                Userlist1.add(cursor.getString(2));
                Userlist1.add(cursor.getString(3));
                Userlist1.add(cursor.getString(4));

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
       // values.put("item_name", itemName);
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
            Cart.put(cursor.getString(2),cursor.getString(4));

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

                Cart_list.add(new Cart_Items(cursor.getString(2),cursor.getString(0),cursor.getString(3),getPriceForID(cursor.getString(2))));
//No INTEGER PRIMARY KEY AUTOINCREMENT,user_Name TEXT,item_ID TEXT,item_q TEXT,item_price TEXT, FOREIGN KEY (item_ID) REFERENCES product_data(No)


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
                //Quantity* price
Double Price = Double.parseDouble(cursor.getString(3))*Double.parseDouble(getPriceForID(cursor.getString(2)));
                Price_list.add(String.valueOf(Price));


// Adding contact to list

                // Log.d("dsad",Ex.getId()+Ex.getReps()+Ex.getEx_name());
            } while (cursor.moveToNext());
        }
// return contact list
        return Price_list;
    }


//-----------------------------------------------Products-------------------------------------------------------------------------
    public boolean addProduct(String Item_name,String item_type, String item_q, String img_path, String price, String gender,String desc){

        boolean flag= false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("No", "null");
        values.put("item_Name", Item_name);
        values.put("item_type", item_type);
        values.put("item_q", item_q);
        values.put("item_price", price);
        values.put("item_img", img_path);
       values.put("item_gender", gender);
        values.put("item_desc", desc);
        if (db.insert("product_data", null, values) != -1) {
            flag = true;


        }
        db.close(); // Closing database connection
        return flag;

    }
    public String getQuantityForId(String id){
        String qty=null;
        String selectQuery =  "SELECT *  FROM product_data where No='"+id+"'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do{
        qty=cursor.getString(3);
            } while (cursor.moveToNext());
            }
        return qty;
    }


    public ArrayList<Display_Items> getProducts() {
        ArrayList<Display_Items> Cart_list = new ArrayList<Display_Items>();
// Select All Query
        String selectQuery =  "SELECT *  FROM product_data ";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Cart_list.add(new Display_Items(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(7)));

// Adding contact to list

                // Log.d("dsad",Ex.getId()+Ex.getReps()+Ex.getEx_name());
            } while (cursor.moveToNext());
        }
// return contact list
        return Cart_list;
    }
public String getNameforID(String pro_ID){
    String name= null;
    String selectQuery =  "SELECT *  FROM product_data where No='"+pro_ID+"'";
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);

    if (cursor.moveToFirst()) {
        do {

            name = cursor.getString(1);


        } while (cursor.moveToNext());

    }
    return name;
    }
    public String getPriceForID(String pro_ID){
        String price = null;
        String selectQuery =  "SELECT *  FROM product_data where No='"+pro_ID+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                price=cursor.getString(4);


            } while (cursor.moveToNext());
        }
    return price;
}
    public boolean updatProductQuantity(String No, String quantity) {
        boolean flag = false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("No", "null");

        //  values.put("item_ID", ID);
        values.put("item_q", quantity);

        if (db.update("product_data", values, "No=?", new String[]{No}) == 1) {
            //"WHERE user_Name=?",new String[] { user_NAME }

            flag = true;
        }
    return flag;
    }
    public ArrayList<Display_Items> getProductsFor(String gender) {
        ArrayList<Display_Items> product_list = new ArrayList<Display_Items>();
// Select All Query
        String selectQuery =  "SELECT *  FROM product_data WHERE item_gender='"+gender+"'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                product_list.add(new Display_Items(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(7)));


// Adding contact to list

                // Log.d("dsad",Ex.getId()+Ex.getReps()+Ex.getEx_name());
            } while (cursor.moveToNext());
        }
// return contact list
        return product_list;
    }
    public ArrayList<Display_Items> getProductSearched(String name) {
        ArrayList<Display_Items> product_list = new ArrayList<Display_Items>();
// Select All Query
        String selectQuery =  "SELECT *  FROM product_data WHERE item_Name LIKE '%"+name+"%'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                product_list.add(new Display_Items(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(7)));


// Adding contact to list

                // Log.d("dsad",Ex.getId()+Ex.getReps()+Ex.getEx_name());
            } while (cursor.moveToNext());
        }
// return contact list
        return product_list;
    }


//------------------------------------messaging----------------------------------------------

    public boolean addMessage(String Item_ID,String sender,String msg, String AdminReply){

        boolean flag= false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("No", "null");
        values.put("itemId", Item_ID);
        values.put("user_ID", sender);
        values.put("msg", msg);
        values.put("reply", AdminReply);
        //values.put("item_img", img_path);
        // values.put("user_PW", User_Pw);

        if (db.insert("msg_data", null, values) != -1) {
            flag = true;


        }
        db.close(); // Closing database connection
        return flag;

    }
    public ArrayList<Message> getMessages(String item) {
        ArrayList<Message> Message_list = new ArrayList<Message>();
// Select All Query
        String selectQuery =  "SELECT *  FROM msg_data Where itemId='"+item+"' ";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

               Message_list.add(new Message(cursor.getString(0),cursor.getString(1),cursor.getString(5),cursor.getString(4),cursor.getString(2),cursor.getString(3)));
// "CREATE TABLE msg_data(No INTEGER PRIMARY KEY AUTOINCREMENT,user_ID TEXT,itemId TEXT,msg TEXT,reply TEXT, msg_Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

// Adding contact to list

                // Log.d("dsad",Ex.getId()+Ex.getReps()+Ex.getEx_name());
            } while (cursor.moveToNext());
        }
// return contact list
        return Message_list;
    }
    public boolean updateMessage(String No, String body) {
        boolean flag= false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("No", "null");

        //  values.put("item_ID", ID);
        values.put("msg", body);

        if(db.update("msg_data",values,"No=?",new String[] { No})==1){
            //"WHERE user_Name=?",new String[] { user_NAME }
            flag=true;
        }


        return flag;
    }
    public boolean deleteMessage(String ID){
        boolean flag = false;
        String selectQuery =  "DELETE  FROM msg_data WHERE No=" + ID + "";

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




}
