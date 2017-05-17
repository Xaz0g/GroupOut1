package handlers;

import android.util.Log;

/**
 * Created by Xaz0g on 2017-05-12.
 */

public class HttpHandler
{
    private static final String HOST_ADRESS = "https://testpvt.herokuapp.com/";

    private static final String USER_NEW_USER = "user/newUser/";

    private static final String USER = "user/";

    private static final String NEW_USER = "newUser/";
    private static final String CHECK_TOKEN = "checkToken/";

    public static String newUser(String params)
    {
        return HOST_ADRESS + USER + NEW_USER + params;
    }

    public static String checkToken(String params)
    {
        return HOST_ADRESS + USER + CHECK_TOKEN + params;
    }
}
