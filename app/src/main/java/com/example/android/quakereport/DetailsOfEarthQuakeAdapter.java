package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zakaria_afir on 13/10/2016.
 */
public class DetailsOfEarthquakeAdapter extends ArrayAdapter {

    public DetailsOfEarthquakeAdapter(Context context, ArrayList<DetailsOfEarthquake> EarthQuakeDetails) {

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

        Date dateOject = new Date(currentEarthQuake.getTimeInMilliseconds());
        TextView dateTextView = (TextView)listItemView.findViewById(R.id.dateTextView);
        String formattedDate = formatDate(dateOject);
        dateTextView.setText(formattedDate);

        TextView timeTextView = (TextView)listItemView.findViewById(R.id.timeTextView);
        String formattedTime = formatTime(dateOject);
        timeTextView.setText(formattedTime);


        return listItemView;
    }

    private String formatDate(Date dateOject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateOject);
    }

    private String formatTime(Date dateOject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateOject);
    }

}
