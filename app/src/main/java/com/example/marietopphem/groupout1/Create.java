package com.example.marietopphem.groupout1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Date;
import java.sql.Time;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Create extends AppCompatActivity {

    ImageButton calendar;
    TextView df;
    TextView st;
    TextView ft;
    int year_x;
    int month_x;
    int day_x;
    static final int DIALOG_ID = 0;
    final Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_bar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        year_x = c.get(Calendar.YEAR);
        month_x = c.get(Calendar.MONTH);
        day_x = c.get(Calendar.DAY_OF_MONTH);
        showDialogOnCalendarClick();


        Spinner p = (Spinner) findViewById(R.id.placeRollList);
        Spinner kat = (Spinner) findViewById(R.id.categoryRollList);
        p.setPrompt("Plats");
        kat.setPrompt("Kategori");

        p.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

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
            df = (TextView) findViewById(R.id.chooseDate);
            if(month_x < 10) {
                if(day_x < 10){
                    df.setText(year_x + "-" + "0" + (month_x + 1) + "-" + "0" + day_x);
                }else {
                    df.setText(year_x + "-" + "0" + (month_x + 1) + "-" + day_x);
                }
            }else{
                if(day_x < 10){
                    df.setText(year_x + "-" + (month_x + 1) + "-" + "0" + day_x);
                }else {
                    df.setText(year_x + "-" + (month_x + 1) + "-" + day_x);
                }
            }

        }
    };

    public void setStartTime(View view){
        final int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog startTimePickerDialog = new TimePickerDialog(Create.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                st = (TextView) findViewById(R.id.choose_starttime);
                int hour_x = hourOfDay;
                int minute_x = minute;
                if (minute < 10){
                    st.setText(hour_x + ":" + "0" + minute_x);
                }else {
                    st.setText(hour_x + ":" + minute_x);
                }
            }
        }, hour, minute, true);
        startTimePickerDialog.show();

    }

    public void setFinishTime(View view){
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog finishTimePickerDialog = new TimePickerDialog(Create.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                ft = (TextView) findViewById(R.id.choose_end_time);
                int hour_x = hourOfDay;
                int minute_x = minute;
                if (minute < 10){
                    ft.setText(hour_x + ":" +"0" + minute_x);
                }else {
                    ft.setText(hour_x+ ":" + minute_x);
                }


            }
        }, hour, minute, true);
        finishTimePickerDialog.show();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent(Create.this, Home.class);
                    startActivity(home);
                    return true;
                case R.id.navigation_add:
                    Intent add = new Intent(Create.this, Create.class);
                    startActivity(add);
                    return true;
                case R.id.navigation_search:
                    Intent search = new Intent(Create.this, Search.class);
                    startActivity(search);
                    return true;
                case R.id.navigation_settings:
                    Intent settings = new Intent(Create.this, Settings.class);
                    startActivity(settings);
                    return true;
            }
            return false;
        }

    };

     public void createActivity(View v) throws ParseException {
         if(v.getId()==R.id.createActivity) {
             df = (TextView) findViewById(R.id.chooseDate);
             String da = df.getText().toString();
             String expectedPattern = "yyyy-MM-dd";
             SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
             Date utilDate = formatter.parse(da);
             java.sql.Date date = convertUtilToSql(utilDate);

             st = (TextView) findViewById(R.id.choose_starttime);
             String start = st.getText().toString();
             String[] startSeparate = start.split(":");
             String sHour = startSeparate[0];
             String sMinute = startSeparate[1];
             Time startTime = new Time(Integer.valueOf(sHour), Integer.valueOf(sMinute), 0);

             ft = (TextView) findViewById(R.id.choose_end_time);
             String end = ft.getText().toString();
             String[] endSeparate = end.split(":");
             String eHour = endSeparate[0];
             String eMinute = endSeparate[1];
             Time endTime = new Time(Integer.valueOf(eHour), Integer.valueOf(eMinute), 0);


             EditText event = (EditText) findViewById(R.id.eventName);
             Spinner placelist = (Spinner) findViewById(R.id.placeRollList);
             Spinner categorylist = (Spinner) findViewById(R.id.categoryRollList);
             EditText mi = (EditText) findViewById(R.id.minCapacity);
             EditText ma = (EditText) findViewById(R.id.maxCapacity);

             RadioButton l1 = (RadioButton) findViewById(R.id.level1);
             RadioButton l2 = (RadioButton) findViewById(R.id.level2);
             RadioButton l3 = (RadioButton) findViewById(R.id.level3);
             RadioButton l4 = (RadioButton) findViewById(R.id.level4);
             RadioButton l5 = (RadioButton) findViewById(R.id.level5);

             //enum difficulty? enum vill va class så hur skriver vi den?
             String difficulty;

             EditText descrip = (EditText) findViewById(R.id.description);

             String eventName = event.getText().toString();
             String placeName = placelist.getSelectedItem().toString();
             String category = categorylist.getSelectedItem().toString();

             String min = mi.getText().toString();
             String max = ma.getText().toString();
             int minCapacity = Integer.parseInt(min);
             int maxCapacity= Integer.parseInt(max);

             if (l1.isChecked()){
                 difficulty = "1";
             }
             if (l2.isChecked()){
                 difficulty = "2";
             }
             if (l3.isChecked()){
                 difficulty = "3";
             }
             if (l4.isChecked()){
                 difficulty = "4";
             }
             if (l5.isChecked()){
                 difficulty = "5";
             }else{
                 System.out.println("Error: Har inte valt nivå!");
                 //Popup om error (tillfälligt utgår från mitten nivå)
                 difficulty = "3";
             }
             String description = descrip.getText().toString();
             System.out.println(eventName +" "+ placeName +" "+ date +" "+ startTime +" "+ endTime +" "+ category +" "+ minCapacity+" "+ maxCapacity+" "+ difficulty +" "+ description);
         }
         Intent i = new Intent(Create.this, Home.class);
         startActivity(i);
     }

    private static java.sql.Date convertUtilToSql(java.util.Date utilDate) {
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        return date;
    }

}
