package com.example.marietopphem.groupout1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class FinderPlace extends Fragment {

    private EditText searchField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_finder_place, container, false);
        searchField = (EditText) rootView.findViewById(R.id.finderPlaceField);
        return rootView;
    }

    public String getSearchField()
    {
        return searchField.getText().toString();
    }
}
