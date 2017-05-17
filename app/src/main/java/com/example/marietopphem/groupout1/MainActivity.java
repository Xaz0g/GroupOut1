package com.example.marietopphem.groupout1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.concurrent.ExecutionException;

import handlers.HttpHandler;
import handlers.HttpTask;

/**
 * Created by Xaz0g on 2017-05-12.
 */

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();
    private LoginButton loginButton;
    private EditText emailField;
    private EditText passwordField;

    CallbackManager callbackManager;
    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());

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

        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.editText6);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    public void logIn(View v) {
        if(v.getId()==R.id.login_button){

            if(checkEmailField() && checkPasswordField())
            {
                try {
                    String httpResponse = new HttpTask().execute("get",HttpHandler.login(loginInfo())).get();
                    String tokenValidation = new HttpTask().execute("get",HttpHandler.checkToken(httpResponse)).get();

                    if(tokenValidation.trim().equals("Ok"))
                    {
                        sharedPrefs.edit().putString("Token",httpResponse).apply();
                        Intent i = new Intent(MainActivity.this, Home.class);
                        startActivity(i);
                    }
                    else
                    {
                        Log.d(TAG,"Failed login: " + httpResponse + " " + tokenValidation);
                    }

                } catch (InterruptedException e) {
                    Log.e(TAG,e.getMessage());
                } catch (ExecutionException e) {
                    Log.e(TAG,e.getMessage());
                }
            }
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

    private String loginInfo()
    {
        return emailField.getText().toString() + "/" + passwordField.getText().toString();
    }

    private boolean checkEmailField()
    {
        return emailField.getText().toString() != "";
    }

    private boolean checkPasswordField()
    {
        return passwordField.getText().toString() != "";
    }
}
