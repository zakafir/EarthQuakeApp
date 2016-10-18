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

    private static final String LOCATION_SEPARATOR=" of ";

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
        //finding the earthquake
        DetailsOfEarthquake currentEarthQuake = (DetailsOfEarthquake) getItem(position);

        //the textView mag
        TextView magTextView = (TextView)listItemView.findViewById(R.id.magTextView);
        magTextView.setText(currentEarthQuake.getMag());

        //the original location
        String originalLocation = currentEarthQuake.getLocation();

        //this strings made to split our location into 2
        String primaryLocation;
        String locationOffset;

        if(originalLocation.contains(LOCATION_SEPARATOR))
        {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        }else{
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        //the primary location view
        TextView primaryLocationView = (TextView)listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        //the offset location view
        TextView locationOffsetView = (TextView)listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);



        //create a new date object
        Date dateOject = new Date(currentEarthQuake.getTimeInMilliseconds());

        //finding the textView with the view ID date
        TextView dateTextView = (TextView)listItemView.findViewById(R.id.dateTextView);

        //format the date String ('1996-01-08')
        String formattedDate = formatDate(dateOject);
        dateTextView.setText(formattedDate);

        //finding the textView with the view ID time
        TextView timeTextView = (TextView)listItemView.findViewById(R.id.timeTextView);

        //format the date String ('09:07')
        String formattedTime = formatTime(dateOject);
        timeTextView.setText(formattedTime);




        return listItemView;
    }

    private String formatDate(Date dateOject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(dateOject);
    }

    private String formatTime(Date dateOject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(dateOject);
    }

}
