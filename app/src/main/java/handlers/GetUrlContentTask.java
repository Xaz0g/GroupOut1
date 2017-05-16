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

public class GetUrlContentTask extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... urlString) {
        URL url = null;
        try {

            url = new URL(urlString[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            int code = connection.getResponseCode();

            if(code == 200)
            {
                BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String content = "", line;
                while ((line = rd.readLine()) != null) {
                    content += line + "\n";
                }
                Log.i("URLSTUFF", content);
                return content;
            }
            else
            {
                Log.e("BadRequest","Error code = " + code);
            }

        } catch (MalformedURLException e) {
            Log.e("URLSTUFF1", e.getMessage());
        } catch (ProtocolException e) {
            Log.e("URLSTUFF2", e.getMessage());
        } catch (IOException e) {
            Log.e("URLSTUFF3", e.getMessage());
        }
        return "";
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(String result) {
    }
}