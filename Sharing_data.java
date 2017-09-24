package com.example.sachinpc.styleomegav10;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

/**
 * Created by SachinPC on 8/7/2017.
 */

public class Sharing_data {

public void share(Context context,Display_Items item){
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    String share_data= "Buy your favourite "+item.getItem_Type()+" " +item.getItem_Name() +" From StyleOmega, at just Rs." +item.getItem_price()+ "only!  see image "+ item.getItem_img();
    sendIntent.putExtra(Intent.EXTRA_TEXT,share_data);
    sendIntent.setType("text/plain");
    context.startActivity(Intent.createChooser(sendIntent, context.getResources().getText(R.string.share)));

}
    public void fb_share(ShareDialog shareDialog,Display_Items item){

        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setQuote("Buy your favourite "+item.getItem_Type()+" " +item.getItem_Name() +" From StyleOmega, at just Rs." +item.getItem_price()+ "only!  see image "+ item.getItem_img())
                .setContentUrl(Uri.parse(item.getItem_img()))
                .build();
        shareDialog.show(linkContent);
    }

}
