package com.example.marietopphem.groupout1;

/**
 * Created by marietopphem on 2017-05-12.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaceSearch extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.t1place, container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.searchPlaceListView);
        ArrayList<String> listTest = new ArrayList<>();
        listTest.add("Element1");
        listTest.add("Element2");
        listTest.add("Element3");

        final ArrayAdapter<String> allItemsAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, listTest);

        lv.setAdapter(allItemsAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> allItemsAdapter, View view, int position, long id) {
                String value = (String)allItemsAdapter.getItemAtPosition(position);
                Log.d("*********", value);
            }
        });

        return rootView;
    }
}
    