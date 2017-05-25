package com.example.marietopphem.groupout1;

/**
 * Created by marietopphem on 2017-05-12.
 */

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.marietopphem.groupout1.R.layout.t1place;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

import handlers.HttpHandler;
import handlers.HttpTask;

public class PlaceSearch extends Fragment {

    ArrayList<String> placeListing = new ArrayList<>();
    ArrayAdapter<String> allItemsAdapter;
    JSONArray favorites;
    JSONArray latestSearch;
    CheckBox favCheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.t1place, container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.searchPlaceListView);


        allItemsAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, placeListing);

        lv.setAdapter(allItemsAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> allItemsAdapter, View view, int position, long id) {
                String value = (String)allItemsAdapter.getItemAtPosition(position);
                Log.d("PLASEA", value);
                Intent i = new Intent(getActivity(), PlaceActivity.class);
                i.putExtra("Name", value);
                i.putExtra("Id", getPlaceId(value));
                startActivity(i);

            }
        });

        favCheck = (CheckBox) rootView.findViewById(R.id.favorite);
        favCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    try {
                        listPlaceResults(favorites);
                    } catch (JSONException e) {
                        Log.e("PLASEA", e.getMessage());
                    }
                }
                else
                {
                    searchPlace();
                }
            }
        });

        return rootView;
    }

    private void listPlaceResults(JSONArray json) throws JSONException {
        ArrayList<String> placeNames = new ArrayList<>();

        for(int i = 0; i < json.length(); i++)
        {
            JSONObject place = json.getJSONObject(i);

            placeNames.add(place.getString("Name"));
        }

        placeListing.clear();
        placeListing.addAll(placeNames);
        allItemsAdapter.notifyDataSetChanged();
    }

    public void searchPlace() {
        favCheck.setChecked(false);
        EditText s = (EditText) getView().findViewById(R.id.placeSearchTextField);
        String searchPlace = s.getText().toString();
        Log.d("PLASEA",searchPlace);

        CheckBox ballCourtCheck = (CheckBox) getView().findViewById(R.id.checkBallCourt);
        CheckBox outdoorGymCheck = (CheckBox) getView().findViewById(R.id.checkOutdoorGym);
        CheckBox runningTracksCheck = (CheckBox) getView().findViewById(R.id.checkRunningTracks);

        boolean ballCourt;
        if (ballCourtCheck.isChecked()) {
            ballCourt = true;
            Log.d("PLASEA","This be tha ballcourt!");
        } else {
            ballCourt = false;
            Log.d("PLASEA","What is a ballcourt?");
        }
        boolean outdoorGym;
        if (outdoorGymCheck.isChecked()) {
            outdoorGym = true;
            Log.d("PLASEA","This be tha gym!");
        } else {
            outdoorGym = false;
            Log.d("PLASEA","no gyms here");
        }
        boolean runningTracks;
        if (runningTracksCheck.isChecked()) {
            runningTracks = true;
            Log.d("PLASEA","Run for it!");
        } else {
            runningTracks = false;
            Log.d("PLASEA","No running");
        }

        String request = HttpHandler.searcPlace();

        if (ballCourt) {
            Log.d("PLASEA","Get ballCourt");
            request += "Bollplaner";
        }
        if (outdoorGym) {
            Log.d("PLASEA","Get outdoorGym");
            if(ballCourt) {
                request += "-";
            }
            request += "Utegym";
        }
        if (runningTracks) {
            Log.d("PLASEA","Get runningTracks");
            if(ballCourt || outdoorGym) {
                request += "-";
            }
            request += "Motionsspår";
        }
        if(!ballCourt && !outdoorGym && !runningTracks) {
            Log.d("PLASEA","Get nothing?");

            request += "Bollplaner-Utegym-Motionsspår";
        }

        if(!searchPlace.equals(""))
        {
            request += "/" + searchPlace;
        }

        Log.d("PLASEA", request);
        try {
            getPlaces(request);
        } catch (ExecutionException e) {
            Log.d("PLASEA", e.getMessage());
        } catch (InterruptedException e) {
            Log.d("PLASEA", e.getMessage());
        } catch (JSONException e) {
            Log.d("PLASEA", e.getMessage());
        }

    }

    private void getPlaces(String request) throws ExecutionException, InterruptedException, JSONException {

        String response = new HttpTask().execute("get",request).get();

        Log.d("PLASEA", response);

        JSONArray jArray = new JSONArray(response);
        latestSearch = jArray;

        listPlaceResults(jArray);
    }

    public void getFavorites(String token)
    {
        String request = HttpHandler.getFavorite(token);
        Log.d("PLASEA", request);

        try {
            String response = new HttpTask().execute("get", request).get();
            Log.d("PLASEA", response);

            favorites = new JSONArray(response);
        } catch (InterruptedException e) {
            Log.d("PLASEA", e.getMessage());
        } catch (ExecutionException e) {
            Log.d("PLASEA", e.getMessage());
        } catch (JSONException e) {
            Log.d("PLASEA", e.getMessage());
        }
    }

    public String getPlaceId(String name){
        try {

            if(favCheck.isChecked()){
               return getIdByName(favorites, name);

            }else{
                return getIdByName(latestSearch, name);
            }
        } catch (JSONException e) {
            Log.e("PLASEA", e.getMessage());
        }
      throw new NoSuchElementException();
    }

    private String getIdByName(JSONArray array, String name) throws JSONException {

        for(int i = 0; i < array.length(); i++){
            if(array.getJSONObject(i).getString("Name").equals(name)){
                return array.getJSONObject(i).getString("Id");
            }
        }
        throw new NoSuchElementException();
    }
}
