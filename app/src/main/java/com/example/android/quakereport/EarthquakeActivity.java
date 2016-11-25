/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<DetailsOfEarthquake>>{

    //the loader ID of the earthquake
    private static final int EARTHQUAKE_LOADER_ID = 1;

    private DetailsOfEarthquakeAdapter mAdapter;

    private static final String USGS_REQUEST_URL =
            "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        mAdapter = new DetailsOfEarthquakeAdapter(this,new ArrayList<DetailsOfEarthquake>());

        earthquakeListView.setAdapter(mAdapter);
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailsOfEarthquake detailsOfEarthquake = (DetailsOfEarthquake) mAdapter.getItem(position);
                Uri earthQuakeUri = Uri.parse(detailsOfEarthquake.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW,earthQuakeUri);
                startActivity(websiteIntent);
            }
        });

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID,null,this);
        Log.v(LOG_TAG,"the initialisation of the loader");
    }

    @Override
    public Loader<List<DetailsOfEarthquake>> onCreateLoader(int id, Bundle args) {
        Log.v(LOG_TAG,"the creation of the loader");
        return new EarthquakeLoader(this,USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<DetailsOfEarthquake>> loader, List<DetailsOfEarthquake> earthquakes) {

        mAdapter.clear();
        if(earthquakes!=null && !earthquakes.isEmpty())
        {
            mAdapter.addAll(earthquakes);
        }
        Log.v(LOG_TAG,"the loader is finished");
    }

    @Override
    public void onLoaderReset(Loader<List<DetailsOfEarthquake>> loader) {
        mAdapter.clear();
        Log.v(LOG_TAG,"the loader is reset now");
    }
}
