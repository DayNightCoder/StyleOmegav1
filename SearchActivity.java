package com.example.sachinpc.styleomegav10;


import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {


    SearchView searchView ;

    ArrayList<Display_Items> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // adds item to action bar
        getMenuInflater().inflate(R.menu.menu_search, menu);

        // Get Search item from action bar and Get Search service
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchManager searchManager = (SearchManager) SearchActivity.this.getSystemService(Context.SEARCH_SERVICE);
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(SearchActivity.this.getComponentName()));
            searchView.setIconified(true);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("well", " this submitted");
                    Toast.makeText(SearchActivity.this,query, Toast.LENGTH_SHORT).show();
                    new AsyncFetch(query).execute();
                    //searchView.setSearchableInfo(searchManager.getSearchableInfo(SearchActivity.this.getComponentName()))
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("well", " this worked");
                    return false;
                }
            });
searchView.setSubmitButtonEnabled(true);

        }

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    // Every time when you press search button on keypad an Activity is recreated which in turn calls this function
    @Override
    protected void onNewIntent(Intent intent) {
        // Get search query and create object of class AsyncFetch
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            if (searchView != null) {
                searchView.clearFocus();

            }
            Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
          //  new AsyncFetch(query).execute();


        }
    }

    // Create class AsyncFetch
    private class AsyncFetch extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(SearchActivity.this);

        String searchQuery;

        public AsyncFetch(String searchQuery){
            this.searchQuery=searchQuery;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
dbClass db= dbClass.getInstance(getApplicationContext());
            itemsList=db.getProductSearched(searchQuery);
            return "done";

        }

        @Override
        protected void onPostExecute(String result) {
            pdLoading.dismiss();
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.searchRv);
            recyclerView.setAdapter(new adapter(getApplicationContext(),itemsList));



            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getBaseContext(), 2);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(1), true));
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            SearchAdapter adapterobj = new SearchAdapter(getBaseContext(),itemsList);
            recyclerView.setAdapter(adapterobj);
            //this method will be running on UI thread
           // Toast.makeText(SearchActivity.this,itemsList.toString(), Toast.LENGTH_SHORT).show();
            recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));




        }
        private int dpToPx(int dp) {
            Resources r = getResources();
            return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
        }
    }
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
}