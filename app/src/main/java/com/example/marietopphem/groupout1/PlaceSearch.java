package com.example.marietopphem.groupout1;

/**
 * Created by marietopphem on 2017-05-12.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static com.example.marietopphem.groupout1.R.layout.t1place;

import java.util.ArrayList;

public class PlaceSearch extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.t1place, container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.searchPlaceListView);
        ArrayList<String> listTest = new ArrayList<>();
        listTest.add("Element1");
        listTest.add("Element2");
        listTest.add("Element3");

        final ArrayAdapter<String> allItemsAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, listTest);

        lv.setAdapter(allItemsAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> allItemsAdapter, View view, int position, long id) {
                String value = (String)allItemsAdapter.getItemAtPosition(position);
                Log.d("*********", value);
            }
        });

        return rootView;
    }
    //OBS! denna metod aktiveras inte och kan därför inte kolla om den är korrekt
    public void searchForPlace(View v){
        if(v.getId() == R.id.searchButton){
            EditText s = (EditText) getView().findViewById(R.id.placeSearchTextField);
            String searchPlace = s.getText().toString();

            CheckBox ballCourtCheck = (CheckBox) getView().findViewById(R.id.checkBallCourt);
            CheckBox outdoorGymCheck = (CheckBox) getView().findViewById(R.id.checkOutdoorGym);
            CheckBox runningTracksCheck = (CheckBox) getView().findViewById(R.id.checkRunningTracks);

            boolean ballCourt;
            if (ballCourtCheck.isChecked()){
                ballCourt = true;
            }else{
                ballCourt = false;
            }
            boolean outdoorGym;
            if (outdoorGymCheck.isChecked()){
                outdoorGym = true;
            }else{
                outdoorGym = false;
            }
            boolean runningTracks;
            if (runningTracksCheck.isChecked()){
                runningTracks = true;
            }else{
                runningTracks = false;
            }

            if(ballCourt == true){
                //List ballcourts
            }
            if(outdoorGym == true){
                //List outdoorGyms
            }
            if(runningTracks == true){
                //List runningtracks
            }
            else{
                //List all places
            }

        }
    }
}
