package com.example.marietopphem.groupout1;

/**
 * Created by marietopphem on 2017-05-12.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import handlers.HttpHandler;
import handlers.HttpTask;

import static com.example.marietopphem.groupout1.R.layout.activity_maps;

public class MapSearch extends Fragment implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {

    private String TAG = "Debug ";

    GoogleMap mGoogleMap;
    MapView mMapView;
    View view;
    SharedPreferences refPref;
    private String JSON_TEST_DATA;

    ArrayList<PositionObject> javaPositions = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        refPref = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());
        JSON_TEST_DATA = getPositions();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(TAG + "crash-checking", "1");

        View rootView = inflater.inflate(activity_maps, container, false);

        Log.v(TAG + "crash-checking", "2");

        return rootView;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) view.findViewById(R.id.map_google);
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        try {
            parseJSON();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        markerIterator(javaPositions);
        LatLng stockholm = new LatLng(59.4031600, 17.9447900);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stockholm, 12.0f));



        Log.v(TAG + "Print pos-Array", javaPositions.toString());
        javaPositions.clear();
        Log.v(TAG + "Print pos-Array", javaPositions.toString());

        mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                final String name = marker.getTitle();
                try {
                    parseJSON();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.v(TAG, "OnInforWindowClick111111");
                String id;
                Intent i = new Intent(getActivity(), PlaceActivity.class);
                i.putExtra("Name", name);
                Log.v(TAG, javaPositions.toString());

                for (PositionObject pos : javaPositions) {
                       if (name.equals(pos.getName().trim())) {
                        id = pos.getId();
                        i.putExtra("Id", id);


                        Log.v(TAG, "OnInfoWindowClick " + id);
                    }
                }

                // Switch view to id's location, activity_place

                Log.v(TAG, "OnInforWindowClick3333333");

                startActivity(i);
            }
        });
    }


    public String getPositions(){
        double x = 59.4031600;
        double y = 17.9447900;

        String token = refPref.getString("Token", "fail404");

        String request = HttpHandler.getNearbyPositions(y,x,"Utegym-Motionsspår-Bollplaner",token);
        Log.d(TAG + "Printing request", request);
        try {
            String response = new HttpTask().execute("get",request).get();
            Log.v(TAG + "printing response", response);
            return response;
        } catch (InterruptedException e) {
            Log.v(TAG + "try/catch 1", e.getMessage());
        } catch (ExecutionException e) {
            Log.v(TAG + "try/catch 2", e.getMessage());
        }
        return null;
    }

    public void parseJSON() throws JSONException{
        Log.d(TAG + "parseJSON-check",JSON_TEST_DATA);
        final JSONArray positions = new JSONArray(JSON_TEST_DATA);

        final int n = positions.length();

        for (int i = 0; i < n; i++) {

            final JSONObject position = positions.getJSONObject(i);

            String name = position.getString("Name");
            String id = position.getString("Id");
            double x = position.getJSONObject("GeographicalPosition").getDouble("X");
            double y = position.getJSONObject("GeographicalPosition").getDouble("Y");

            PositionObject posObject = new PositionObject(name, id, x, y);
            javaPositions.add(posObject);
        }
        Log.v(TAG + "PrintArrayList", javaPositions.toString());
    }

    public void markerIterator(ArrayList<PositionObject> javaPositions){
        for(PositionObject pos: javaPositions){
            double x = pos.getLatitude();
            double y = pos.getLongitude();
            String id = pos.getId();
            String name = pos.getName();


            // Get amount of events at objects location
         //   int numberOfEvents = countEventsAtLocation(id);

//            if(numberOfEvents == -1){
                LatLng newMark = new LatLng(x,y);
                mGoogleMap.addMarker(new MarkerOptions().position(newMark).title(name).snippet("Klicka för att se platsens event")); // This snippet is supposed to calculate the amount of events at a location

  //          }
           // else {
           //     LatLng newMark = new LatLng(x,y);
             //   mGoogleMap.addMarker(new MarkerOptions().position(newMark).title(name).snippet("This location has "+ numberOfEvents +" events"));
//
  //          }
        }
    }

//    public int countEventsAtLocation(String id){
//        int counter = -1;
//        // Make statement to get all events at this location
//
//        String request = HttpHandler.eventCounter(id);
//
//        try {
//            counter = Integer.parseInt(new HttpTask().execute("GET", request).get().trim() );
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        return counter;
//    }


    @Override
    public void onInfoWindowClick(Marker marker) {

    }
}