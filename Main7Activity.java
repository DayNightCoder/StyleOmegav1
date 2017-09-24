package com.example.sachinpc.styleomegav10;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main7Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

dbClass db= new dbClass(this);
        SharedPrefClass sp= new SharedPrefClass(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view3);
        ArrayList<Cart_Items> cartList= new ArrayList<Cart_Items>();
        cartList.addAll(db.getCartDetails(sp.getUserName()));
        MyAdapter mAdapter = new MyAdapter(cartList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);
        TextView total= (TextView) findViewById(R.id.totalTV);
      Double Total=  new cartLogic().getTotal(db.getCartPrices(sp.getUserName()));
        total.setText("Total : " + Total.toString() );

    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }
}
