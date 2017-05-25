package handlers;

import android.util.Log;

/**
 * Created by Xaz0g on 2017-05-12.
 */

public class HttpHandler
{
    private static final String HOST_ADRESS = "https://testpvt.herokuapp.com/";

    private static final String USER = "user/";
    private static final String EVENT = "event/";
    private static final String PLACE = "place/";
    private static final String PARTICIPATION = "participation/";

    private static final String NEW_USER = "newUser/";
    private static final String CHECK_TOKEN = "checkToken/";
    private static final String LOGIN = "login/";
    private static final String NEW_EVENT = "newEvent/";
    private static final String SEARCH = "search/";
    private static final String USER_ID = "userId/";
    private static final String GET_EVENT = "getEvent/";
    private static final String FAVOIRTE = "favorite/";
    private static final String CHECK_PARTICIPATION = "checkParticipation/";
    private static final String NEARBY = "nearby/";

    private static final String GET_SALT = "getSalt/";
    private static final String GET = "get/";

    public static String newUser(String user)
    {

        return HOST_ADRESS + USER + NEW_USER + user;
    }

    public static String newEvent(String newEvent)
    {

        return HOST_ADRESS + EVENT + NEW_EVENT + newEvent;
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

    public static String searchForPlace(String category, String searchTerm)
    {
        return  HOST_ADRESS + PLACE + SEARCH + category + "/" + searchTerm;
    }

    public static String userId(String token)
    {
        return HOST_ADRESS + USER + USER_ID + token;
    }

    public static String getEvent(String by, String token, String parameter){

        return HOST_ADRESS + EVENT + GET_EVENT + by + "/" + token + "/" + parameter;
    }

    public static String searcPlace()
    {
        return HOST_ADRESS + PLACE + SEARCH;
    }

    public static String getFavorite(String token)
    {
        return HOST_ADRESS + PLACE + FAVOIRTE + GET + token;
    }

    public static String checkParticipation(String token, int id){

        return HOST_ADRESS + PARTICIPATION + CHECK_PARTICIPATION + token + "/" + id;
    }

    public static String getNearbyPositions(double longi, double lati, String place, String token){

        return HOST_ADRESS + PLACE + NEARBY + longi + "/" +  lati + "/" + place + "/" + token;
    }
}
