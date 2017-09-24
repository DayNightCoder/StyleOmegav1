package com.example.sachinpc.styleomegav10;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sachinpc.styleomegav10.Constants;
import com.example.sachinpc.styleomegav10.dbClass;

/**
 * Created by Oclemmy on 4/29/2016 for ProgrammingWizards Channel and http://www.Camposha.com.
 */
public class DBadapter {
    Context c;
    SQLiteDatabase db;
    dbClass helper;
    public DBadapter(Context c) {
        this.c = c;
        helper=new dbClass(c);
    }
    //OPEN
    public DBadapter openDB()
    {
        try {
            db=helper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
    //CLOSE
    public void closeDB()
    {
        try {
            helper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //SAVE
    public long add(String name,String url)
    {
        try {
            ContentValues cv=new ContentValues();
            cv.put(Constants.NAME,name);
            cv.put(Constants.URL, url);
            db.insert(Constants.TB_NAME, Constants.ROW_ID, cv);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    //RETRIEVE
    public Cursor getTVShows()
    {
        String[] columns={Constants.ROW_ID,Constants.NAME,Constants.URL};
        return db.query(Constants.TB_NAME,columns,null,null,null,null,null);
    }
}