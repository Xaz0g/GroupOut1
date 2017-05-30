package handlers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.marietopphem.groupout1.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

import models.EveObject;

/**
 * Created by Xaz0g on 2017-05-24.
 */

public class PlaceEventAdapter extends BaseAdapter {

    private Context mContext;
    private List<EveObject> mEventList;
    String token;
    private int userId;

    public PlaceEventAdapter (Context mContext, List<EveObject> mEventList) {
        this.mContext = mContext;
        this.mEventList = mEventList;

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
        View v = View.inflate(mContext, R.layout.list_for_places, null);

        CheckBox particpate = (CheckBox) v.findViewById(R.id.pparticipateCheck);
        TextView eventName = (TextView) v.findViewById(R.id.pevent_name_maker);
        TextView endTime = (TextView) v.findViewById(R.id.pendTime_maker);
        TextView startTime = (TextView) v.findViewById(R.id.pstartTime_maker);
        TextView date = (TextView) v.findViewById(R.id.pdate_maker);

        particpate.setChecked(checkParticipating(position));
        eventName.setText(mEventList.get(position).getName());
        endTime.setText(mEventList.get(position).getEndTime());
        startTime.setText(mEventList.get(position).getStartTime() + " - ");
        date.setText(mEventList.get(position).getEventDate());

        final CheckBox participate = (CheckBox) v.findViewById(R.id.pparticipateCheck);

        participate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String response = "";
                if(isChecked)
                {
                    Log.d("CheckBox", "True");
                    String request = HttpHandler.newParticipation(token, mEventList.get(position).getId());
                    Log.d("CheckBox", request);
                    try {
                        response = new HttpTask().execute("PUT", request).get().trim();
                    } catch (InterruptedException e) {
                        Log.e("CheckBox", e.getMessage());
                    } catch (ExecutionException e) {
                        Log.e("CheckBox", e.getMessage());
                    }

                    if(!response.equals("true")){
                        participate.setChecked(false);
                        Log.d("CheckBox", "ERROR : " + response);
                    }
                    else
                    {
                        Log.d("CheckBox", "YAY : " + response);
                        mEventList.get(position).setParticipating(!mEventList.get(position).isParticipating());
                    }
                }
                else
                {
                    Log.d("CheckBox", "False");
                    String request = HttpHandler.cancelParticipation(token, mEventList.get(position).getId());
                    Log.d("CheckBox", request);
                    try {
                        response = new HttpTask().execute("PUT", request).get().trim();
                    } catch (InterruptedException e) {
                        Log.e("CheckBox", e.getMessage());
                    } catch (ExecutionException e) {
                        Log.e("CheckBox", e.getMessage());
                    }

                    if(!response.equals("true")){
                        participate.setChecked(true);
                        Log.d("CheckBox", "ERROR : " + response);
                    }
                    else
                    {
                        Log.d("CheckBox", "YAY : " + response);
                        mEventList.get(position).setParticipating(!mEventList.get(position).isParticipating());
                    }
                }
            }
        });

        return v;
    }

    private boolean checkParticipating(int pos)
    {
        if(mEventList.get(pos).isParticipating())
        {
            Log.d("Boolis", "true");
            return true;
        }
        else
        {
            Log.d("Boolis", "false");
            return false;
        }
    }

    public void setToken(String token){
        this.token = token;
    }

    public void setUserId(int userId){
        this.userId=userId;
    }
}
