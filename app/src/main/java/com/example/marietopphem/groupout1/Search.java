package com.example.marietopphem.groupout1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.Context;
import android.location.LocationManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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

public class Search extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    SharedPreferences sharedPrefs;
    LocationManager locationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.search);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(1).setChecked(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mSectionsPagerAdapter.getItem(2);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void searchForPlace(View v) {
        if (v.getId() == R.id.searhPlaceBtn) {
            mSectionsPagerAdapter.ps.searchPlace();
        }
    }

    public void searchForCategory(View v){
        if(v.getId() == R.id.searchButton){

            mSectionsPagerAdapter.cs.searchEvent();
        }
    }

        /**
         * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
         * one of the sections/tabs/pages.
         */
        class SectionsPagerAdapter extends FragmentPagerAdapter {

            PlaceSearch ps;
            CategorySearch cs;
            MapSearch ms;

            public SectionsPagerAdapter(FragmentManager fm) {
                super(fm);
            }



            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        MapSearch t3 = new MapSearch();
                        ms = t3;
                        return t3;
                    case 1:
                        CategorySearch t2 = new CategorySearch();
                        cs = t2;
                        return t2;
                    case 2:
                        PlaceSearch t1 = new PlaceSearch();
                        ps = t1;
                        ps.getFavorites(sharedPrefs.getString("Token","FAIL"));
                        return t1;
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                // Show 3 total pages.
                return 3;
            }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Karta";
                case 1:
                    return "Kategori";
                case 2:
                    return "Plats";
            }
            return null;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent(Search.this, Home.class);
                    startActivity(home);
                    return true;
                case R.id.navigation_add:
                    Intent add = new Intent(Search.this, Create.class);
                    startActivity(add);
                    return true;
                case R.id.navigation_search:
                    Intent search = new Intent(Search.this, Search.class);
                    startActivity(search);
                    return true;
                case R.id.navigation_settings:
                    Intent settings = new Intent(Search.this, AppSettings.class);
                    startActivity(settings);
                    return true;
            }
            return false;
        }

    };
}
