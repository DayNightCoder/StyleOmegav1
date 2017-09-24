package com.example.sachinpc.styleomegav10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class RegSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_success);

        ListView lv = (ListView) findViewById(R.id.listView1);
        ArrayList<String> UserList = new ArrayList();
      dbClass db= new dbClass(this);
//
        UserList.addAll(db.getAllUsersString());
//UserList.add("Hello");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
               this,
               android.R.layout.simple_list_item_1,

              UserList);

        lv.setAdapter(arrayAdapter);
    }

}

