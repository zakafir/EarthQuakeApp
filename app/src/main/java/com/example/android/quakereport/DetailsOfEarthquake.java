package com.example.android.quakereport;

/**
 * Created by zakaria_afir on 13/10/2016.
 */
public class DetailsOfEarthquake {

    private String location;
    private String mag;
    private String date;

    public DetailsOfEarthquake(String location, String mag, String date) {
        this.location = location;
        this.mag = mag;
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public String getMag() {
        return mag;
    }

    public String getDate() {
        return date;
    }
}
