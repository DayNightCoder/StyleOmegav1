package com.example.sachinpc.styleomegav10;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;
import java.util.List;

public class SearchResult extends AppCompatActivity {

    CallbackManager callbackManager;
    ShareDialog shareDialog;
    String type,name,price,id,desc,image,qty;
    Display_Items item;
    Sharing_data sd= new Sharing_data();
    dbClass db;
    SharedPrefClass sp;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        db= dbClass.getInstance(this);
        sp=new SharedPrefClass(this);

        ShareButton shareButton = (ShareButton)findViewById(R.id.fb_share_btn);

shareButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            sd.fb_share(shareDialog,item);
         ///   ShareLinkContent linkContent = new ShareLinkContent.Builder()

                 //   .setQuote("Get your favourite "+type+","+name+" from your most loyal dealer StyleOmega, at just Rs."+price)
                 //   .setContentUrl(Uri.parse("http://www.google.com"))
                //    .build();
           // shareDialog.show(linkContent);

        }
    }
});
       //

         name= getIntent().getStringExtra("Name");
         id= getIntent().getStringExtra("Id");
         desc= getIntent().getStringExtra("Desc");
         image= getIntent().getStringExtra("Img");
        type= getIntent().getStringExtra("Type");
        qty= getIntent().getStringExtra("Qty");
      price= getIntent().getStringExtra("Price");
        item=new Display_Items(id,name,type,qty,price,image,desc);
        ImageView imageView= (ImageView) findViewById(R.id.img_seeProd);
        ImageView ShareImg= (ImageView) findViewById(R.id.search_share);
        ImageView MsgImg= (ImageView) findViewById(R.id.search_msg);
        TextView nametv= (TextView) findViewById(R.id.product_name);
        TextView desctv= (TextView) findViewById(R.id.textView12);
        TextView pricetv = (TextView) findViewById(R.id.textView6);
        nametv.setText(name);
        desctv.setText(desc);
        pricetv.setText("Rs. "+price+"/=");
        Glide.with(this).load(image).placeholder(R.drawable.dots_vertical).into(imageView);
        Uri uri = Uri.parse(image);

        SharePhoto photo = new SharePhoto.Builder()
                .setImageUrl(uri)
                .setCaption("Hello from Style Omega")
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        shareButton.setShareContent(content);
        ShareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sd.share(view.getContext(),item);
            }
        });
        //ShareDialog.show(this, content);
         shareDialog = new ShareDialog(this);
        // this part is optional
        callbackManager = CallbackManager.Factory.create();
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    public void onMsg(View v){


        final Dialog dialog = new Dialog(v.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.message_dialog);
        final List<Message> messages= new ArrayList<Message>();
        messages.addAll(db.getMessages(item.getItem_ID()));
        RecyclerView rV= (RecyclerView) dialog.findViewById(R.id.msg_recycler);
        final msg_adapter msg_adapter= new msg_adapter(dialog.getContext(),messages);
        rV.setAdapter(msg_adapter);


        LinearLayoutManager llm = new LinearLayoutManager(dialog.getContext());
        rV.setLayoutManager(llm);

        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(dialog.getContext(),
        //      llm.getOrientation());
        // rV.addItemDecoration(dividerItemDecoration);
        dialog.show();
        final EditText msgBox= (EditText) dialog.findViewById(R.id.msg_Box);
        Button sendBtn = (Button) dialog.findViewById(R.id.send_btn);
        //LinearLayoutManager llm = new LinearLayoutManager(dialog.getContext());
        // rV.setLayoutManager(llm);

        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(dialog.getContext(),
        //      llm.getOrientation());
        // rV.addItemDecoration(dividerItemDecoration);
        //  dialog.show();


        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(dialog.getContext(),
        //      llm.getOrientation());
        // rV.addItemDecoration(dividerItemDecoration);
        dialog.show();
        //   Sharing_data sd = new Sharing_data();
        // sd.share(mContext, Item);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = msgBox.getText().toString();
                if (db.addMessage(item.getItem_ID(),sp.getUserName(),msg,"hey")) {
                    Toast.makeText(v.getContext(), "Done!", Toast.LENGTH_SHORT).show();
                    //c.viewCart(v, sp.getUserName());
                    swap((ArrayList<Message>) messages,db.getMessages(item.getItem_ID()),msg_adapter);
                    msg_adapter.notifyDataSetChanged();
                    //dialog.dismiss();
                }
            }
        });
    }




    //  });

    // SharePhoto photo = new SharePhoto.Builder()
    ////     .setImageUrl(new Uri.Builder().path(Item.getItem_img()).build())
    //   .setCaption("Buy your favourite" + Item.getItem_Type() + ", " + Item.getItem_Name())
    //   .build();
    //SharePhotoContent content = new SharePhotoContent.Builder()

    //      .setContentUrl(new Uri.Builder().path(Item.getItem_img()).build())
    //    .addPhoto(photo)
    //    .build();

    //   holder.shareButton.setShareContent(content);
    // ShareDialog.show(vie,content);



public void swap(ArrayList<Message> message,ArrayList<Message> newmessage, RecyclerView.Adapter ad){
        message.clear();
        message.addAll(newmessage);
        ad.notifyDataSetChanged();
        }
    }

