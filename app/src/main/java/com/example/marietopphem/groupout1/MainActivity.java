package com.example.marietopphem.groupout1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.io.IOException;

import handlers.GetUrlContentTask;
import handlers.HttpHandler;

/**
 * Created by Xaz0g on 2017-05-12.
 */

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();
    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GetUrlContentTask().execute("https://testpvt.herokuapp.com/participation/cancelParticipation/p042o4nb2oi1fch9kp6k2a6qqe/10");

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login);
        loginButton = (LoginButton)findViewById(R.id.fb_login_button);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    public void logIn(View v) {
        if(v.getId()==R.id.login_button){
            Intent i = new Intent(MainActivity.this, Home.class);
            startActivity(i);
        }
    }

    public void newAccount(View v){
        if (v.getId()==R.id.create_account){
            Intent i = new Intent(MainActivity.this, Register.class);
            startActivity(i);
        }
    }

    public void fbLogIn(View v){
        if (v.getId()==R.id.fb_login_button){
            Intent i = new Intent(MainActivity.this, Home.class);
            startActivity(i);
        }
    }
}
