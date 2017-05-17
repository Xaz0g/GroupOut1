package com.example.marietopphem.groupout1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.ExecutionException;

import handlers.HttpHandler;
import handlers.HttpTask;
import handlers.PassworHandler;
import models.NewUser;

/**
 * Created by marietopphem on 2017-05-05.
 */

public class Register extends Activity {


    private final String TAG = Register.class.getSimpleName();

    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
    private EditText password2Field;

    SharedPreferences sharedPrefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        nameField = (EditText)findViewById(R.id.firstName);
        emailField = (EditText)findViewById(R.id.email);
        passwordField = (EditText)findViewById(R.id.password1);
        password2Field = (EditText)findViewById(R.id.password2);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());

    }

    public void createAccount(View view) {

        if (view.getId() == R.id.create_account) {
            if(checkValues()){
                try {
                    String salt = new HttpTask().execute("get",HttpHandler.newUser("getSalt")).get();

                    String json = createNewUser(salt);

                    String httpResponse = new HttpTask().execute("put",HttpHandler.newUser(json)).get();

                    String validation = new HttpTask().execute("get",HttpHandler.checkToken(httpResponse)).get();

                    if(validation.trim().equals("Ok"))
                    {
                        sharedPrefs.edit().putString("Token",httpResponse).apply();
                        Intent i = new Intent(Register.this, Home.class);
                        startActivity(i);
                    }
                    else
                    {
                        Log.e(TAG, "Failed token check: " + validation);
                    }

                } catch (InterruptedException e) {
                    Log.d("Register",e.getMessage());
                } catch (ExecutionException e) {
                    Log.d("Register",e.getMessage());
                }
            }
        }
    }

    private String createNewUser(String salt){
        NewUser user = new NewUser();

        user.setUserName(nameField.getText().toString());
        user.setEmail(emailField.getText().toString());
        try {
            user.setPasswordHash(PassworHandler.hashPassword(passwordField.getText().toString(),salt));
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, e.getMessage());
        } catch (InvalidKeySpecException e) {
            Log.e(TAG, e.getMessage());
        }
        user.setSalt(salt);

        return user.toJsonString();
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
        String email = emailField.getText().toString();
        return email != "" && validateEmail(email);
    }

    private boolean chekPasswordField()
    {
        return passwordField.getText().toString() != "";
    }

    private boolean chekPassword2FieldField()
    {
        String password2 = password2Field.getText().toString();
        return password2 != "" && password2.equals(passwordField.getText().toString());
    }

    private boolean validateEmail(String email)
    {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}