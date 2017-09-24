package com.example.sachinpc.styleomegav10;

import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
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

import com.andremion.counterfab.CounterFab;
import com.bumptech.glide.Glide;
import com.bumptech.glide.util.ExceptionCatchingInputStream;

import java.util.ArrayList;
import java.util.List;

import static com.example.sachinpc.styleomegav10.R.id.img_seeProd;


/**
 * Created by SachinPC on 7/11/2017.
 */

public class MensAdapter extends RecyclerView.Adapter<MensAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;
  //  CounterFab cf;
//public Album album;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow, mesg;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title_mens);
            count = (TextView) view.findViewById(R.id.count_mens);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail_mens);
            overflow = (ImageView) view.findViewById(R.id.overflow_mens);
            mesg = (ImageView) view.findViewById(R.id.mesg_mens);
           // album= new Album();
        }
    }


    public MensAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mens_card, parent, false);

        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Album album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfSongs() + " songs");
        final cartLogic c = new cartLogic();
        final dbClass db = new dbClass(mContext);

        final SharedPrefClass sp = new SharedPrefClass(mContext);
        //  int resID = mContext.getResources().getIdentifier(album.getThumbnail() , "drawable", mContext.getPackageName());
        // loading album cover using Glide library
        //  PicassoClient.loadImage(c,tvShows.get(position).getImageUrl(),holder.img);
        Glide.with(mContext).load(album.getThumbnail()).placeholder(R.drawable.dots_vertical).into(holder.thumbnail);

        View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View v) {
                if (v.equals(holder.thumbnail)) {
                    final SharedPrefClass sp = new SharedPrefClass(v.getContext());
                    // Toast.makeText(v.getContext(),,Toast.LENGTH_LONG).show();
                    final cartLogic c = new cartLogic();
                    //  c.addToCart(v,album.getName(),String.valueOf(album.getNumOfSongs()));
                    final Dialog dialog = new Dialog(v.getContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                    dialog.setContentView(R.layout.add_cart_dialog);
                    // EditText upd_q= (EditText) view.findViewById(R.id.update_q1);
                    Button btn = (Button) dialog.findViewById(R.id.btn_add);
//                                final String update_Q= upd_q.getText().toString();
                    dialog.show();


                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dbClass db = new dbClass(v.getContext());
                            EditText quantity = (EditText) dialog.findViewById(R.id.add_q);

                            String add_Q = quantity.getText().toString();
                          //  if (c.addToCart(v, album.getName(), add_Q)) {
                            //    c.viewCart(v, sp.getUserName());

                              //  c.cf(2);
                               // c.cf(v.getContext().getApplicationContext(),2);
                            //    dialog.dismiss();
                           // }
                            // if( db.updateCartInfo(.getID(),update_Q)) {
                            //  Toast.makeText(view.getContext(), "Successfully updated!", Toast.LENGTH_SHORT).show();
                            //  Intent in = new Intent(view.getContext(),Main7Activity.class);
                            //  view.getContext().startActivity(in);
                            //}


                        }
                    });
//                    c.viewCart(v,sp.getUserName());
                }
            }
        };
        //holder.thumbnail.setOnClickListener(clickListener);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Dialog dialog = new Dialog(view.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_seeproduct);
                TextView tv = (TextView) dialog.findViewById(R.id.product_name);
                tv.setText(album.getName());
                ImageView prod_image = (ImageView) dialog.findViewById(img_seeProd);
                Glide.with(mContext).load(album.getThumbnail()).placeholder(R.drawable.dots_vertical).into(prod_image);

                final EditText quantity = (EditText) dialog.findViewById(R.id.prod_qnt);
                // EditText upd_q= (EditText) view.findViewById(R.id.update_q1);
                Button btn = (Button) dialog.findViewById(R.id.pro_add_btn);
//                                final String update_Q= upd_q.getText().toString();
                dialog.show();


                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String add_Q = quantity.getText().toString();
                     //   if (c.addToCart(view, album.getName(), add_Q)) {
                     //       c.viewCart(view, sp.getUserName());
                      //      dialog.dismiss();
                      //  }
                    }
                });
                // EditText upd_q= (EditText) view.findViewById(R.id.update_q1);
                // Button btn= (Button) dialog.findViewById(R.id.btn_add);
//                                final String update_Q= upd_q.getText().toString();


                //  showPopupMenu(holder.overflow);
            }
        });
        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(view.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_seeproduct);
                TextView tv = (TextView) dialog.findViewById(R.id.product_name);
                tv.setText(album.getName());
                ImageView prod_image = (ImageView) dialog.findViewById(img_seeProd);
                Glide.with(mContext).load(album.getThumbnail()).placeholder(R.drawable.dots_vertical).into(prod_image);
                final EditText quantity = (EditText) dialog.findViewById(R.id.prod_qnt);

                // EditText upd_q= (EditText) view.findViewById(R.id.update_q1);
                Button btn = (Button) dialog.findViewById(R.id.pro_add_btn);
//                                final String update_Q= upd_q.getText().toString();
                dialog.show();


                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String add_Q = quantity.getText().toString();
                       // if (c.addToCart(v, album.getName(), add_Q)) {
                        //    c.viewCart(v, sp.getUserName());
                       //     dialog.dismiss();
                      //  }
                    }
                });
                // EditText upd_q= (EditText) view.findViewById(R.id.update_q1);
                // Button btn= (Button) dialog.findViewById(R.id.btn_add);
//                                final String update_Q= upd_q.getText().toString();


                //  showPopupMenu(holder.overflow);
            }

        });

    holder.mesg.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Dialog dialog = new Dialog(view.getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.message_dialog);
List<Message> messages= new ArrayList<Message>();
           // messages.add(new Message("sachin","hello"));
            //messages.add(new Message("sachin","hello1"));
           // messages.add(new Message("sachin","hello2"));
           // messages.add(new Message("sachin","hello3"));
            RecyclerView rV= (RecyclerView) dialog.findViewById(R.id.msg_recycler);
          msg_adapter msg_adapter= new msg_adapter(dialog.getContext(),messages);
          rV.setAdapter(msg_adapter);
EditText msgBox= (EditText) view.findViewById(R.id.msg_Box);
Button sendBtn = (Button) view.findViewById(R.id.send_btn);
            LinearLayoutManager llm = new LinearLayoutManager(dialog.getContext());
       rV.setLayoutManager(llm);

            //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(dialog.getContext(),
              //      llm.getOrientation());
           // rV.addItemDecoration(dividerItemDecoration);
            dialog.show();
        }

    });
}
    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
