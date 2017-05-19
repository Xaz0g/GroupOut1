package com.example.marietopphem.groupout1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Create extends AppCompatActivity {

    private static final String TAG = Create.class.getSimpleName();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        nameField = (EditText) findViewById(R.id.event_name_maker);
        year_x = c.get(Calendar.YEAR);
        month_x = c.get(Calendar.MONTH);
        day_x = c.get(Calendar.DAY_OF_MONTH);

        showDialogOnCalendarClick();

        Spinner kat = (Spinner) findViewById(R.id.category_roll_list);
        kat.setPrompt("Kategori");

        kat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
    }

    public void showDialogOnCalendarClick(){
        calendar = (ImageButton) findViewById(R.id.calendar);

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
            df = (TextView) findViewById(R.id.choose_date);
            df.setText(day_x + "/" + (month_x+1) + "-" + year_x);

        }
    };

    public void setStartTime(View view){
        startHour = c.get(Calendar.HOUR);
        startMinute = c.get(Calendar.MINUTE);

        TimePickerDialog startTimePickerDialog = new TimePickerDialog(Create.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                st = (TextView) findViewById(R.id.choose_starttime);
                st.setText(hourOfDay + ":" + minute);

            }
        }, startHour, startMinute, true);
        startTimePickerDialog.show();
    }

    public void setFinishTime(View view){
        finishHour = c.get(Calendar.HOUR);
        finishMinute = c.get(Calendar.MINUTE);

        TimePickerDialog finishTimePickerDialog = new TimePickerDialog(Create.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                ft = (TextView) findViewById(R.id.choose_end_time);
                ft.setText(hourOfDay + ":" + minute);

            }
        }, finishHour, finishMinute, true);
        finishTimePickerDialog.show();
    }

    public void goHome(View view){
        if (view.getId()== R.id.home){
            Intent i = new Intent(Create.this, Home.class);
            startActivity(i);
        }
    }

    public void pickPlace(View view){
        if (view.getId()== R.id.findPlaceButton){
            Intent i = new Intent(Create.this, PlaceFinder.class);
            startActivity(i);
        }
    }

    public void createEvent(View view){
        if (view.getId()== R.id.create_activity){

            checkValues();
            //ImageButton calendar;
            //TextView df;
            //TextView st;
            //TextView ft;
            //int year_x;
            //int month_x;
            //int day_x;
            //static final int DIALOG_ID = 0;
            //final Calendar c = Calendar.getInstance();
        }
    }

    private boolean checkValues(){

        return checkNameField() && checkDate() && checkStartTime() && checkFinishTime();
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
}
