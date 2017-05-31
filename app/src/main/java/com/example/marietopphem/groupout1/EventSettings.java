package com.example.marietopphem.groupout1;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import handlers.HttpHandler;
import handlers.HttpTask;
import models.EveObject;
import models.NewEvent;

/**
 * Created by amaliaskantz on 2017-05-17.
 */

public class EventSettings extends Activity {

    private static final String TAG = EventSettings.class.getSimpleName();

    private SharedPreferences sharedPrefs;
    private EditText nameField;
    private ImageButton calendar;
    private TextView dateF;
    private TextView st;
    private TextView ft;

    private int year_x;
    private int month_x;
    private int day_x;
    private static final int DIALOG_ID = 0;
    private final Calendar c = Calendar.getInstance();
    private int startHour;
    private int startMinute;
    private int finishHour;
    private int finishMinute;
    private Spinner kat;
    private EditText min;
    private EditText max;
    private EditText descField;
    private TextView placeText;
    private Bundle bundle;

    private EveObject changedEvent;

    private boolean findPlace;

    private RadioGroup diffi;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_settings);

        bundle = getIntent().getExtras();

        dateF = (TextView) findViewById(R.id.echoose_date);
        st = (TextView) findViewById(R.id.echoose_starttime);
        ft = (TextView) findViewById(R.id.echoose_end_time);

        descField = (EditText) findViewById(R.id.edescription);

        nameField = (EditText) findViewById(R.id.eevent_name_maker);
        year_x = c.get(Calendar.YEAR);
        month_x = c.get(Calendar.MONTH);
        day_x = c.get(Calendar.DAY_OF_MONTH);
        min = (EditText) findViewById(R.id.emin_capacity);
        max = (EditText) findViewById(R.id.emax_capacity);
        placeText = (TextView) findViewById(R.id.eeventPlaceSettings);
        diffi = (RadioGroup) findViewById(R.id.eradioGroup);
        kat = (Spinner) findViewById(R.id.ecategory_roll_list);
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());

        kat.setPrompt("Kategori");

        if(bundle != null){
            setChangedEvent();
            setDifficulty();
            setCategory();
            showDialogOnCalendarClick();

        }else{
            throw new NullPointerException("Oncreate -Why dont I have NO bundle...? I want BUNDLE");
        }
        /*
        kat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });*/
    }

    private void setCategory() {
        switch(changedEvent.getCategory()){
            case "Styrketräning":
                kat.setSelection(0);
                break;
            case "Kondition":
                kat.setSelection(1);
                break;
            case "Rörelse och dans":
                kat.setSelection(2);
                break;
            case "Bollsport":
                kat.setSelection(3);
                break;
            default:
                kat.setSelection(4);
                break;
        }
    }

    private void setDifficulty() {
        //RadioGroup difficultySettings = (RadioGroup) findViewById(R.id.eradioGroup);
        RadioButton r1 = (RadioButton) findViewById(R.id.elevel1);
        RadioButton r2 = (RadioButton) findViewById(R.id.elevel2);
        RadioButton r3 = (RadioButton) findViewById(R.id.elevel3);
        RadioButton r4 = (RadioButton) findViewById(R.id.elevel4);
        RadioButton r5 = (RadioButton) findViewById(R.id.elevel5);

        Log.d(TAG, changedEvent.getDifficulty());

        switch(Integer.parseInt(changedEvent.getDifficulty())){
            case 1:
                r1.setChecked(true);
                break;
            case 2:
                r2.setChecked(true);
                break;
            case 3:
                r3.setChecked(true);
                break;
            case 4:
                r4.setChecked(true);
                break;
            case 5:
                r5.setChecked(true);
                break;
            default:
                throw new IllegalArgumentException("EventSetings- Why are you giving me difficulties that dont exist?");
        }
    }

    private void setChangedEvent(){
        String description = bundle.getString("Description");
        String endTime = bundle.getString("EndTime");
        String startTime = bundle.getString("StartTime");
        String eventName = bundle.getString("EventName");
        String place = bundle.getString("Place");
        int difficulty = bundle.getInt("Difficulty");
        int minCapacity = bundle.getInt("MinCapacity");
        int maxCapacity = bundle.getInt("MaxCapacity");
        String date = bundle.getString("Date");
        String category = bundle.getString("Category");
        String placeId = bundle.getString("EvePlaceID");
        int id = bundle.getInt("EveEventID");
        int leaderId = bundle.getInt("EveLeaderID");
        boolean participating = false;

        changedEvent = new EveObject(eventName, category, description, placeId, date, startTime,
                endTime, true, id, leaderId, minCapacity, maxCapacity, 0, difficulty, participating, place);

        descField.setText(changedEvent.getDescription(), TextView.BufferType.EDITABLE);
        ft.setText(changedEvent.getEndTime());
        st.setText(changedEvent.getStartTime());
        nameField.setText(changedEvent.getName(), TextView.BufferType.EDITABLE);
        placeText.setText(changedEvent.getPlaceName());
        min.setText(""+changedEvent.getMinCapacity(),TextView.BufferType.EDITABLE);
        max.setText(""+changedEvent.getMaxCapacity(),TextView.BufferType.EDITABLE);
        dateF.setText(changedEvent.getEventDate());
    }

    public void showDialogOnCalendarClick(){
        calendar = (ImageButton) findViewById(R.id.ecalendar);

        calendar.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        showDialog(DIALOG_ID);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListner, year_x, month_x, day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear;
            day_x = dayOfMonth;
            dateF.setText(day_x + "/" + (month_x+1) + "-" + year_x);

        }
    };

    public void setStartTime(View view){
        startHour = c.get(Calendar.HOUR);
        startMinute = c.get(Calendar.MINUTE);

        TimePickerDialog startTimePickerDialog = new TimePickerDialog(EventSettings.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                if(hourOfDay<10){
                    if(minute<10){
                        st.setText("0" + hourOfDay + ":" + "0" + minute);
                    }else{
                        st.setText("0" + hourOfDay + ":" + minute);
                    }
                }else{
                    if(minute<10){
                        st.setText(hourOfDay + ":" + "0" + minute);
                    }else{
                        st.setText(hourOfDay + ":" + minute);
                    }
                }

            }
        }, startHour, startMinute, false);
        startTimePickerDialog.show();
    }

    public void setFinishTime(View view){
        finishHour = c.get(Calendar.HOUR);
        finishMinute = c.get(Calendar.MINUTE);

        TimePickerDialog finishTimePickerDialog = new TimePickerDialog(EventSettings.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                if(hourOfDay<10){
                    if(minute<10){
                        ft.setText("0" + hourOfDay + ":" + "0" + minute);
                    }else{
                        ft.setText("0" + hourOfDay + ":" + minute);
                    }
                }else{
                    if(minute<10){
                        ft.setText(hourOfDay + ":" + "0" + minute);
                    }else{
                        ft.setText(hourOfDay + ":" + minute);
                    }
                }

            }
        }, finishHour, finishMinute, false);
        finishTimePickerDialog.show();
    }

    public void goHome(View view){
        if (view.getId()== R.id.home){
            Intent i = new Intent(EventSettings.this, Home.class);
            startActivity(i);
        }
    }

    public void pickPlace(View view){
        if (view.getId()== R.id.efindPlaceButton){
            findPlace = true;
            Intent i = new Intent(EventSettings.this, PlaceFinder.class);
            startActivity(i);
        }
    }

    @Override
    public void onResume(){
        super.onResume();

    }

    public void changeEvent(View view){
        if (view.getId()== R.id.echange_activity){

            boolean check = checkValues();
            Log.d(TAG, "Check: " + check);

            if(check)
            {
                NewEvent e = createNewEvent();
                String json = e.toJsonString();
                Log.d(TAG, "json " + json);
                String token = sharedPrefs.getString("Token","FAIL");
                Log.d(TAG,token);

                try {
                    String httpResponse = new HttpTask().execute("put", HttpHandler.newEvent(json+ "/" + token)).get();
                    Log.d(TAG, "httpResponse " + httpResponse);
                    finish();
                } catch (InterruptedException e1) {
                    Log.d(TAG, "InterruptedException " + e1.getMessage());
                } catch (ExecutionException e1) {
                    Log.d(TAG, "ExecutionException " + e1.getMessage());
                }
            }
        }
    }


    private NewEvent createNewEvent() {
        NewEvent e = new NewEvent();
        e.setName(nameField.getText().toString());
        e.setPlaceId(sharedPrefs.getString("finderId","FAIL"));
        e.setEventDate(new Date(year_x - 1900, month_x, day_x));
        int[] tempStart = parseTime(st.getText().toString());
        e.setStartTime(new Time(tempStart[0],tempStart[1],0));
        int[] tempEnd = parseTime(ft.getText().toString());
        e.setEndTime(new Time(tempEnd[0],tempEnd[1],0));
        e.setCategory((String)kat.getSelectedItem());
        e.setMinCapacity(Integer.parseInt(min.getText().toString()));
        e.setMaxCapacity(Integer.parseInt(max.getText().toString()));
        String s = ((RadioButton) findViewById(diffi.getCheckedRadioButtonId() )).getText().toString();
        e.setDifficulty(Integer.parseInt(s));
        e.setDescription(descField.getText().toString());

        return e;
    }

    private boolean checkValues(){

        return checkNameField() && checkDate() && checkMinAndMax() && checkDescription() && checkPlace();
    }

    private boolean checkNameField(){
        Log.d(TAG,nameField.getText().toString());
        return nameField.getText().toString() != null && nameField.getText().toString() != "";
    }

    private boolean checkDate(){
        Log.d(TAG, year_x + " " + month_x + " " + day_x);

        Calendar cal = Calendar.getInstance();

        int yearDiff = year_x - cal.get(Calendar.YEAR);
        if(yearDiff > 0)
        {
            Log.d(TAG, "year 1 true " + yearDiff);
            return true;
        }
        else if(yearDiff < 0)
        {
            Log.d(TAG, "year 2 false " + yearDiff);
            return false;
        }

        int monthDiff = month_x - cal.get(Calendar.MONTH);
        if(monthDiff > 0)
        {
            Log.d(TAG, "month 1 true " + monthDiff);
            return true;
        }
        else if(monthDiff < 0)
        {
            Log.d(TAG, "month 2 false " + monthDiff);
            return false;
        }

        int dayDiff = day_x - cal.get(Calendar.DAY_OF_MONTH);
        if(dayDiff < 0)
        {
            Log.d(TAG, "day 1 false " + dayDiff);
            return false;
        }

        if(dayDiff == 0)
        {
            return checkStartTime() && checkFinishTime();
        }

        Log.d(TAG, "day 2 true " + dayDiff);
        return true;
    }

    private boolean checkStartTime(){
        if(st == null)
        {
            Log.d(TAG, "st is null");
            return false;
        }

        int[] time = parseTime(st.getText().toString());
        Log.d(TAG, time[0] + " " + time[1]);

        Calendar cal = Calendar.getInstance();

        int hourDiff = time[0] - cal.get(Calendar.HOUR_OF_DAY);
        if(hourDiff < 0)
        {
            Log.d(TAG, "st 1 false " + hourDiff);
            return false;
        }
        else if(hourDiff > 0)
        {
            Log.d(TAG, "st 2 true " + hourDiff);
            return true;
        }

        int minuteDiff = time[1] - cal.get(Calendar.MINUTE);
        if(minuteDiff < 0)
        {
            Log.d(TAG, "st 3 false " + hourDiff);
            return false;
        }

        Log.d(TAG, "st 4 true " + hourDiff);
        return true;
    }

    private boolean checkFinishTime(){
        if(ft == null)
        {
            Log.d(TAG, "ft is null");
            return false;
        }
        int[] sTime = parseTime(st.getText().toString());
        int[] fTime = parseTime(ft.getText().toString());
        Log.d(TAG, fTime[0] + " " + fTime[1]);

        int hourDiff = fTime[0] - sTime[0];
        if(hourDiff < 0)
        {
            Log.d(TAG, "ft 1 false " + hourDiff);
            return false;
        }
        else if(hourDiff > 0)
        {
            Log.d(TAG, "ft 2 true " + hourDiff);
            return true;
        }

        int minuteDiff = fTime[1] - sTime[1];
        if(minuteDiff < 0)
        {
            Log.d(TAG, "ft 3 false " + minuteDiff);
            return false;
        }

        Log.d(TAG, "ft 4 true " + minuteDiff);
        return true;
    }

    private boolean checkMinAndMax()
    {
        int minInt = Integer.parseInt(min.getText().toString());
        if(minInt > 1)
        {
            if(Integer.parseInt(max.getText().toString()) >= minInt)
            {
                return true;
            }
        }

        return false;
    }

    private boolean checkDescription()
    {
        return descField.getText().toString() != null && !descField.getText().toString().equals("");
    }

    private boolean checkPlace()
    {
        String s = sharedPrefs.getString("finderId","FAIL");
        Log.d(TAG, s);
        return !s.equalsIgnoreCase("fail");
    }

    private int[] parseTime(String time){
        int hour;
        int minute;
        int[] timeAgain = new int[2];

        String[] split = time.split(":");
        hour = Integer.parseInt(split[0]);
        minute = Integer.parseInt(split[1]);

        timeAgain[0] = hour;
        timeAgain[1] = minute;
        return timeAgain;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent(EventSettings.this, Home.class);
                    startActivity(home);
                    return true;
                case R.id.navigation_add:
                    Intent add = new Intent(EventSettings.this, Create.class);
                    startActivity(add);
                    return true;
                case R.id.navigation_search:
                    Intent search = new Intent(EventSettings.this, Search.class);
                    startActivity(search);
                    return true;
                case R.id.navigation_settings:
                    Intent settings = new Intent(EventSettings.this, AppSettings.class);
                    startActivity(settings);
                    return true;
            }
            return false;
        }

    };


}

