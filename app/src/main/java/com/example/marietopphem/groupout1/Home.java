package com.example.marietopphem.groupout1;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import handlers.EventActivity;
import handlers.EventListAdapter;
import handlers.HttpHandler;
import handlers.HttpTask;
import models.EveObject;

/**
 * Created by marietopphem on 2017-05-05.
 */

public class Home extends AppCompatActivity{

    SharedPreferences sharedPrefs;


    private ListView lvEvent;
    private EventListAdapter adapter;
    private List<EveObject> eventList;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());

        lvEvent = (ListView) findViewById(R.id.listView);
        eventList = new ArrayList<>();
        fillList();

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
                    Intent settings = new Intent(Home.this, AppSettings.class);
                    startActivity(settings);
                    return true;
            }
            return false;
        }

    };

    private ArrayList<EveObject> getEventParticipation()
    {
        try {

            String eventJson = getEventJson();

            ArrayList<EveObject> eventsPart = EventActivity.parseJSON(eventJson);
            Log.d("HOME!", "" + eventsPart.size());


            return eventsPart;

        } catch (ExecutionException e) {
            Log.d("HOME!", e.getMessage());
        } catch (InterruptedException e) {
            Log.d("HOME!", e.getMessage());
        } catch (JSONException e) {
            Log.d("HOME!", e.getMessage());
        }
        return null;
    }

    private void getId() throws ExecutionException, InterruptedException {
        String token = sharedPrefs.getString("Token","FAIL");
        Log.d("HOME!", "Token : " + token);

        if(token.equalsIgnoreCase("FAIL"))
        {
            throw new NullPointerException();
        }

        String idRequest = HttpHandler.userId(token);
        Log.d("HOME!", "HttpRequest : " + idRequest);

        String response = new HttpTask().execute("get", idRequest).get();
        Log.d("HOME!", response);

        adapter.setUserId(Integer.parseInt(response.trim()));

    }

    private void fillList(){
        eventList = getEventParticipation();
    }

    private String getEventJson () throws ExecutionException, InterruptedException {
        String httpRequest = HttpHandler.getEvent("participant", sharedPrefs.getString("Token", "FAIL"), "All");
        Log.d("HOME!", httpRequest);

        String response = new HttpTask().execute("get", httpRequest).get();
        Log.d("HOME!", response);
        return response;
    }

}
