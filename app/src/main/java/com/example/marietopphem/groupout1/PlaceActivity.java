package com.example.marietopphem.groupout1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import handlers.EventListAdapter;
import handlers.HttpHandler;
import handlers.HttpTask;
import handlers.PlaceEventAdapter;
import models.EveObject;

public class PlaceActivity extends AppCompatActivity {

    ArrayList<EveObject> eventListing = new ArrayList<>();
    PlaceEventAdapter placeEventAdapter;
    JSONArray eventsOnPlace;
    ListView listv;
    Bundle extras;

    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
        listv = (ListView) findViewById(R.id.placeActivityList);

        placeEventAdapter = new PlaceEventAdapter(getApplicationContext(), eventListing);
        placeEventAdapter.setToken(sharedPrefs.getString("Token", "FAIL"));
        listv.setAdapter(placeEventAdapter);

        extras = getIntent().getExtras();
        if(extras != null){
            String recievedData = extras.getString("Name");
            TextView v = (TextView) findViewById(R.id.placeActivityNameField);
            v.setText(recievedData);
        }

        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long name) {
                String value = (String)placeEventAdapter.getItem(position);
                Log.d("PLASEA", value);
            }
        });

        getEventsForPlace();
    }

    private void getEventsForPlace() {
        String placeId = extras.getString("Id");
        String token = sharedPrefs.getString("Token","FAIL");

        String request = HttpHandler.getEvent("place",token,placeId);
        Log.d("PLASAC",request);

        try {
            String response = new HttpTask().execute("get",request).get();
            Log.d("PLASAC",response);
            eventsOnPlace = new JSONArray(response);
            showEvents();
        } catch (InterruptedException e) {
            Log.d("PLASAC",e.getMessage());
        } catch (ExecutionException e) {
            Log.d("PLASAC",e.getMessage());
        } catch (JSONException e) {
            Log.d("PLASAC",e.getMessage());
        }
    }

    private void showEvents() throws JSONException {
        eventListing.clear();

        for(int i = 0; i < eventsOnPlace.length(); i++)
        {
            String name = eventsOnPlace.getJSONObject(i).getString("name");
            String category = eventsOnPlace.getJSONObject(i).getString("category");
            String description = eventsOnPlace.getJSONObject(i).getString("description");
            String placeId = eventsOnPlace.getJSONObject(i).getString("placeId");
            String eventDate = eventsOnPlace.getJSONObject(i).getString("eventDate");
            String startTime = eventsOnPlace.getJSONObject(i).getString("startTime");
            String endTime = eventsOnPlace.getJSONObject(i).getString("endTime");
            Boolean visible = eventsOnPlace.getJSONObject(i).getBoolean("visible");
            int id = eventsOnPlace.getJSONObject(i).getInt("id");
            int leaderId = eventsOnPlace.getJSONObject(i).getInt("leaderId");
            int minCapacity = eventsOnPlace.getJSONObject(i).getInt("minCapacity");
            int maxCapacity = eventsOnPlace.getJSONObject(i).getInt("maxCapacity");
            int registration = eventsOnPlace.getJSONObject(i).getInt("registration");
            int difficulty = eventsOnPlace.getJSONObject(i).getInt("difficulty");
            eventListing.add(new EveObject(name, category, description, placeId, eventDate, startTime, endTime, visible, id, leaderId, minCapacity, maxCapacity, registration, difficulty));
        }

        placeEventAdapter.notifyDataSetChanged();
    }
}
