package com.example.sachinpc.styleomegav10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class AdminConsole extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_console);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new Admin_Img_Adapter(this));

        // String name = user.get(SessionManagement.User_NAME);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //Toast.makeText(MainActivity.this, "" + position,
                // Toast.LENGTH_SHORT).show();

                if(position==0){
                    Intent intent = new Intent(AdminConsole.this, Main2Activity.class);

                    startActivity(intent);


                }
                if(position==1){
                    Intent intent = new Intent(AdminConsole.this,Add_product.class);

                    startActivity(intent);


                }



            }
        });
    }
    public void onLogout(View v){
        Toast.makeText(this, "Admin logged out", Toast.LENGTH_LONG).show();
       Intent in = new Intent(this,MainActivity.class);
        startActivity(in);
    }
}
