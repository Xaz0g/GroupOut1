package com.example.marietopphem.groupout1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

/**
 * Created by Elina on 2017-05-10.
 */

public class AppSettings extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }

    public void saveSettings(View v){
        if(v.getId()== R.id.saveButton) {
            EditText em = (EditText) findViewById(R.id.email);
            EditText na = (EditText) findViewById(R.id.username);
            EditText pa = (EditText) findViewById(R.id.passwordField);
            Switch sw = (Switch) findViewById((R.id.notificationSwitch));

            String email = em.getText().toString();
            String name = na.getText().toString();
            String password = pa.getText().toString();
            boolean notificationSwitch;
            if (sw.isChecked()){
                notificationSwitch = true;
            }else{
                notificationSwitch = false;
            }

            //Send status of email, name, password & notificationSwitch to server then change view to home
            Intent i = new Intent(AppSettings.this, Home.class);
            startActivity(i);
        }
    }
}

