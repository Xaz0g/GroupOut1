package com.example.marietopphem.groupout1;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import handlers.HttpHandler;
import handlers.HttpTask;
import models.NewUser;

/**
 * Created by marietopphem on 2017-05-05.
 */

public class Register extends Activity {

    private final String TAG = RegisterNewUserTask.class.getSimpleName();

    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
    private EditText password2Field;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        nameField = (EditText)findViewById(R.id.firstName);
        emailField = (EditText)findViewById(R.id.email);
        passwordField = (EditText)findViewById(R.id.password1);
        password2Field = (EditText)findViewById(R.id.password2);
    }

    public void createAccount(View view) {

        if (view.getId() == R.id.create_account) {
            if(checkValues()){
                try {
                    String salt = new HttpTask().execute("get","https://testpvt.herokuapp.com/user/newUser/getSalt").get();
                    Log.d("Register salt",salt);
                } catch (InterruptedException e) {
                    Log.d("Register",e.getMessage());
                } catch (ExecutionException e) {
                    Log.d("Register",e.getMessage());
                }
                String json = createNewUser();
                //Log.d("Register",json);
                //new RegisterNewUserTask().execute(json);

                Intent i = new Intent(Register.this, Home.class);
                startActivity(i);
            }
        }
    }

    private String createNewUser(){
        NewUser user = new NewUser();

        user.setUserName(nameField.getText().toString());
        user.setEmail(emailField.getText().toString());
        user.setPasswordHash(passwordField.getText().toString());
        user.setSalt("burlesque");

        return user.toJsonString();
    }

    private void checkNewUser(String response) {
        Log.d(TAG, response);

        if(response.equalsIgnoreCase("Bad request, error code: 400"))
        {
            
        }
        else
        {

        }
    }

    private boolean checkValues(){
        return chekNameField() && chekEmailField() && chekPasswordField() && chekPassword2FieldField();
    }

    private boolean chekNameField()
    {
        return nameField.getText().toString() != "";
    }

    private boolean chekEmailField()
    {
        return emailField.getText().toString() != "";
    }

    private boolean chekPasswordField()
    {
        return passwordField.getText().toString() != "";
    }

    private boolean chekPassword2FieldField()
    {
        return password2Field.getText().toString() != "";
    }

    private class RegisterNewUserTask extends AsyncTask<String, Integer, String> {

        private final String TAG = RegisterNewUserTask.class.getSimpleName();

        @Override
        protected String doInBackground(String... params) {

            URL url = null;
            try {
                url = new URL(HttpHandler.newUser(params[0]));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("PUT");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.connect();

                int code = connection.getResponseCode();
                if (code == 200) {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String content = "", line;
                    while ((line = rd.readLine()) != null) {
                        content += line + "\n";
                    }
                    return content;
                } else {
                    return "Bad request, error code: " + code;
                }

            } catch (MalformedURLException e) {
                return "MalformedURL error: " + e.getMessage();
            } catch (ProtocolException e) {
                return "Protocol error: " + e.getMessage();
            } catch (IOException e) {
                return "IO error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            checkNewUser(result);
        }
    }
}





