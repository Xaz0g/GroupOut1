/*package com.example.marietopphem.groupout1;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    GoogleMap mMap;
    ArrayList<PositionObject> javaPositions = new ArrayList<>();

    private LocationManager locationManager;
    private LocationListener locationListener;

    public static String JSON_DATA =
            "[ "
                    + "    {" + "  \"GeographicalPosition\" : {" + "    \"X\" : \"59.333988302200886\","
                    + "    \"Y\" : \"18.04795240339837\"" + "  },"
                    + " \"Id\" : \"35eb5586-74df-447b-b2df-ca055e3e9ec5\","
                    + " \"Name\" : \"Kungsholms strandstigs utegym\" " + "  },"

                    + "{" + "  \"GeographicalPosition\" : {" + "    \"X\" : \"59.37105799218928\","
                    + "    \"Y\" : \"18.058316113699362\"" + "  },"
                    + " \"Id\" : \"9013d8c9-b162-42ed-b256-b9d448961853\","
                    + " \"Name\" : \"Lappkärrsbergets utegym, Docentbacken\" " + "  },"

                    + "{" + "  \"GeographicalPosition\" : {" + "    \"X\" : \"59.339124550592736\","
                    + "    \"Y\" : \"18.040644423563727\"" + "  },"
                    + " \"Id\" : \"fb6ad163-513b-4962-ac3c-17c484ef2bb6\"," + " \"Name\" : \"Vasaparkens utegym\" "
                    + "  },"

                    + "{" + "  \"GeographicalPosition\" : {" + "    \"X\" : \"59.340671511858346\","
                    + "    \"Y\" : \"18.009912671835714\"" + "  },"
                    + " \"Id\" : \"90f749ae-96ab-4d73-a6f2-22c14846e095\","
                    + " \"Name\" : \"Hornsbergs strands utegym\" " + "  }"
                    + " ]"
            ;


    public boolean locationServiceBoolean(Context context) {
        int locationMode = 0;
        String locationProviders;
        boolean logCheckTest = false;

        Log.v("locationServiceBoolean", "first locationServiceBoolean initiated");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            if (locationMode != Settings.Secure.LOCATION_MODE_OFF) {
                logCheckTest = true;
            }
            Log.v("locationServiceBoolean", "second locationServiceBoolean initiated");
            Log.v("locationServiceBoolean", "Hejhej123" + logCheckTest + " " + locationMode);
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            Log.v("locationServiceBoolean", "third locationServiceBoolean initiated");
            Log.v("locationServiceBoolean", "tjotjo456" + logCheckTest + " " + locationProviders + " " + locationMode);
            return !TextUtils.isEmpty(locationProviders);
        }


    }


    public void locationServiceChecker(){
        Log.v("locationServiceChecker", "first locationServiceChecker initiated");
        if(locationServiceBoolean(this)){
            LatLng userPosition = new LatLng(59.911491, 10.757933);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(userPosition));
        }
        else {
            LatLng stockholm = new LatLng(59.334591, 18.063240);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(stockholm));
        }
        Log.v("locationServiceChecker", "second locationServiceChecker initiated");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("onCreate", "first onCreate initiated");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_div);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Log.v("onCreate", "second onCreate initiated");
        methodTesting();

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
   /* @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in every position retreived from arraylist
        for (PositionObject pos : javaPositions) {
            LatLng position = new LatLng(pos.getLatitude(),pos.getLongitude());
            mMap.addMarker(new MarkerOptions().position(position));

        }
    }



    public void parse() throws JSONException {

        final JSONArray positions = new JSONArray(JSON_DATA);



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
    }
}
*/


