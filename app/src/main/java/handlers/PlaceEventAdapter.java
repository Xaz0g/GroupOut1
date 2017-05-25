package handlers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.list_for_places, null);
        CheckBox particpate = (CheckBox) v.findViewById(R.id.pparticipateCheck);
        TextView eventName = (TextView) v.findViewById(R.id.pevent_name_maker);
        TextView difficulty = (TextView) v.findViewById(R.id.pdifficulty_maker);
        TextView endTime = (TextView) v.findViewById(R.id.pendTime_maker);
        TextView startTime = (TextView) v.findViewById(R.id.pstartTime_maker);
        TextView date = (TextView) v.findViewById(R.id.pdate_maker);
        TextView registrations = (TextView) v.findViewById(R.id.pnumberOfParticipants_maker);

        particpate.setChecked(false);
        eventName.setText(mEventList.get(position).getName());
        difficulty.setText(mEventList.get(position).getDifficulty());
        endTime.setText(mEventList.get(position).getEndTime());
        startTime.setText(mEventList.get(position).getStartTime());
        date.setText(mEventList.get(position).getEventDate());
        registrations.setText(mEventList.get(position).getRegistration());

        return v;
    }

    private boolean checkParticipation(int pos){


        String request = HttpHandler.checkParticipation(token, mEventList.get(pos).getId());

        Log.d("PLASAC", request);
        try {
            String response = new HttpTask().execute("get", request).get();
            Log.d("PLASAC", response);
            return response.equals("true".trim());
        } catch (InterruptedException e) {
            Log.e("PLASAC", e.getMessage());
        } catch (ExecutionException e) {
            Log.e("PLASAC", e.getMessage());
        }
        return false;
    }

    public void setToken(String token){
        this.token = token;
    }
}
