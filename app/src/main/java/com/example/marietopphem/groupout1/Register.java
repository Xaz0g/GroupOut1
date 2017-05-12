package com.example.marietopphem.groupout1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by marietopphem on 2017-05-05.
 */

public class Register extends  Activity{

        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.register);
        }

        public void createAccount(View view){

            if (view.getId()==R.id.create_account){
                Intent i = new Intent(Register.this, Home.class);
                startActivity(i);
            }
        }

    }





