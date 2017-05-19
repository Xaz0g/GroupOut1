package handlers;

import android.util.Log;

/**
 * Created by Xaz0g on 2017-05-12.
 */

public class HttpHandler
{
    private static final String HOST_ADRESS = "https://testpvt.herokuapp.com/";

    private static final String USER = "user/";

    private static final String NEW_USER = "newUser/";
    private static final String CHECK_TOKEN = "checkToken/";
    private static final String LOGIN = "login/";

    private static final String GET_SALT = "getSalt/";

    public static String newUser(String user)
    {

        return HOST_ADRESS + USER + NEW_USER + user;
    }

    public static String checkToken(String token)
    {
        return HOST_ADRESS + USER + CHECK_TOKEN + token;
    }

    public static String login(String loginInfo)
    {
        return HOST_ADRESS + USER + LOGIN + loginInfo;
    }

    public static String getSalt(String userName)
    {
        return HOST_ADRESS + USER + LOGIN + GET_SALT + userName;
    }
}
