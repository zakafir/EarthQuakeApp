package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by zakaria_afir on 25/11/2016.
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<DetailsOfEarthquake>> {

    private static final  String LOG_TAG = EarthquakeLoader.class.getName();
    private String mUrl;

    public EarthquakeLoader(Context context,String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<DetailsOfEarthquake> loadInBackground() {

        if(mUrl == null)
        {
            return null;
        }

        List<DetailsOfEarthquake> earthquakes = Query.fetchEarthquakeData(mUrl);
        return earthquakes;
    }

}
