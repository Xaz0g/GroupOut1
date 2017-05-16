package models;

import android.util.Log;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import handlers.PassworHandler;
import handlers.UserInputHandler;

/**
 * Created by Xaz0g on 2017-05-15.
 */

public class NewUser
{
    private String userName;
    private String email;
    private String passwordHash;
    private String salt;

    public String toJsonString()
    {
        return "{\"userName\":\"" + userName + "\",\"email\":\"" + email + "\",\"passwordHash\":\"" + passwordHash + "\",\"salt\":\"" + salt + "\"}";
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getSalt() {
        return salt;
    }
}
