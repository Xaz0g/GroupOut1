package com.example.marietopphem.groupout1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ExecutionException;

import handlers.HttpHandler;
import handlers.HttpTask;

public class PlaceFinder extends AppCompatActivity {

    private PlaceFinderAdapter placeFinderAdapter;
    private ViewPager viewPager;

    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_finder);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.finder_toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        placeFinderAdapter = new PlaceFinderAdapter(getSupportFragmentManager());


        viewPager = (ViewPager) findViewById(R.id.placeContainer);
        viewPager.setAdapter(placeFinderAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.finder_tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    public void searchPlace(View view){
        if (view.getId()== R.id.FinderSearchBtn){
            String searchTerm = placeFinderAdapter.fp.getSearchField();
            String categories = "Utegym-Bollplaner-Motionsspår";

            String httpRequest= HttpHandler.searchForPlace(categories,searchTerm);
            Log.d("PSEARCH", httpRequest);

            try {
                String response = new HttpTask().execute("GET",httpRequest).get();
                Log.d("PSEARCH", response);
            } catch (InterruptedException e)
            {
                Log.e("PSEARCH",e.getMessage());
            }
            catch (ExecutionException e)
            {
                Log.e("PSEARCH",e.getMessage());
            }
        }
    }

    public class PlaceFinderAdapter extends FragmentPagerAdapter
    {
        FinderPlace fp;

        public PlaceFinderAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch(position)
            {
                case 0 : FinderPlace t1 = new FinderPlace();
                    fp = t1;
                    return t1;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Sök";
            }
            return null;
        }
    }
}
