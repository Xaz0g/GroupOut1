package com.example.marietopphem.groupout1;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import handlers.EventListAdapter;
import models.Event;

/**
 * Created by marietopphem on 2017-05-05.
 */

public class Home extends AppCompatActivity{

    private ListView lvEvent;
    private EventListAdapter adapter;
    private List<Event> eventList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(0).setChecked(true);

        lvEvent = (ListView) findViewById(R.id.listView);

        eventList = new ArrayList<>();
        //add data
        eventList.add(new Event("Hoppning", "Hagaparken", "20170501", "10:00", "12:00", "6", "4", "Hej", true));
        eventList.add(new Event("Springa", "Utegym", "20170501", "10:00", "12:00", "6", "4", "Fångad av en stormvind!", false));
        eventList.add(new Event("Simma", "Farsta", "20170501",  "10:00", "12:00", "6", "4","Hej igen", true));
        eventList.add(new Event("Pilla", "Hagaparken", "20170501","10:00", "12:00", "6", "4", "Kolla här!", false));

        adapter = new EventListAdapter(getApplicationContext(), eventList);
        lvEvent.setAdapter(adapter);

        lvEvent.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long name) {

                Toast.makeText(getApplicationContext(), "Event klickat id" + view.getTag(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent(Home.this, Home.class);
                    startActivity(home);
                    return true;
                case R.id.navigation_add:
                    Intent add = new Intent(Home.this, Create.class);
                    startActivity(add);
                    return true;
                case R.id.navigation_search:
                    Intent search = new Intent(Home.this, Search.class);
                    startActivity(search);
                    return true;
                case R.id.navigation_settings:
                    Intent settings = new Intent(Home.this, Settings.class);
                    startActivity(settings);
                    return true;
            }
            return false;
        }

    };
}
