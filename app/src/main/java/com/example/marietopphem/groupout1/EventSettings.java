package com.example.marietopphem.groupout1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by amaliaskantz on 2017-05-17.
 */

public class EventSettings extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_settings);


        String description;
        String endTime;
        String startTime;
        String eventName;
        String place;
        String difficulty;
        String minCapacity;
        String maxCapacity;
        String date;
        String category;
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            description= null;
            endTime = null;
            startTime = null;
            eventName = null;
            place = null;
            difficulty = null;
            minCapacity = null;
            maxCapacity = null;
            date = null;
            category = null;
        } else {
            description = extras.getString("Description");
            endTime = extras.getString("EndTime");
            startTime = extras.getString("StartTime");
            eventName = extras.getString("EventName");
            place = extras.getString("Place");
            difficulty = extras.getString("Difficulty");
            minCapacity = extras.getString("MinCapacity");
            maxCapacity = extras.getString("MaxCapacity");
            date = extras.getString("Date");
            category = extras.getString("Category");
        }

        EditText descriptionSettings = (EditText) findViewById(R.id.edescription);
        descriptionSettings.setText(description, TextView.BufferType.EDITABLE);

        TextView endTimeSettings = (TextView) findViewById(R.id.echoose_end_time);
        endTimeSettings.setText(endTime);

        TextView startTimeSettings = (TextView) findViewById(R.id.echoose_starttime);
        startTimeSettings.setText(startTime);

        EditText eventNameSettings = (EditText) findViewById(R.id.eevent_name_maker);
        eventNameSettings.setText(eventName, TextView.BufferType.EDITABLE);

        TextView placeSettings = (TextView) findViewById(R.id.eeventPlaceSettings);
        placeSettings.setText(place);

        //RadioGroup difficultySettings = (RadioGroup) findViewById(R.id.eradioGroup);
        RadioButton r1 = (RadioButton) findViewById(R.id.elevel1);
        RadioButton r2 = (RadioButton) findViewById(R.id.elevel2);
        RadioButton r3 = (RadioButton) findViewById(R.id.elevel3);
        RadioButton r4 = (RadioButton) findViewById(R.id.elevel4);
        RadioButton r5 = (RadioButton) findViewById(R.id.elevel5);

        Log.d("diff", difficulty + "");

        int diff = Integer.parseInt(difficulty);
       if(diff==1){
            r1.setChecked(true);
        }
        if(diff==2){
            r2.setChecked(true);
        }
        if(diff==3){
            r3.setChecked(true);
        }
        if(diff==4){
            r4.setChecked(true);
        }
        if(diff==5){
            r5.setChecked(true);
        }


        EditText minCapacitySettings = (EditText) findViewById(R.id.emin_capacity);
        minCapacitySettings.setText(minCapacity,TextView.BufferType.EDITABLE);

        EditText maxCapacitySettings = (EditText) findViewById(R.id.emax_capacity);
        maxCapacitySettings.setText(maxCapacity,TextView.BufferType.EDITABLE);

        TextView dateSettings = (TextView) findViewById(R.id.echoose_date);
        dateSettings.setText(date);

        Spinner categorySettings = (Spinner) findViewById(R.id.ecategory_roll_list);

        int cate = -1;
        if(category.equals("Styrketräning")){
            cate = 0;
        }
        if(category.equals("Kondition")){
            cate = 1;
        }
        if(category.equals("Rörelse och dans")){
            cate = 2;
        }
        if(category.equals("Bollsport")){
            cate = 3;
        }
        if(category.equals("Övrigt")){
            cate = 4;
        }
        categorySettings.setSelection(cate);

    }


}

