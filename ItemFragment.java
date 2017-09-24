package com.example.sachinpc.styleomegav10;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ItemFragment extends Fragment {

    private RecyclerView recyclerView;
    //private Item_Adapter adapter;
    View rootView;
    private   ArrayList<Display_Items> ItemList ;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      // type= savedInstanceState.getString("Type");
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_women, container, false);
        //    initCollapsingToolbar();
  //      ItemList= new ArrayList<>();

//ArrayList<Display_Items> items= savedInstanceState.getParcelableArrayList("ItemsArray");
       // setItemList(items);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.wmen_recycler);
        recyclerView.setHasFixedSize(true);

        //adapter = new Item_Adapter(rootView.getContext(), albumList);
       // ProductLogic pl = new ProductLogic();
       // pl.loadProducts(rootView);

        //cartLogic cL= new cartLogic();
        //  cL.cf(this.getActivity(),5);

     //  final dbClass db = new dbClass(this.getContext());
        //getAlbumList().addAll(db.getProducts());
     // setItemList(db.getProductsFor(type));
        //  new Thread(new Runnable() {
        //    @Override
        //   public void run() {

setAdapter(recyclerView,rootView,this.getContext(),ItemList);

        //    }
        //   }).start();
        //


        // prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) rootView.findViewById(R.id.backdrop_mens));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootView;
    }

    public void setAdapter(RecyclerView recyclerView, View rootView, Context context, List <Display_Items> itemsList){
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(rootView.getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(2), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter adapterobj = new adapter(context,itemsList);
        recyclerView.setAdapter(adapterobj);

    }

    public ArrayList<Display_Items> getItemList() {
        return ItemList;
    }




    public void setItemList(ArrayList<Display_Items> ItemList) {
        this.ItemList = ItemList;
    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}