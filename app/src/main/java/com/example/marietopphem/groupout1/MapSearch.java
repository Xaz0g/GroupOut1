package com.example.marietopphem.groupout1;

/**
 * Created by marietopphem on 2017-05-12.
 */


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
        import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.attr.fragment;
import static com.example.marietopphem.groupout1.R.id.map_google;
import static com.example.marietopphem.groupout1.R.layout.activity_maps;

public class MapSearch extends Fragment implements OnMapReadyCallback{

    private String TAG = "debug";

    GoogleMap mGoogleMap;
    MapView mMapView;
    View view;

    ArrayList<PositionObject> javaPositions = new ArrayList<>();

    double latitudeInput;
    double longitudeInput;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(TAG, "hehehe111111");
        View rootView = inflater.inflate(activity_maps, container, false);

         Log.v(TAG, "hehehe111111");

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

    }

    private String JSON_TEST_DATA =
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
            + " \"Name\" : \"Hornsbergs strands utegym\" " + "  },"
            + "    {" + "  \"GeographicalPosition\" : {" + "    \"X\" : \"59.33234621581338\","
            + "    \"Y\" : \"18.036367921017284\"" + "  },"
            + " \"Id\" : \"4e635253-5611-4421-b86b-671a17d89f5f\","
            + " \"Name\" : \"Kronobergsparkens utegym\" " + "  },"
            + "{" + "  \"GeographicalPosition\" : {" + "    \"X\" : \"59.3258078912206\","
            + "    \"Y\" : \"18.02116198619644\"" + "  },"
            + " \"Id\" : \"02a9113d-b542-40fc-a6fa-a0f3a636adff\","
            + " \"Name\" : \"Smedsuddsbadets utegym\" " + "  } , "





            + "{" + "  \"GeographicalPosition\" : {" + "    \"X\" : \"59.357626218928615\","
            + "    \"Y\" : \"18.09991851664943\"" + "  },"
            + " \"Id\" : \"55a72c6f-ed00-472a-bf0f-cfa0a6a8fc44\"," + " \"Name\" : \"Hjorthagens utegym\" "
            + "  },"
            + "{" + "  \"GeographicalPosition\" : {" + "    \"X\" : \"59.329230551407775\","
            + "    \"Y\" : \"18.02530357000892\"" + "  },"
            + " \"Id\" : \"f3880539-4c02-47bf-855c-dd79f11d4404\","
            + " \"Name\" : \"Rålambshovsparkens utegym\" " + "  },"
            + "    {" + "  \"GeographicalPosition\" : {" + "    \"X\" : \"59.34630803139627\","
            + "    \"Y\" : \"18.053722739958612\"" + "  },"
            + " \"Id\" : \"3c1ad3a1-5227-410e-8d4b-e80f0b64480f\","
            + " \"Name\" : \"Vanadislundens utegym\" " + "  },"
            + "{" + "  \"GeographicalPosition\" : {" + "    \"X\" : \"59.344186775120285\","
            + "    \"Y\" : \"18.099205258013004\"" + "  },"
            + " \"Id\" : \"8166652f-f79e-4fe7-a757-d0e6485b7bf9\","
            + " \"Name\" : \"Uteträffen i Tessinparken, utegym för seniorer\" " + "  }"
            + " ]"
    ;


    public void parseJSON() throws JSONException{
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
        Log.v("PrintArrayList", javaPositions.toString());
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