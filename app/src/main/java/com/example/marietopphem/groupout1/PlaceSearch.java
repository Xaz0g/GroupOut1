package com.example.marietopphem.groupout1;

/**
 * Created by marietopphem on 2017-05-12.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import static com.example.marietopphem.groupout1.R.layout.t1place;

public class PlaceSearch extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(t1place, container, false);
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
