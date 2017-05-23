package com.example.marietopphem.groupout1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class FinderPlace extends Fragment {

    private EditText searchField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_finder_place, container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.placelistView);
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

        searchField = (EditText) rootView.findViewById(R.id.finderPlaceField);
        return rootView;
    }

    public String getSearchField()
    {
        return searchField.getText().toString();
    }
}
