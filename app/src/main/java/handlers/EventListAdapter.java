package handlers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.BaseAdapter;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.marietopphem.groupout1.Home;
import com.example.marietopphem.groupout1.R;

import java.util.List;

import models.Event;

/**
 * Created by amaliaskantz on 2017-05-16.
 */

public class EventListAdapter extends BaseAdapter{

    private Context mContext;
    private List<Event> mEventList;

    DescriptionPopUp dpu;

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
        Button infoBtn = (Button)v.findViewById(R.id.info_btn);
        Button settings = (Button)v.findViewById(R.id.settings);


        TextView eventName = (TextView)v.findViewById(R.id.event_name_maker);
        TextView placeName = (TextView)v.findViewById(R.id.place_name_maker);
        TextView date = (TextView)v.findViewById(R.id.date_maker);
        TextView startTime = (TextView)v.findViewById(R.id.startTime_maker);
        TextView endTime = (TextView)v.findViewById(R.id.endTime_maker);
        TextView participants = (TextView)v.findViewById(R.id.numberOfParticipants);
        TextView owner = (TextView)v.findViewById(R.id.owner_or_participant);
        TextView difficulty = (TextView)v.findViewById(R.id.difficulty);

        //set text for textview
        eventName.setText(mEventList.get(position).getName());
        placeName.setText(mEventList.get(position).getPlace());
        date.setText(mEventList.get(position).getDate());
        startTime.setText(mEventList.get(position).getStartTime());
        endTime.setText(mEventList.get(position).getEndTime());
        participants.setText(mEventList.get(position).getParticipants());
        difficulty.setText(mEventList.get(position).getDifficulty());

        if(!mEventList.get(position).isLeader()){
            settings.setVisibility(View.GONE);
            owner.setText("Du är anmäld");
            deleteBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //do something

                    mEventList.remove(position); //or some other task (tar för tillfället bort)
                    notifyDataSetChanged();
                }
            });
            infoBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    String descrip = mEventList.get(position).getDescription();
                    Intent info = new Intent(mContext, DescriptionPopUp.class);
                    info.putExtra("Description", descrip);
                    mContext.startActivity(info);

                }
            } );


        }else if(mEventList.get(position).isLeader()){

            settings.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    mEventList.remove(position); //or some other task (tar för tillfället bort)
                    notifyDataSetChanged();
                }
            });
            owner.setText("Du är skapare");
            deleteBtn.setVisibility(View.GONE);
            infoBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    String descrip = mEventList.get(position).getDescription();
                    Intent info = new Intent(mContext, DescriptionPopUp.class);
                    info.putExtra("Description", descrip);
                    mContext.startActivity(info);

                }
            } );
        }


        v.setTag(mEventList.get(position).getStartTime());
        return v;
    }


}
