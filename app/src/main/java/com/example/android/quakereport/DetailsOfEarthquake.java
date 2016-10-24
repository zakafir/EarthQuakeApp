package com.example.android.quakereport;

/**
 * Created by zakaria_afir on 13/10/2016.
 */
public class DetailsOfEarthquake {

    private String location;
    private double mag;
    private long timeInMilliseconds;
    private String url;

    public DetailsOfEarthquake(String location, double mag, long timeInMilliseconds) {
        this.location = location;
        this.mag = mag;
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public DetailsOfEarthquake(String location, double mag, long timeInMilliseconds, String url)
    {
        this(location,mag,timeInMilliseconds);
        this.url = url;
    }

    public String getLocation() {
        return location;
    }

    public double getMag() {
        return mag;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public String getUrl() {
        return url;
    }
}
