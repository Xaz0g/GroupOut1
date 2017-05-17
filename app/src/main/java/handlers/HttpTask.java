package handlers;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Xaz0g on 2017-05-16.
 */

public class HttpTask extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... params) {
        String httpMethod = params[0];
        String urlString = params[1];

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if(httpMethod.equalsIgnoreCase("put"))
            {
                connection.setRequestMethod("PUT");
                connection.setDoOutput(true);
            }
            else
            {
                connection.setRequestMethod("GET");
            }

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();

            int code = connection.getResponseCode();
            if (code == 200) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
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
}
