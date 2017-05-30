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
import models.NewEvent;

/**
 * Created by amaliaskantz on 2017-05-17.
 */

public class EventSettings extends Activity {

    //hejdhfoikdlsökdjfghfkdslökdfjhbdkljhgfchjkljhgfcdxghjkhg

    private static final String TAG = Create.class.getSimpleName();

    SharedPreferences sharedPrefs;
    EditText nameField;
    ImageButton calendar;
    TextView df;
    TextView st;
    TextView ft;

    int year_x;
    int month_x;
    int day_x;
    static final int DIALOG_ID = 0;
    final Calendar c = Calendar.getInstance();
    int startHour;
    int startMinute;
    int finishHour;
    int finishMinute;
    Spinner kat;
    EditText min;
    EditText max;
    EditText descField;
    TextView placeText;
    Bundle bundle;

    boolean findPlace;

    RadioGroup diffi;

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

        nameField = (EditText) findViewById(R.id.eevent_name_maker);
        year_x = c.get(Calendar.YEAR);
        month_x = c.get(Calendar.MONTH);
        day_x = c.get(Calendar.DAY_OF_MONTH);
        min = (EditText) findViewById(R.id.emin_capacity);
        max = (EditText) findViewById(R.id.emax_capacity);
        descField = (EditText) findViewById(R.id.edescription);
        placeText = (TextView) findViewById(R.id.eeventPlaceSettings);

        diffi = (RadioGroup) findViewById(R.id.eradioGroup);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());

        showDialogOnCalendarClick();

        kat = (Spinner) findViewById(R.id.ecategory_roll_list);
        kat.setPrompt("Kategori");

        kat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        bundle = getIntent().getExtras();

        if(bundle != null)
        {
            placeText.setText(bundle.getString("placeName"));
            sharedPrefs.edit().putString("finderId", bundle.getString("placeId")).apply();
        }
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
            df = (TextView) findViewById(R.id.echoose_date);
            df.setText(day_x + "/" + (month_x+1) + "-" + year_x);

        }
    };

    public void setStartTime(View view){
        startHour = c.get(Calendar.HOUR);
        startMinute = c.get(Calendar.MINUTE);

        TimePickerDialog startTimePickerDialog = new TimePickerDialog(EventSettings.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                st = (TextView) findViewById(R.id.echoose_starttime);
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
                ft = (TextView) findViewById(R.id.echoose_end_time);
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

        if(findPlace)
        {
            String str = sharedPrefs.getString("finderName", "FAIL");

            Log.d(TAG, str);
            if(!str.equalsIgnoreCase("FAIL")){
                placeText.setText(str);
            }

            findPlace = false;
        }
        else
        {
            bundle = getIntent().getExtras();
            if(bundle != null)
            {
                placeText.setText(bundle.getString("placeName"));
                sharedPrefs.edit().putString("finderId", bundle.getString("placeId")).apply();
            }
        }

    }

    public void createEvent(View view){
        if (view.getId()== R.id.ecreate_activity){

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
        e.setPlaceId(sharedPrefs.getString("finderId","FAIL"));       //temp
        e.setEventDate(new Date(year_x - 1900, month_x, day_x));
        int[] tempStart = parseTime(st.getText().toString());
        e.setStartTime(new Time(tempStart[0],tempStart[1],0));
        int[] tempEnd = parseTime(ft.getText().toString());
        e.setEndTime(new Time(tempEnd[0],tempEnd[1],0));
        e.setCategory((String)kat.getSelectedItem());   // is obj
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

