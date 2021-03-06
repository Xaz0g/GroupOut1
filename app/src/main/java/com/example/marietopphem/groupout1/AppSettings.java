package com.example.marietopphem.groupout1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import java.util.concurrent.ExecutionException;

import handlers.HttpHandler;
import handlers.HttpTask;

/**
 * Created by Elina on 2017-05-10.
 */

public class AppSettings extends Activity{

    private SharedPreferences sharedPrefs;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(3).setChecked(true);
    }

    public void logout(View view)
    {
        if(view.getId() == R.id.logout)
        {
            String token = sharedPrefs.getString("Token","FAIL");
            String request = HttpHandler.logout(token);
            Log.d("LOGOUT!", request);

            try {
                String response = new HttpTask().execute("PUT", request).get();
                Log.d("LOGOUT!", response);

                if(response.trim().equals("true"))
                {
                    Intent logout = new Intent(AppSettings.this, MainActivity.class);

                    startActivity(logout);
                }

            } catch (InterruptedException e) {
                Log.d("LOGOUT!",e.getMessage());
            } catch (ExecutionException e) {
                Log.d("LOGOUT!",e.getMessage());
            }
        }
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



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent(AppSettings.this, Home.class);
                    startActivity(home);
                    return true;
                case R.id.navigation_add:
                    Intent add = new Intent(AppSettings.this, Create.class);
                    startActivity(add);
                    return true;
                case R.id.navigation_search:
                    Intent search = new Intent(AppSettings.this, Search.class);
                    startActivity(search);
                    return true;
                case R.id.navigation_settings:
                    Intent settings = new Intent(AppSettings.this, AppSettings.class);
                    startActivity(settings);
                    return true;
            }
            return false;
        }

    };

    public void logoutUser (View view){

        Intent logout = new Intent(AppSettings.this, MainActivity.class);

        startActivity(logout);

    }
}

