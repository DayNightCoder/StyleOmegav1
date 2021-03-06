package com.example.sachinpc.styleomegav10;

import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.andremion.counterfab.CounterFab;
import com.example.sachinpc.styleomegav10.dummy.DummyContent;
import com.facebook.FacebookSdk;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.util.zip.Inflater;

import static com.example.sachinpc.styleomegav10.R.id.container;

public class Main2Activity extends AppCompatActivity  {


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    TabLayout tabLayout;
    CounterFab cf;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SharedPrefClass sp= new SharedPrefClass(getApplicationContext());
        sp.checkLogin();
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("StyleOmega Inc.");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

       tabLayout= (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();
        //final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.
        //fab.setOnClickListener(new View.OnClickListener() {
          //  @Override
           // public void onClick(View view) {
             //  Intent in = new Intent(fab.getContext(),Main7Activity.class);
              //  startActivity(in);
                /// / Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
           // }
        //});
       cf= (CounterFab) findViewById(R.id.counter_fab);
        cf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(cf.getContext(),Main7Activity.class);
                  startActivity(in);
            }

        });
       // cf.setCount(10);

    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_women);
        tabLayout.getTabAt(1).setIcon(R.drawable.men_icon_w);
        tabLayout.getTabAt(2).setIcon(R.drawable.kids_icon);
        tabLayout.getTabAt(3).setIcon(R.drawable.accessories_icon);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);

        return true;
    }

    //@Override
   // public boolean onOptionsItemSelected(MenuItem item) {

   //     return super.onOptionsItemSelected(item);
   // }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();




        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {

        }
        if (id == R.id.action_search) {
            Intent in = new Intent(this,SearchActivity.class);
           startActivity(in);
        }
        if (id == R.id.action_update) {
            Intent in = new Intent(this,UpdateActivity.class);
            startActivity(in);
        }
        if (id == R.id.action_logout) {
            Intent in = new Intent(this,Main3Activity.class);
            startActivity(in);
        }
        if (id == R.id.action_ViewCart) {
            Intent in = new Intent(this,Main7Activity.class);
            startActivity(in);
        }
        if (id == R.id.action_Cart) {
          Intent in = new Intent(this,Main5Activity.class);
            startActivity(in);
           // android.app.Fragment fragment = new ItemFragment();
           // View rv= new View(this);

        //    getFragmentManager().beginTransaction().replace(container,fragment);
        }

        return super.onOptionsItemSelected(item);
    }




    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
    //    public static PlaceholderFragment newInstance(int sectionNumber) {
        //    PlaceholderFragment fragment = new PlaceholderFragment();
        //    Bundle args = new Bundle();
         //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
         //   fragment.setArguments(args);
         //   return fragment;
    //    }

      //  @Override
       /** public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView=null;
            /** if(getArguments().getInt(ARG_SECTION_NUMBER)==1){
               rootView = inflater.inflate(R.layout.fragment_blank, container, false);
Fragment f= new BlankFragment();
                getFragmentManager().beginTransaction().attach(f).commit();
             //   TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//          textView.setText("Hii");
            }
            if(getArguments().getInt(ARG_SECTION_NUMBER)==2){
           ;
              // rootView = inflater.inflate(R.layout.fragment_item_list, container, false);

             // getFragmentManager().beginTransaction().attach(fragment).commit();
               // Intent in= new Intent(this,Main5Activity.class);
               // fragment.onActivityCreated();
               // Log.d(TAG, "onCreateView:");

            }
            if(getArguments().getInt(ARG_SECTION_NUMBER)==3){
                rootView = inflater.inflate(R.layout.fragment_main4, container, false);


            }
           // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            return rootView;
        }*/
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
           // return PlaceholderFragment.newInstance(position + 1);
           // for(int i =0; i)
            dbClass db = new dbClass(getBaseContext());
            switch (position) {
                //for(int i=0;i<4;i++) {
                    case 0:

                       // ItemFragment itemfrag= new ItemFragment();
                      //  itemfrag.setItemList(db.getProductsFor("WMN"));
                        //return itemfrag;
                        //return new FragmentSetter().getFragment("WMN", getBaseContext());
return new FragmentSetter().getFragment("WMN",getBaseContext());

                   case 1:
                       return new FragmentSetter().getFragment("MEN", getBaseContext());
                       //ItemFragment itemfrag1= new ItemFragment();
                       //itemfrag1.setItemList(db.getProductsFor("MEN"));
                      // return itemfrag1;

                case 2:
                    return new FragmentSetter().getFragment("KID", getBaseContext());
            case 3:
                return new FragmentSetter().getFragment("ACC", getBaseContext());

            }

            return null;
        }

        @Override
        public int getCount() {

            // Show 4 total pages.
            return new FragmentSetter().getFragcount();
        }



        @Override
        public CharSequence getPageTitle(int position) {
        //    switch (position) {
              //  case 0:
              //      return "SECTION 1";
              //  case 1:
               //     return "SECTION 2";
               // case 2:
               //     return "SECTION 3";
           // }
            return null;
        }

    }



}
