package com.example.sachinpc.styleomegav10;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.sachinpc.styleomegav10.R.id.img_seeProd;

/**
 * Created by SachinPC on 8/5/2017.
 */

public class msg_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private Context context;
        private LayoutInflater inflater;
        List<Message> data= Collections.emptyList();
        Message current;
    dbClass db;


        // create constructor to initialize context and data sent from MainActivity
        public msg_adapter(Context context, List<Message> data){
            this.context=context;
            inflater= LayoutInflater.from(context);
            this.data=data;
        }

        // Inflate the layout when ViewHolder created
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=inflater.inflate(R.layout.msg_layout, parent,false);
            MessageHolder holder=new MessageHolder(view);

            return holder;
        }

        // Bind data
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            // Get current position of item in RecyclerView to bind data and assign values from list
            MessageHolder myHolder= (MessageHolder) holder;
            db= new dbClass(context);
            final SharedPrefClass sp= new SharedPrefClass(context);
            final Message current=data.get(position);
            myHolder.sender.setText(current.getMsg_sender());
            myHolder.body.setText(current.getMsg_body());
            myHolder.time.setText(current.getMessage_time());
            if(current.getAdminreply()==null){
                myHolder.sender.setText(current.getMsg_sender());
            }else{
                myHolder.sender.setText(current.getMsg_sender()+ " , Admin Replied");

            }
            if(current.getMsg_sender().equals(sp.getUserName())){
                myHolder.options.setText("Edit");

            }
            else{
                myHolder.options.setText("More");
            }
            //myHolder.textPrice.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
            myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {




                    final Dialog dialog = new Dialog(view.getContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.msg_details_dialog);
                    TextView sender = (TextView) dialog.findViewById(R.id.msger);
                    TextView body = (TextView) dialog.findViewById(R.id.msg_body);
                    TextView time = (TextView) dialog.findViewById(R.id.time);
                    TextView reply = (TextView) dialog.findViewById(R.id.msg_reply);
                    sender.setText(current.getMsg_sender() +" says :" );
                    body.setText(current.getMsg_body());
                    time.setText("at :"+ current.getMessage_time());
                    if(current.getAdminreply()==null){
                        reply.setText("No reply yet");

                    }
                    else{
                        reply.setText("Admin reply : \n"+current.getAdminreply());
                    }

                    //ImageView prod_image = (ImageView) dialog.findViewById(img_seeProd);
                   // Glide.with(mContext).load(album.getThumbnail()).placeholder(R.drawable.dots_vertical).into(prod_image);

                    //final EditText quantity = (EditText) dialog.findViewById(R.id.prod_qnt);
                    // EditText upd_q= (EditText) view.findViewById(R.id.update_q1);
                    //Button btn = (Button) dialog.findViewById(R.id.pro_add_btn);
//                                final String update_Q= upd_q.getText().toString();
                    dialog.show();
                }
            });
            myHolder.options.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {



                    final Dialog dialog = new Dialog(view.getContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    if(current.getMsg_sender().equals(sp.getUserName())){

                        dialog.setContentView(R.layout.edit_msg);
                        TextView sender = (TextView) dialog.findViewById(R.id.msger);
                        TextView body = (TextView) dialog.findViewById(R.id.msg_body);
                        TextView time = (TextView) dialog.findViewById(R.id.time);
                        final EditText edit_msg= (EditText) dialog.findViewById(R.id.edit_msg);
                        Button edit_btn= (Button) dialog.findViewById(R.id.edit_btn);
                        Button dlt_btn= (Button) dialog.findViewById(R.id.Del_btn);
                        sender.setText(current.getMsg_sender() +"(me) :" );
                        body.setText(current.getMsg_body());
                        time.setText("at :"+ current.getMessage_time());
                        edit_msg.setText(current.getMsg_body());
                        edit_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String msg = edit_msg.getText().toString();
                                if (db.updateMessage(current.getMessage_no(),edit_msg.getText().toString())) {
                                    Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show();

                                    dialog.dismiss();
                                    //c.viewCart(v, sp.getUserName());
                                   //swap((ArrayList<Message>) messages,db.getMessages(Item.getItem_ID()),msg_adapter);
                                   // msg_adapter.notifyDataSetChanged();
                                    //dialog.dismiss();
                                }
                            }
                        });
                        dlt_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar.make(view,"Are you sure you want to delete the message?",Snackbar.LENGTH_LONG).setAction("Yes", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(db.deleteMessage(current.getMessage_no())){
                                            Snackbar.make(view,"Deleted message",Snackbar.LENGTH_LONG).show();}
                                    }
                                }).show();
                            }
                        });


                    }
else{
                        dialog.setContentView(R.layout.msg_details_dialog);
                        TextView sender = (TextView) dialog.findViewById(R.id.msger);
                        TextView body = (TextView) dialog.findViewById(R.id.msg_body);
                        TextView time = (TextView) dialog.findViewById(R.id.time);
                        TextView reply = (TextView) dialog.findViewById(R.id.msg_reply);
                        sender.setText(current.getMsg_sender() +" says :" );
                        body.setText(current.getMsg_body());
                        time.setText("at :"+ current.getMessage_time());
                        if(current.getAdminreply()==null){
                            reply.setText("No reply yet");

                        }
                        else{
                            reply.setText("Admin reply : \n"+current.getAdminreply());
                        }
                    }



                    //ImageView prod_image = (ImageView) dialog.findViewById(img_seeProd);
                    // Glide.with(mContext).load(album.getThumbnail()).placeholder(R.drawable.dots_vertical).into(prod_image);

                    //final EditText quantity = (EditText) dialog.findViewById(R.id.prod_qnt);
                    // EditText upd_q= (EditText) view.findViewById(R.id.update_q1);
                    //Button btn = (Button) dialog.findViewById(R.id.pro_add_btn);
//                                final String update_Q= upd_q.getText().toString();
                    dialog.show();
                }
            });

        }

        // return total item from List
        @Override
        public int getItemCount() {
            return data.size();
        }



    class MessageHolder extends RecyclerView.ViewHolder  {

            TextView sender;
            TextView body;
            TextView time;
        TextView options;
            ImageView overFlow;

            // create constructor to get widget reference
            public MessageHolder(View itemView) {
                super(itemView);
                sender = (TextView) itemView.findViewById(R.id.msg_sender);
                body = (TextView) itemView.findViewById(R.id.mesg_text);
                time = (TextView) itemView.findViewById(R.id.time_text);
                //overFlow  = (ImageView) itemView.findViewById(R.id.msg_of);
                options= (TextView) itemView .findViewById(R.id.Option_text);
              //  itemView.setOnClickListener(this);
            }

            // Click event for all items
            //@Override
          //  public void onClick(View v) {

                //    Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show();
                    // showPopupMenu(v);
            //    Dialog d= new Dialog(context);
              //  d.requestWindowFeature(Window.FEATURE_NO_TITLE);

                //d.setContentView(R.layout.msg_details_dialog);
                //TextView tv= (TextView) d.findViewById(R.id.msg_details);
               // tv.setText();
                //d.show();

            //}


        }

            }





