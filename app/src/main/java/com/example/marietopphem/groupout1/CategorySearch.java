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
        import android.widget.TextView;

        import java.util.concurrent.ExecutionException;

        import handlers.HttpHandler;
        import handlers.HttpTask;

public class CategorySearch extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.t2category, container, false);

        return rootView;
    }
    //OBS! denna metod aktiveras inte och kan därför inte kolla om den är korrekt
    public void searchForCategory(View v){
        if(v.getId() == R.id.searchButton){
            EditText s = (EditText) getView().findViewById(R.id.categorySearchTextField);
            String searchPattern = s.getText().toString();

            CheckBox cardioCheck = (CheckBox) getView().findViewById(R.id.checkCardio);
            CheckBox mobilityCheck = (CheckBox) getView().findViewById(R.id.checkMobility);
            CheckBox strengthTrainingCheck = (CheckBox) getView().findViewById(R.id.checkStrengthTraining);
            CheckBox ballSportsCheck = (CheckBox) getView().findViewById(R.id.checkBallSports);

            boolean cardio = cardioCheck.isChecked();
            boolean mobility = mobilityCheck.isChecked();
            boolean strengthTraining = strengthTrainingCheck.isChecked();
            boolean ballSports = ballSportsCheck.isChecked();

            String categoryString = "";
            int cats = 0;
            if(cardio)
            {
                categoryString += "Kondition";
                cats++;
            }

            if(mobility)
            {
                if(cardio)
                {
                    categoryString += "-";
                }

                categoryString += "Rörlighet+och+dans";
                cats++;
            }

            if(strengthTraining)
            {
                if(cardio || mobility)
                {
                    categoryString += "-";
                }

                categoryString += "Styrketräning";
                cats++;
            }

            if (ballSports)
            {
                if(cardio || mobility || strengthTraining)
                {
                    categoryString += "-";
                }

                categoryString += "Bollsport";
                cats++;
            }

            String request = (cats > 0 && cats <= 4) ? HttpHandler.eventSearch(searchPattern, categoryString) :
                    HttpHandler.eventSearch(searchPattern, "All");

            try {
                String response = new HttpTask().execute("get", request).get();

            } catch (InterruptedException e) {
                Log.e("CATSEA", e.getMessage());
            } catch (ExecutionException e) {
                Log.e("CATSEA", e.getMessage());
            }
        }
    }
}
