package com.example.android.quakereport;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zakaria_afir on 15/10/2016.
 */
public final class Query {
    /** Tag for the log messages */
    public static final String LOG_TAG = Query.class.getSimpleName();

    private Query()
    {

    }


    //this method get throw JSON nodes to get the properties of an earthquake
    public static ArrayList<DetailsOfEarthquake> extractFeatureFromJson(String earthquakeJSON)
    {
        if(TextUtils.isEmpty(earthquakeJSON))
        {
            return null;
        }
        ArrayList<DetailsOfEarthquake> earthquakes = new ArrayList<DetailsOfEarthquake>();

        try {
            //Start with the root JSON
            JSONObject root = new JSONObject(earthquakeJSON);

            //then access to the child-node
            JSONArray features = root.getJSONArray("features");

            //loop to recurse all the earthquake objects
            for(int i=0; i<=features.length();i++) {
                JSONObject node = features.getJSONObject(i);
                JSONObject properties = node.getJSONObject("properties");

                //the properties of an earthquake
                double mag = properties.getDouble("mag");
                String place = properties.optString("place").toString();
                String url = properties.optString("url").toString();

                //extract the value for the key called "time"
                long time = properties.getLong("time");
                //creating an earthquake object
                DetailsOfEarthquake oneEarthquake = new DetailsOfEarthquake(place,mag,time,url);

                //add One Earthquake to the list of Earthquakes
                earthquakes.add(oneEarthquake);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return earthquakes;
    }

    /**
     * Query the USGS dataset and return an {@link List<DetailsOfEarthquake>} object to represent a single earthquake.
     */
    public static List<DetailsOfEarthquake> fetchEarthquakeData(String requestUrl) {
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        List<DetailsOfEarthquake> earthquake = extractFeatureFromJson(jsonResponse);

        // Return the {@link Event}
        return earthquake;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }



}
