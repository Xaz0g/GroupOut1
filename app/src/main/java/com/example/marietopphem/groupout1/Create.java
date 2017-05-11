package com.example.marietopphem.groupout1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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

        year_x = c.get(Calendar.YEAR);
        month_x = c.get(Calendar.MONTH);
        day_x = c.get(Calendar.DAY_OF_MONTH);
        showDialogOnCalendarClick();


        Spinner p = (Spinner) findViewById(R.id.platsSpinner);
        Spinner kat = (Spinner) findViewById(R.id.kategoriSpinner);
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
            df = (TextView) findViewById(R.id.datefield);
            df.setText(day_x + "/" + (month_x+1) + "-" + year_x);

        }
    };

    public void setStartTime(View view){
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog startTimePickerDialog = new TimePickerDialog(Create.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                st = (TextView) findViewById(R.id.startTfield);
                st.setText(hourOfDay + ":" + minute);

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
                ft = (TextView) findViewById(R.id.finishTfield);
                ft.setText(hourOfDay + ":" + minute);

            }
        }, hour, minute, true);
        finishTimePickerDialog.show();
    }
}
