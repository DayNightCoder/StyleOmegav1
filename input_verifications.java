package com.example.sachinpc.styleomegav10;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SachinPC on 8/30/2017.
 */

public class input_verifications {

    public boolean isEmpty(View v,String[] fields,String[] fieldNames){
        //String fieldList= fields;
        boolean flag= false;

        List fieldsList= new ArrayList<String>();
        for (int i=0; i<fields.length;i++){
           if( fields[i].isEmpty()){
               fieldsList.add(fieldNames[i]);

           }

        }
        if(!fieldsList.isEmpty()) {
            String list = Arrays.toString(fieldsList.toArray()).replace("[", "").replace("]", "");
            Toast.makeText(v.getContext(), "Please fill the empty " + list + " field(s)", Toast.LENGTH_SHORT).show();
       flag=true;
        }

return flag;
    }
public boolean passwordsMisMatch(View v,String p1,String p2){
    boolean flag= false;
    if(!p1.equals(p2)){
        flag= true;
        Toast.makeText(v.getContext(), "Passwords Mismatch!", Toast.LENGTH_SHORT).show();
    }

return flag;
}
    public boolean UsernameAlreadyExists(View v, String username){
        boolean flag= false;
        dbClass db= dbClass.getInstance(v.getContext());
        if(db.userExists(username)){
            Toast.makeText(v.getContext(), "UserName already exists", Toast.LENGTH_SHORT).show();
            flag= true;
        }
        return flag;
    }

}
