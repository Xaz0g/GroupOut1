package com.example.marietopphem.groupout1;

/**
 * Created by marietopphem on 2017-05-12.
 */


import android.support.v4.app.Fragment;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

public class MapSearch extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.t3map, container, false);
        return rootView;
    }
}