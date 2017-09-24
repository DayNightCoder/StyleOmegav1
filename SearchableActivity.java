package com.example.sachinpc.styleomegav10;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

public class SearchableActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchMethod(query,getBaseContext());
        }



    }
    public void searchMethod(String query, Context context){
        dbClass db= dbClass.getInstance(context);
        db.getProductSearched(query);
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.message_dialog);
        final List<Display_Items> searchResult= new ArrayList<Display_Items>();
        searchResult.addAll(db.getProductSearched(query));
        RecyclerView rV= (RecyclerView) dialog.findViewById(R.id.msg_recycler);
        final adapter adapter= new  adapter(dialog.getContext(),searchResult);
        rV.setAdapter(adapter);



    }
}
