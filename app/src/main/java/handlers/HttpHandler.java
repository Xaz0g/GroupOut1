package handlers;

import android.util.Log;

/**
 * Created by Xaz0g on 2017-05-12.
 */

public class HttpHandler
{
    private static final String HOST_ADRESS = "https://testpvt.herokuapp.com/";
    private static final String USER_NEW_USER = "user/newUser/";

    public static String newUser(String params)
    {
        return HOST_ADRESS + USER_NEW_USER + params;
    }
}
