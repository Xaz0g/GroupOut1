package com.example.marietopphem.groupout1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FinderPlace extends Fragment {

    private ArrayList<String> placeListing;
    private ArrayAdapter<String> allItemsAdapter;
    private EditText searchField;
    private JSONArray retrievedPlaces;
    private SharedPreferences sharedPrefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_finder_place, container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.placelistView);
        placeListing = new ArrayList<>();

        allItemsAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, placeListing);

        lv.setAdapter(allItemsAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> allItemsAdapter, View view, int position, long id) {
                String value = (String)allItemsAdapter.getItemAtPosition(position);
                Log.d("PSEARCH", value);

                choosenPlaceHandling(value);

                getActivity().finish();
            }
        });

        searchField = (EditText) rootView.findViewById(R.id.finderPlaceField);
        return rootView;
    }

    public void setRetrievedPlaces(JSONArray array, SharedPreferences sharedPrefs){

        this.sharedPrefs = sharedPrefs;
        retrievedPlaces = array;

        try {
            listPlaceResults(retrievedPlaces);
        } catch (JSONException e) {
            Log.d("PSEARCH", e.getMessage());
        }

    }

    protected void choosenPlaceHandling(String placeName){
        try {
            for(int i = 0; i < retrievedPlaces.length() ; i++){

                if(placeName.equalsIgnoreCase(retrievedPlaces.getJSONObject(i).getString("Name"))) {
                    JSONObject thePlace = retrievedPlaces.getJSONObject(i);

                    sharedPrefs.edit().putString("Name", placeName).apply();
                    sharedPrefs.edit().putString("Id", thePlace.getString("Id")).apply();

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void listPlaceResults(JSONArray json) throws JSONException {
        ArrayList<String> placeNames = new ArrayList<>();

        for(int i = 0; i < json.length(); i++)
        {
            JSONObject place = json.getJSONObject(i);

            placeNames.add(place.getString("Name"));
        }

        showPlaces(placeNames);
    }

    public String getSearchField()
    {
        return searchField.getText().toString();
    }

    public void showPlaces(ArrayList<String> places)
    {
        placeListing.clear();
        placeListing.addAll(places);
        allItemsAdapter.notifyDataSetChanged();
    }
}
