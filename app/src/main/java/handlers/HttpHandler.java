package handlers;

import android.util.Log;

/**
 * Created by Xaz0g on 2017-05-12.
 */

public class HttpHandler
{
    private final static GetUrlContentTask GET_URL_CONTENT_TASK = new GetUrlContentTask();

    protected static String response;

    public String urlRequest(String url)
    {
        response = null;

        GET_URL_CONTENT_TASK.execute(url);

        while(response == null)
        {
        }

        return response;
    }
}
