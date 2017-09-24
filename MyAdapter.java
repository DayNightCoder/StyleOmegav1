package com.example.sachinpc.styleomegav10;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Cart_Items> cartList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {
        public TextView name,id,price;
        public ImageView button;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.item_name);
            id = (TextView) view.findViewById(R.id.item_id);
            price = (TextView) view.findViewById(R.id.item_price);
        button= (ImageView)view.findViewById(R.id.imageView5) ;
            view.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {

        }
    }


    public MyAdapter(List<Cart_Items> cartList) {
        this.cartList = cartList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Cart_Items cart = cartList.get(position);

       holder.name.setText(cart.getName());
        holder.id.setText("No of items: "+cart.getQuantity());
        holder.price.setText(String.valueOf(cart.calTotal()));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(view.getContext(), holder.button);
                //inflating menu from xml resource
                popup.inflate(R.menu.cart_list);

                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {



                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        final dbClass db= new dbClass(view.getContext());
                        switch (item.getItemId()) {

                            case R.id.item1:
                                //handle menu1 click

                               if( db.deleteItem(cart.getID())) {
                                   Toast.makeText(view.getContext(), "Successfully deleted!", Toast.LENGTH_SHORT).show();
                                   Intent in = new Intent(view.getContext(),Main7Activity.class);
                                   view.getContext().startActivity(in);
                               }
                                break;
                            case R.id.item2:
                              //
                              //update(view,cart);

                                final Dialog dialog = new Dialog(view.getContext());
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                                dialog.setContentView(R.layout.dialog_update_cart);
                                // EditText upd_q= (EditText) view.findViewById(R.id.update_q1);
                                Button btn= (Button) dialog.findViewById(R.id.button5);
//                                final String update_Q= upd_q.getText().toString();
                                dialog.show();


                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dbClass db= new dbClass(view.getContext());
                                        EditText upd_q= (EditText) dialog.findViewById(R.id.add_q);

                                        String update_Q= upd_q.getText().toString();
                                        if( db.updateCartInfo(cart.getID(),update_Q)) {
                                            Toast.makeText(view.getContext(), "Successfully updated!", Toast.LENGTH_SHORT).show();
                                            Intent in = new Intent(view.getContext(),Main7Activity.class);
                                            view.getContext().startActivity(in);
                                        }


                                    }
                                });
                                break;

                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });
    }
/**public void update(final View view, final Cart_Items cart){
    final Dialog dialog = new Dialog(view.getContext());
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

    dialog.setContentView(R.layout.dialog_update_cart);
    // EditText upd_q= (EditText) view.findViewById(R.id.update_q1);
    Button btn= (Button) dialog.findViewById(R.id.button5);
//                                final String update_Q= upd_q.getText().toString();
    dialog.show();


    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dbClass db= new dbClass(view.getContext());
            EditText upd_q= (EditText) dialog.findViewById(R.id.update_q1);

            String update_Q= upd_q.getText().toString();
            if( db.updateCartInfo(cart.getID(),update_Q)) {
                Toast.makeText(view.getContext(), "Successfully updated!", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(view.getContext(),Main7Activity.class);
                view.getContext().startActivity(in);
            }


        }
    });


}

public double getPrice(String quantity, String price){
    double finalprice= Double.parseDouble(price)* Integer.parseInt(quantity);


    return finalprice;
}
 **/
    @Override
    public int getItemCount() {
        return cartList.size();
    }
}