package com.example.marietopphem.groupout1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
            if (view.getId()==R.id.createAccountSecondary){
                EditText un = (EditText) findViewById(R.id.username);
                EditText em = (EditText) findViewById(R.id.email);
                EditText pa1 = (EditText) findViewById(R.id.password1);
                EditText pa2 = (EditText) findViewById(R.id.password2);

                String name = un.toString();
                String email = em.toString();
                String password = pa1.toString();
                String passwordConfirm = pa2.toString();

                System.out.println(name + " " + email + " " + password + " " + passwordConfirm);
                if (password.equals(passwordConfirm)) {
                    Intent i = new Intent(Register.this, Home.class);
                    startActivity(i);
                }else{
                    //Popup om att lösenorden inte överrensstämmer?
                    System.out.println("Not consistent password");
                }
            }
        }

        public void showPassword(View view){
            if (view.getId()==R.id.showPassword){
                EditText un = (EditText) findViewById(R.id.username);
                EditText em = (EditText) findViewById(R.id.email);
                EditText pa1 = (EditText) findViewById(R.id.password1);
                EditText pa2 = (EditText) findViewById(R.id.password2);

                String name = un.toString();
                String email = em.toString();
                String password = pa1.toString();
                String passwordConfirm = pa2.toString();

                //Ska byta till vy där lösenord syns
                Intent i = new Intent();
                startActivity(i);
            }
        }

    }





