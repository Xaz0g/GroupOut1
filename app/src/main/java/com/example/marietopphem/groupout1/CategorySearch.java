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
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;

        import org.json.JSONArray;
        import org.json.JSONException;

        import java.util.ArrayList;
        import java.util.concurrent.ExecutionException;

        import handlers.EventActivity;
        import handlers.HttpHandler;
        import handlers.HttpTask;
        import handlers.PlaceEventAdapter;
        import models.EveObject;

public class CategorySearch extends Fragment{

    private ArrayList<EveObject> eventList;
    private PlaceEventAdapter placeEventAdapter;
    private SharedPreferences sharedPrefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.t2category, container, false);

        this.sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());

        eventList = new ArrayList<>();

        placeEventAdapter = new PlaceEventAdapter(getActivity().getApplicationContext(), eventList);
        placeEventAdapter.setToken(sharedPrefs.getString("Token", "FAIL"));
        placeEventAdapter.setUserId(Integer.parseInt(sharedPrefs.getString("userId", "FAIL")));

        ListView lv = (ListView) rootView.findViewById(R.id.eventlistcat);
        lv.setAdapter(placeEventAdapter);



        return rootView;
    }
    //OBS! denna metod aktiveras inte och kan därför inte kolla om den är korrekt
    public void searchEvent() {

        EditText s = (EditText) getView().findViewById(R.id.categorySearchTextField);
        String searchPattern = s.getText().toString();
        searchPattern = (searchPattern == null || searchPattern.equals("")) ? "*" : searchPattern;
        Log.d("CASEA", searchPattern);

        CheckBox cardioCheck = (CheckBox) getView().findViewById(R.id.checkCardio);
        CheckBox mobilityCheck = (CheckBox) getView().findViewById(R.id.checkMobility);
        CheckBox strengthTrainingCheck = (CheckBox) getView().findViewById(R.id.checkStrengthTraining);
        CheckBox ballSportsCheck = (CheckBox) getView().findViewById(R.id.checkBallSports);
        CheckBox miscellaneousCheck = (CheckBox) getView().findViewById(R.id.miscellaneous);

        boolean cardio = cardioCheck.isChecked();
        boolean mobility = mobilityCheck.isChecked();
        boolean strengthTraining = strengthTrainingCheck.isChecked();
        boolean ballSports = ballSportsCheck.isChecked();
        boolean miscellaneous = miscellaneousCheck.isChecked();

        String categoryString = "";
        int cats = 0;
        if (cardio) {
            categoryString += "Kondition";
            cats++;
        }

        if (mobility) {
            if (cardio) {
                categoryString += "-";
            }

            categoryString += "Rörlighet+och+dans";
            cats++;
        }

        if (strengthTraining) {
            if (cardio || mobility) {
                categoryString += "-";
            }

            categoryString += "Styrketräning";
            cats++;
        }

        if (ballSports) {
            if (cardio || mobility || strengthTraining) {
                categoryString += "-";
            }

            categoryString += "Bollsport";
            cats++;
        }

        if(miscellaneous){
            if (cardio || mobility || strengthTraining || ballSports) {
                categoryString += "-";
            }
            categoryString += "Övrigt";
            cats++;
        }

        String token = sharedPrefs.getString("Token", "FAIL");
        String request = (cats > 0 && cats <= 4) ? HttpHandler.eventSearch(searchPattern, categoryString, token) :
                HttpHandler.eventSearch(searchPattern, "All", token);

        Log.d("CASEA", request);

        getEvents(request);


    }

    private void getEvents(String request){
        try {
            String response = new HttpTask().execute("get", request).get();

            Log.d("CASEA", response);

            ArrayList<EveObject> temp = EventActivity.parseJSONv2(response);
            eventList.clear();
            eventList.addAll(temp);
            placeEventAdapter.notifyDataSetChanged();

        } catch (InterruptedException e) {
            Log.e("CATSEA", e.getMessage());
        } catch (ExecutionException e) {
            Log.e("CATSEA", e.getMessage());
        } catch (JSONException e) {
            Log.e("CATSEA", e.getMessage());
        }
    }

}
