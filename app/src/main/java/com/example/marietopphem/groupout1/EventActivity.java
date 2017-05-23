package com.example.marietopphem.groupout1;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

import java.util.ArrayList;
/**
 * Created by Elina on 2017-05-23.
 */

public class EventActivity {
    static EventActivity program = new EventActivity();
    private ArrayList<EveObject> eveList = new ArrayList<>();
    private static String JSON_EVENT_DATA =
            "["
                    + " { "+" \"id\" : \"12\","
                    + " \"leaderId\" : \"18\","
                    + " \"minCapacity\" : \"2\","
                    + " \"maxCapacity\" : \"100\","
                    + " \"registration\" : \"1\","
                    + " \"difficulty\" : \"5\","
                    + " \"name\" : \"Cirkelövning\","
                    + " \"category\" : \"Styrketräning\","
                    + " \"description\" : \"Tänkte köra lite cirkelövningar, kom!!\","
                    + " \"placeId\" : \"395561a3-f816-46fd-9c55-34afd436120e\","
                    + " \"eventDate\" : \"2017-08-11\","
                    + " \"startTime\" : \"11:00:00\","
                    + " \"endTime\" : \"12:00:00\","
                    + " \"visible\" : \"false\" " + " }, "

                    + " { "+" \"id\" : \"14\","
                    + " \"leaderId\" : \"18\","
                    + " \"minCapacity\" : \"2\","
                    + " \"maxCapacity\" : \"3\","
                    + " \"registration\" : \"1\","
                    + " \"difficulty\" : \"3\","
                    + " \"name\" : \"Hohoh\","
                    + " \"category\" : \"Styrketräning\","
                    + " \"description\" : \"Vi ska spela fiolioliolej\","
                    + " \"placeId\" : \"395561a3-f816-46fd-9c55-34afd436120e\","
                    + " \"eventDate\" : \"2017-05-19\","
                    + " \"startTime\" : \"15:00:00\","
                    + " \"endTime\" : \"17:00:00\","
                    + " \"visible\" : \"false\" " + " }, "

                    + " { "+" \"id\" : \"15\","
                    + " \"leaderId\" : \"18\","
                    + " \"minCapacity\" : \"5\","
                    + " \"maxCapacity\" : \"50\","
                    + " \"registration\" : \"1\","
                    + " \"difficulty\" : \"4\","
                    + " \"name\" : \"Cirkelövning\","
                    + " \"category\" : \"Styrketräning\","
                    + " \"description\" : \"Tänkte köra lite cirkelövningar, kom!!\","
                    + " \"placeId\" : \"395561a3-f816-46fd-9c55-34afd436120e\","
                    + " \"eventDate\" : \"2017-08-11\","
                    + " \"startTime\" : \"11:00:00\","
                    + " \"endTime\" : \"12:00:00\","
                    + " \"visible\" : \"false\" " + " } "
                    + "]"
            ;

    public void parseJSON () throws JSONException{
        final JSONArray events = new JSONArray(JSON_EVENT_DATA);
        final int n = events.length();

        for(int i = 0; i < n; i++){

            final JSONObject event = events.getJSONObject(i);

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


            EveObject eve = new EveObject(name, category, description, placeId, eventDate, startTime, endTime, visible, id, leaderId, minCapacity, maxCapacity, registration, difficulty);
            eveList.add(eve);

        }
        Log.v("Print that shit", eveList.toString());
    }

    public static void main(String[]args)  {
        try {
            program.parseJSON();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
