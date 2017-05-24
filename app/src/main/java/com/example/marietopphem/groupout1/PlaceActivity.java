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

public class PlaceActivity extends AppCompatActivity {

    ArrayList<String> eventListing = new ArrayList<>();
    ArrayAdapter<String> eventListAdapter;
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

        eventListAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, eventListing);
        listv.setAdapter(eventListAdapter);

        extras = getIntent().getExtras();
        if(extras != null){
            String recievedData = extras.getString("Name");
            TextView v = (TextView) findViewById(R.id.placeActivityNameField);
            v.setText(recievedData);
        }

        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long name) {
                String value = (String)eventListAdapter.getItem(position);
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
            eventListing.add(eventsOnPlace.getJSONObject(i).getString("name"));
        }

        eventListAdapter.notifyDataSetChanged();
    }
}
