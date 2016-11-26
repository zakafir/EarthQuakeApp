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
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<DetailsOfEarthquake>>{

    //the loader ID of the earthquake
    private static final int EARTHQUAKE_LOADER_ID = 1;

    private DetailsOfEarthquakeAdapter mAdapter;

    private static final String USGS_REQUEST_URL =
            "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=2&limit=50";
    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        mEmptyStateTextView = (TextView)findViewById(R.id.empty_view);
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        earthquakeListView.setEmptyView(mEmptyStateTextView);
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

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID,null,this);
            Log.v(LOG_TAG,"the initialisation of the loader");
        }else{
            View loadingIndocator = findViewById(R.id.loading_indicator);
            loadingIndocator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

    }

    @Override
    public Loader<List<DetailsOfEarthquake>> onCreateLoader(int id, Bundle args) {
        Log.v(LOG_TAG,"the creation of the loader");
        return new EarthquakeLoader(this,USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<DetailsOfEarthquake>> loader, List<DetailsOfEarthquake> earthquakes) {
        View loadingIndocator = findViewById(R.id.loading_indicator);
        loadingIndocator.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.no_earthquake);
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
