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
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.list_for_places, null);
        CheckBox particpate = (CheckBox) v.findViewById(R.id.pparticipateCheck);
        TextView eventName = (TextView) v.findViewById(R.id.pevent_name_maker);
        TextView difficulty = (TextView) v.findViewById(R.id.pdifficulty_maker);
        TextView endTime = (TextView) v.findViewById(R.id.pendTime_maker);
        TextView startTime = (TextView) v.findViewById(R.id.pstartTime_maker);
        TextView date = (TextView) v.findViewById(R.id.pdate_maker);
        TextView registrations = (TextView) v.findViewById(R.id.pnumberOfParticipants_maker);

        particpate.setChecked(checkParticipation(position));
        eventName.setText(mEventList.get(position).getName());
        difficulty.setText("Svårighetsgrad: " + mEventList.get(position).getDifficulty() + "/5");
        endTime.setText(mEventList.get(position).getEndTime());
        startTime.setText(mEventList.get(position).getStartTime() + " - ");
        date.setText(mEventList.get(position).getEventDate());
        registrations.setText("Antal anmälda: " + mEventList.get(position).getRegistration() + "/" + mEventList.get(position).getMaxCapacity());

        return v;
    }

    private boolean checkParticipation(int pos){
        if(userId == mEventList.get(pos).getLeaderId()){
            return true;
        }
        return false;
    }

    public void setToken(String token){
        this.token = token;
    }

    public void setUserId(int userId){
        this.userId=userId;
    }
}
