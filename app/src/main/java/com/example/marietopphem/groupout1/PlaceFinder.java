package com.example.marietopphem.groupout1;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class PlaceFinder extends AppCompatActivity {

    private PlaceFinderAdapter placeFinderAdapter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_finder);

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

    public class PlaceFinderAdapter extends FragmentPagerAdapter
    {

        public PlaceFinderAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch(position)
            {
                case 0 : FinderPlace t1 = new FinderPlace();
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
