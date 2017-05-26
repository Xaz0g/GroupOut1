package com.example.marietopphem.groupout1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Elina on 2017-05-10.
 */

public class Settings extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(3).setChecked(true);
    }

    public void save_settings(View v){
        Intent i = new Intent(Settings.this, Home.class);
        startActivity(i);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent(Settings.this, Home.class);
                    startActivity(home);
                    return true;
                case R.id.navigation_add:
                    Intent add = new Intent(Settings.this, Create.class);
                    startActivity(add);
                    return true;
                case R.id.navigation_search:
                    Intent search = new Intent(Settings.this, Search.class);
                    startActivity(search);
                    return true;
                case R.id.navigation_settings:
                    Intent settings = new Intent(Settings.this, Settings.class);
                    startActivity(settings);
                    return true;
            }
            return false;
        }

    };
}

