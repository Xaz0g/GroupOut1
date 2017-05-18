package handlers;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.BaseAdapter;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.marietopphem.groupout1.R;

import java.util.List;

import models.Event;

/**
 * Created by amaliaskantz on 2017-05-16.
 */

public class EventListAdapter extends BaseAdapter{

    private Context mContext;
    private List<Event> mEventList;

    public EventListAdapter(Context mContext, List<Event> mEventList) {
        this.mContext = mContext;
        this.mEventList = mEventList;
    }


    @Override
    public int getCount(){
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
        Button deleteBtn = (Button)v.findViewById(R.id.delete_btn);
        Button settings = (Button)v.findViewById(R.id.settings);


        TextView eventName = (TextView)v.findViewById(R.id.event_name_maker);
        TextView placeName = (TextView)v.findViewById(R.id.place_name_maker);
        TextView date = (TextView)v.findViewById(R.id.date_maker);
        TextView startTime = (TextView)v.findViewById(R.id.startTime_maker);
        TextView endTime = (TextView)v.findViewById(R.id.endTime_maker);
        TextView participants = (TextView)v.findViewById(R.id.numberOfParticipants);
        TextView difficulty = (TextView)v.findViewById(R.id.difficulty);

        //set text for textview
        eventName.setText(mEventList.get(position).getName());
        placeName.setText(mEventList.get(position).getPlace());
        date.setText(mEventList.get(position).getDate());
        startTime.setText(mEventList.get(position).getStartTime());
        endTime.setText(mEventList.get(position).getEndTime());

        difficulty.setText(mEventList.get(position).getDifficulty());

        if(!mEventList.get(position).isLeader()){
            settings.setVisibility(View.GONE);
            participants.setText("Du är anmäld");
            deleteBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //do something

                    mEventList.remove(position); //or some other task
                    notifyDataSetChanged();
                }
            });
        }else if(mEventList.get(position).isLeader()){

            settings.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //do something
                }
            });
            participants.setText("Du är skapare");
            deleteBtn.setVisibility(View.GONE);
        }


        v.setTag(mEventList.get(position).getStartTime());
        return v;
    }


}
