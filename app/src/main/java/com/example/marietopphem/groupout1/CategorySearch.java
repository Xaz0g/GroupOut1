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
        import android.widget.TextView;

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
            String searchCategory = s.getText().toString();

            CheckBox cardioCheck = (CheckBox) getView().findViewById(R.id.checkCardio);
            CheckBox mobilityCheck = (CheckBox) getView().findViewById(R.id.checkMobility);
            CheckBox strengthTrainingCheck = (CheckBox) getView().findViewById(R.id.checkStrengthTraining);
            CheckBox ballSportsCheck = (CheckBox) getView().findViewById(R.id.checkBallSports);

            boolean cardio;
            if (cardioCheck.isChecked()){
                cardio = true;
            }else{
                cardio = false;
            }
            boolean mobility;
            if (mobilityCheck.isChecked()){
                mobility = true;
            }else{
                mobility = false;
            }
            boolean strengthTraining;
            if (strengthTrainingCheck.isChecked()){
                strengthTraining = true;
            }else{
                strengthTraining = false;
            }
            boolean ballSports;
            if (ballSportsCheck.isChecked()){
                ballSports  = true;
            }else{
                ballSports = false;
            }

            if (cardio == true){
                //List cardio-activities
            }
            if (mobility == true){
                //List mobility-activities
            }
            if (strengthTraining == true){
                //List strengthTraining-activities
            }
            if (ballSports == true){
                //List ballsport-activities
            }else{
                //List all activities
            }
        }
    }
}
