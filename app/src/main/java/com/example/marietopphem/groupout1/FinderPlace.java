package com.example.marietopphem.groupout1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FinderPlace extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_finder_place, container, false);
        return rootView;
    }

    public void searchPlace(View view){
        if (view.getId()== R.id.FinderSearchBtn){


        }
    }
}
