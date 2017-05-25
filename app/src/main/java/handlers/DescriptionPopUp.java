package handlers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.marietopphem.groupout1.Home;
import com.example.marietopphem.groupout1.R;

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
        getWindow().setLayout((int)(width*.5), (int)(height*.5));

        String description;
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            description= null;
        } else {
            description = extras.getString("Description");
        }
        TextView descriptionView = (TextView) findViewById(R.id.descriptionView);
        descriptionView.setText(description);


    }
    public void okButton(View v){
        Intent i = new Intent(DescriptionPopUp.this, Home.class);
        startActivity(i);
    }
}
