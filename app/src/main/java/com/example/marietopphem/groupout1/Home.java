package com.example.marietopphem.groupout1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by marietopphem on 2017-05-05.
 */

public class Home extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createNewEvent(View view){
        if (view.getId()== R.id.createActivity){
            Intent i = new Intent(Home.this, Create.class);
            startActivity(i);
        }
    }
}
