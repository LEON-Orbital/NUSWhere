package com.example.mainpage.API;

import android.util.Log;

import com.example.mainpage.map.Venue;
import com.example.mainpage.map.VenueList;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class NUSModsAPI {


    // Create connection and fetch the data
    public static String request(String uri) throws IOException {
        StringBuilder sb = new StringBuilder();
        URL url = new URL(uri);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.connect();

        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));

            //temporary string to hold each line read from the reader.
            String inputLine = "";
            while ((inputLine = bin.readLine()) != null) {
                sb.append(inputLine);
            }
        } finally {
            //regardless of success or failure
            urlConnection.disconnect();
        }
        return sb.toString();
    }

    // Sort the JSONObject into Venue objects and initialise in VenueList
    public static void fetchVenues() throws IOException, JSONException {

        // create variable to hold return data
        ArrayList<Venue> allVenues = new ArrayList<>();

        // fetch json data and parse into string
        String venues = "https://raw.githubusercontent.com/nusmodifications/nusmods/master/website/src/data/venues.json";
        String result = NUSModsAPI.request(venues);

        //parse the json string
        JSONObject jsonVenues = new JSONObject(result);

        Iterator<String> idList = jsonVenues.keys();
        while (idList.hasNext()) {

            // get key and value
            String id = idList.next();
            JSONObject venue = jsonVenues.getJSONObject(id);

            //get venue details
            String roomName = venue.getString("roomName");
            Integer floor = venue.optInt("floor");

            Double locX = null;
            Double locY = null;
            JSONObject location = venue.optJSONObject("location");
            if (location != null) {
                locX = location.getDouble("x");
                locY = location.getDouble("y");
            }

            Venue newVenue = new Venue(id, roomName, floor, locX, locY);
            allVenues.add(newVenue);
        }
        Log.d("No. of Fetched Venues", String.valueOf(allVenues.size()));
        VenueList venueList = new VenueList();
        venueList.addAll(allVenues);
    }
}
