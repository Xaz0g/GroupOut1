package com.example.marietopphem.groupout1;

/**
 * Created by marietopphem on 2017-05-12.
 */


import android.support.v4.app.Fragment;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapSearch extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.t3map, container, false);
        return rootView;
    }

    //OBS! denna metod aktiveras inte och kan därför inte kolla om den är korrekt
    public void searchOnMap(View v){
        if(v.getId() == R.id.searchButton){
            EditText s = (EditText) getView().findViewById(R.id.searchByMap);
            String searchMap = s.getText().toString();

            //Check string searchMap towards server and place on map
        }
    }
}