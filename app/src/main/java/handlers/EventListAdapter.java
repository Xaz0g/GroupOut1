package handlers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.BaseAdapter;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.marietopphem.groupout1.EventSettings;
import com.example.marietopphem.groupout1.Home;
import com.example.marietopphem.groupout1.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.concurrent.ExecutionException;

import models.EveObject;
import models.Event;

/**
 * Created by amaliaskantz on 2017-05-16.
 */

public class EventListAdapter extends BaseAdapter {

    private Context mContext;
    private List<EveObject> mEventList;
    private int userId;
    private String token;
    DescriptionPopUp dpu;

    public EventListAdapter(Context mContext, List<EveObject> mEventList) {
        this.mContext = mContext;
        this.mEventList = mEventList;
    }


    public void setUserId(int id) {
        userId = id;
    }

    @Override
    public int getCount() {
        return mEventList.size();
    }

    @Override
    public Object getItem(int position) {
        return mEventList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_list, null);

        Button deleteBtn = (Button) v.findViewById(R.id.delete_btn);
        Button infoBtn = (Button) v.findViewById(R.id.info_btn);
        Button settings = (Button) v.findViewById(R.id.settings);

        TextView eventName = (TextView) v.findViewById(R.id.event_name_maker);
        TextView placeName = (TextView) v.findViewById(R.id.place_name_maker);
        TextView date = (TextView) v.findViewById(R.id.date_maker);
        TextView startTime = (TextView) v.findViewById(R.id.startTime_maker);
        TextView endTime = (TextView) v.findViewById(R.id.endTime_maker);


        //set text for textview
        eventName.setText(mEventList.get(position).getName());
        placeName.setText(mEventList.get(position).getPlaceName());
        date.setText(mEventList.get(position).getEventDate());
        startTime.setText(mEventList.get(position).getStartTime() + " - ");
        endTime.setText(mEventList.get(position).getEndTime());

        Log.d("BLAO user: ", userId + "");
        if ((mEventList.get(position).getLeaderId() != userId)) {
            settings.setVisibility(View.GONE);

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String request = HttpHandler.cancelParticipation(token,mEventList.get(position).getId());
                    Log.d("BLAO",request);
                    try {
                        String response = new HttpTask().execute("PUT", request).get().trim();
                        Log.d("BLAO",response);

                        if(response.equals("true"))
                        {
                            mEventList.remove(position);
                            notifyDataSetChanged();
                        }

                    } catch (InterruptedException e) {
                        Log.e("BLAO",e.getMessage());
                    } catch (ExecutionException e) {
                        Log.e("BLAO",e.getMessage());
                    }
                }
            });
        } else {
            settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("BLAO", "ISLEADER!");
                    notifyDataSetChanged();
                }
            });
            deleteBtn.setVisibility(View.GONE);
        }

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent info = new Intent(mContext, DescriptionPopUp.class);

                String descriptionToPopUp = mEventList.get(position).getDescription();
                info.putExtra("Description", descriptionToPopUp);

                String endTimeToPopUp = mEventList.get(position).getEndTime();
                info.putExtra("EndTime", endTimeToPopUp);

                String startTimeToPopUp = mEventList.get(position).getStartTime();
                info.putExtra("StartTime", startTimeToPopUp);

                String categoryToPopUp = mEventList.get(position).getCategory();
                info.putExtra("Category", categoryToPopUp);

                String dateToPopUp = mEventList.get(position).getEventDate();
                info.putExtra("Date", dateToPopUp);

                String eventNameToPopUp = mEventList.get(position).getName();
                info.putExtra("EventName", eventNameToPopUp);

                String difficultyToPopUp = mEventList.get(position).getDifficulty();
                info.putExtra("Difficulty", difficultyToPopUp);

                int maxCapacityToPopUp = mEventList.get(position).getMaxCapacity();
                String max = maxCapacityToPopUp + " ";
                info.putExtra("MaxCapacity", max);

                int minCapacityToPopUp = mEventList.get(position).getMinCapacity();
                String min = minCapacityToPopUp + " ";
                info.putExtra("MinCapacity", min);

                String placeToPopUp = mEventList.get(position).getPlaceName();
                info.putExtra("Place", placeToPopUp);
                mContext.startActivity(info);

            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, EventSettings.class);

                String descriptionToPopUp = mEventList.get(position).getDescription();
                intent.putExtra("Description", descriptionToPopUp);

                String endTimeToPopUp = mEventList.get(position).getEndTime();
                intent.putExtra("EndTime", endTimeToPopUp);

                String startTimeToPopUp = mEventList.get(position).getStartTime();
                intent.putExtra("StartTime", startTimeToPopUp);

                String categoryToPopUp = mEventList.get(position).getCategory();
                intent.putExtra("Category", categoryToPopUp);

                String dateToPopUp = mEventList.get(position).getEventDate();
                intent.putExtra("Date", dateToPopUp);

                String eventNameToPopUp = mEventList.get(position).getName();
                intent.putExtra("EventName", eventNameToPopUp);

                String difficultyToPopUp = mEventList.get(position).getDifficulty();
                intent.putExtra("Difficulty", difficultyToPopUp);

                int maxCapacityToPopUp = mEventList.get(position).getMaxCapacity();
                intent.putExtra("MaxCapacity", maxCapacityToPopUp);

                int minCapacityToPopUp = mEventList.get(position).getMinCapacity();
                intent.putExtra("MinCapacity", minCapacityToPopUp);

                String placeToPopUp = mEventList.get(position).getPlaceName();
                intent.putExtra("Place", placeToPopUp);
                mContext.startActivity(intent);
            }
        });

        v.setTag(mEventList.get(position).getStartTime());
        return v;

    }

    public void setToken(String token)
    {
        this.token = token;
    }


}
