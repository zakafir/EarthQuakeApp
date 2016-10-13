package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zakaria_afir on 13/10/2016.
 */
public class DetailsOfEarthQuakeAdapter extends ArrayAdapter {

    public DetailsOfEarthQuakeAdapter(Context context, ArrayList<DetailsOfEarthquake> EarthQuakeDetails) {

        super(context, 0, EarthQuakeDetails );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        DetailsOfEarthquake currentEarthQuake = (DetailsOfEarthquake) getItem(position);

        TextView magTextView = (TextView)listItemView.findViewById(R.id.magTextView);
        magTextView.setText(currentEarthQuake.getMag());

        TextView locationTextView = (TextView)listItemView.findViewById(R.id.locationTextView);
        locationTextView.setText(currentEarthQuake.getLocation());

        TextView dateTextView = (TextView)listItemView.findViewById(R.id.dateTextView);
        dateTextView.setText(currentEarthQuake.getDate());


        return listItemView;
    }
}
