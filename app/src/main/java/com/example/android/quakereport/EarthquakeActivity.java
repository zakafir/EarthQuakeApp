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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<DetailsOfEarthquake> earthquakes = new ArrayList<>();
        earthquakes.add(new DetailsOfEarthquake("San Francisco", "1.1","2016-09-07"));
        earthquakes.add(new DetailsOfEarthquake("London", "1.3","2016-09-07"));
        earthquakes.add(new DetailsOfEarthquake("Tokyo", "2.1","2016-09-07"));
        earthquakes.add(new DetailsOfEarthquake("Mexico City",  "2.1","2016-09-07"));
        earthquakes.add(new DetailsOfEarthquake("Moscow",  "1.6","2016-09-07"));
        earthquakes.add(new DetailsOfEarthquake("Rio de Janeiro",  "0.1","2016-09-07"));
        earthquakes.add(new DetailsOfEarthquake("Paris",  "1.5","2016-09-07"));

        DetailsOfEarthQuakeAdapter detailsOfEarthQuakeAdapter = new DetailsOfEarthQuakeAdapter(this,earthquakes);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(detailsOfEarthQuakeAdapter);
    }
}
