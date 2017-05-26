package handlers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

import java.util.ArrayList;

import models.EveObject;

/**
 * Created by Elina on 2017-05-23.
 */

public class EventActivity {

    public static ArrayList<EveObject> parseJSON (String json) throws JSONException{
        JSONArray events = new JSONArray(json);
        int n = events.length();

        ArrayList<EveObject> eveList = new ArrayList<>();

        for(int i = 0; i < n; i++){

            JSONObject event = events.getJSONObject(i);

            int id = event.getInt("id");
            int leaderId = event.getInt("leaderId");
            int minCapacity = event.getInt("minCapacity");
            int maxCapacity = event.getInt("maxCapacity");
            int registration = event.getInt("registration");
            int difficulty = event.getInt("difficulty");
            String name = event.getString("name");
            String category = event.getString("category");
            String description = event.getString("description");
            String placeId = event.getString("placeId");
            String eventDate = event.getString("eventDate");
            String startTime = event.getString("startTime");
            String endTime = event.getString("endTime");
            Boolean visible = event.getBoolean("visible");
            String placeName = event.getString("placeName");


            EveObject eve = new EveObject(name, category, description, placeId, eventDate, startTime, endTime, visible, id, leaderId, minCapacity, maxCapacity, registration, difficulty, false, placeName);
            eveList.add(eve);

        }

        return eveList;
    }
}
