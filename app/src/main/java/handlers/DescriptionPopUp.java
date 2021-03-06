package handlers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.marietopphem.groupout1.Home;
import com.example.marietopphem.groupout1.R;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

import static android.content.ContentValues.TAG;

/**
 * Created by Malin on 2017-05-22.
 */

public class DescriptionPopUp extends Activity {


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.87), (int)(height*.67));

        String description;
        String endTime;
        String startTime;
        String eventName;
        String place;
        String difficulty;
        String registration;
        String maxCapacity;
        String date;
        String category;
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            description= null;
            endTime = null;
            startTime = null;
            eventName = null;
            place = null;
            difficulty = null;
            registration = null;
            maxCapacity = null;
            date = null;
            category = null;
        } else {
            description = extras.getString("Description");
            endTime = extras.getString("EndTime");
            startTime = extras.getString("StartTime");
            eventName = extras.getString("EventName");
            place = extras.getString("Place");
            difficulty = extras.getString("Difficulty");
            registration = extras.getString("Registrations");
            maxCapacity = extras.getString("MaxCapacity");
            date = extras.getString("Date");
            category = extras.getString("Category");
        }
        TextView descriptionPopUpView = (TextView) findViewById(R.id.descriptionPopUp);
        descriptionPopUpView.setText(description);

        TextView endTimePopUpView = (TextView) findViewById(R.id.endTimePopUp);
        endTimePopUpView.setText(endTime);

        TextView startTimePopUpView = (TextView) findViewById(R.id.startTimePopUp);
        startTimePopUpView.setText(startTime);

        TextView eventNamePopUpView = (TextView) findViewById(R.id.eventNamePopUp);
        eventNamePopUpView.setText(eventName);

        TextView placePopUpView = (TextView) findViewById(R.id.eventPlacePopUp);
        placePopUpView.setText(" " + place);

        TextView difficultyPopUpView = (TextView) findViewById(R.id.difficultyPopUp);
        difficultyPopUpView.setText(difficulty + "/5");

        TextView minCapacityPopUpView = (TextView) findViewById(R.id.maxCapacityPopUp);
        minCapacityPopUpView.setText(registration + "/" + maxCapacity);

        TextView datePopUpView = (TextView) findViewById(R.id.datePopUp);
        datePopUpView.setText(date);

        TextView categoryPopUpView = (TextView) findViewById(R.id.categoryPopUp);
        categoryPopUpView.setText(category);

        ShareButton fbShareButton = (ShareButton) findViewById(R.id.fbShareButton);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://fb.me/464928887185539"))
                .setQuote("Häng med mig och på passet " + eventName + " på " + place + " den " + date + " klockan " +startTime)
                .setShareHashtag(new ShareHashtag.Builder()
                        .setHashtag("#Groupout")
                        .build())
                .build();
        fbShareButton.setShareContent(content);
    }

}
