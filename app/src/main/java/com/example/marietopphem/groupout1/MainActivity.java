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
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.ExecutionException;

import handlers.HttpHandler;
import handlers.HttpTask;
import handlers.PassworHandler;

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
        loginButton = (LoginButton)findViewById(R.id.fbLoginButton);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("public_profile",  "email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    String email = object.getString("email");
                                    String name = object.getString("name");
                                    name = name.replaceAll(" ", "");

                                    String request = HttpHandler.fbUser(name, email);
                                    Log.d("FACEBOOK", request);
                                    String response2 = new HttpTask().execute("GET", request).get();
                                    Log.d("FACEBOOK", response2);
                                    sharedPrefs.edit().putString("Token", response2).apply();

                                    Intent i = new Intent(MainActivity.this, Home.class);
                                    startActivity(i);

                                } catch (JSONException e) {
                                    Log.d("FACEBOOK",e.getMessage());
                                } catch (InterruptedException e) {
                                    Log.d("FACEBOOK",e.getMessage());
                                } catch (ExecutionException e) {
                                    Log.d("FACEBOOK",e.getMessage());
                                }
                                Log.d("FACEBOOK", response.toString());
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    public void logIn(View v) {
        if(v.getId()==R.id.loginButton){

            if(checkEmailField() && checkPasswordField())
            {
                try {
                    String httpResponse = new HttpTask().execute("get",HttpHandler.login(loginInfo())).get();
                    String tokenValidation = new HttpTask().execute("get",HttpHandler.checkToken(httpResponse)).get();

                    if(tokenValidation.trim().equals("Ok"))
                    {
                        sharedPrefs.edit().putString("Token",httpResponse).apply();
                        String userId = new HttpTask().execute("get", HttpHandler.userId(httpResponse)).get().trim();
                        sharedPrefs.edit().putString("userId", userId).apply();
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
                } catch (InvalidKeySpecException e) {
                    Log.e(TAG,e.getMessage());
                } catch (NoSuchAlgorithmException e) {
                    Log.e(TAG,e.getMessage());
                }
            }
        }
    }

    public void newAccount(View v){
        if (v.getId()==R.id.createAccountPrimary){
            Intent i = new Intent(MainActivity.this, Register.class);
            startActivity(i);
        }
    }

    public void forgotPassword(View v){
        if (v.getId()==R.id.passwordForgotten){
            Intent i = new Intent(MainActivity.this, Home.class);
            startActivity(i);
        }
    }

    private String loginInfo() throws ExecutionException, InterruptedException, InvalidKeySpecException, NoSuchAlgorithmException
    {
        String email = emailField.getText().toString();
        Log.d(TAG, email);
        String password = passwordField.getText().toString();
        Log.d(TAG, password);
        String salt = new HttpTask().execute("get",HttpHandler.getSalt(email)).get();
        Log.d(TAG, salt);
        String passwordHash = PassworHandler.hashPassword(password.trim(),salt.trim());
        Log.d(TAG, passwordHash);
        return emailField.getText().toString() + "/" + passwordHash ;
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
