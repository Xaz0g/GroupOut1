package com.example.marietopphem.groupout1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Elina on 2017-05-10.
 */

public class Settings extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }

    public void save_settings(View v){
        Intent i = new Intent(Settings.this, Home.class);
        startActivity(i);
    }
}

