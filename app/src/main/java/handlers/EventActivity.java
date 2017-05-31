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

    public static ArrayList<EveObject> parseJSONv2 (String json) throws JSONException{
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
            Boolean participating = event.getBoolean("participating");
            String placeName = event.getString("placeName");


            EveObject eve = new EveObject(name, category, description, placeId, eventDate, startTime, endTime, visible, id, leaderId, minCapacity, maxCapacity, registration, difficulty, participating, placeName);
            eveList.add(eve);

        }

        return eveList;
    }

    public static String eveToJson(EveObject e)
    {
        String nameParsed = parseToLegal(e.getName());
        String descParced = parseToLegal(e.getDescription());

        return "{\"id\":\"" + e.getId() + "\",\"minCapacity\":\"" + e.getMinCapacity() + "\",\"maxCapacity\":\"" + e.getMaxCapacity()
                + "\",\"difficulty\":\"" + e.getDifficulty() + "\",\"name\":\"" + nameParsed
                + "\",\"category\":\"" + e.getCategory()
                + "\",\"description\":\"" + descParced + "\",\"placeId\":\"" + e.getPlaceId()
                + "\",\"eventDate\":\"" + e.getEventDate() + "\",\"startTime\":\"" + e.getStartTime()
                + "\",\"endTime\":\"" + e.getEndTime() + "\"}";
    }

    private static String parseToLegal(String s){
        String sParsed = "";

        for(char c : s.toCharArray())
        {
            sParsed += (c == '?') ? "*!*" : c;
        }

        return sParsed;
    }
}
