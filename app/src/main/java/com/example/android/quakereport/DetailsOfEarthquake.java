package com.example.android.quakereport;

/**
 * Created by zakaria_afir on 13/10/2016.
 */
public class DetailsOfEarthquake {

    private String location;
    private String mag;
    private long timeInMilliseconds;

    public DetailsOfEarthquake(String location, String mag, long timeInMilliseconds) {
        this.location = location;
        this.mag = mag;
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public String getLocation() {
        return location;
    }

    public String getMag() {
        return mag;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }
}
