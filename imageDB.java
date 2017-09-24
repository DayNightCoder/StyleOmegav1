package com.example.sachinpc.styleomegav10;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Oclemmy on 4/29/2016 for ProgrammingWizards Channel and http://www.Camposha.com.
 */
public class imageDB extends SQLiteOpenHelper {


    public imageDB(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Constants.CREATE_TB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constants.DROP_TB);

        onCreate(db);
    }
}