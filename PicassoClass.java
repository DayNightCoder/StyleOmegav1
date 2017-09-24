package com.example.sachinpc.styleomegav10;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by SachinPC on 7/17/2017.
 */

public class PicassoClass {
    //DOWNLOAD AND CACHE IMG
    public static void loadImage(Context c, String url, ImageView img)
    {
        if(url != null && url.length()>0)
        {
            Picasso.with(c).load(url).placeholder(R.drawable.album1).into(img);
        }else {
            Picasso.with(c).load(R.drawable.album1).into(img);
        }
    }
}