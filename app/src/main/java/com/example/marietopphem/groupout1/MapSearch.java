package com.example.marietopphem.groupout1;

/**
 * Created by marietopphem on 2017-05-12.
 */

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import handlers.HttpHandler;
import handlers.HttpTask;

import static com.example.marietopphem.groupout1.R.layout.activity_maps;

public class MapSearch extends Fragment implements OnMapReadyCallback{

    private String TAG = "Debug ";

    GoogleMap mGoogleMap;
    MapView mMapView;
    View view;
    SharedPreferences refPref;

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
        LatLng stockholm = new LatLng(59.334591, 18.063240);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stockholm, 12.0f));

        Log.v(TAG + "Print pos-Array", javaPositions.toString());
        javaPositions.clear();
        Log.v(TAG + "Print pos-Array", javaPositions.toString());
    }

    private String JSON_TEST_DATA;

    public String getPositions(){
        double x = 59.334591;
        double y = 18.063240;

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
            String name = pos.getName();

            LatLng newMark = new LatLng(x,y);
            mGoogleMap.addMarker(new MarkerOptions().position(newMark).title(name));
        }
    }
}