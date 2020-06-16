package com.example.mainpage.map;

import java.util.ArrayList;

public class VenueList {

    private static ArrayList<Venue> venueList = new ArrayList<>();

    public VenueList() {}

    public Venue get(int position) {
        return venueList.get(position);
    }

    public ArrayList<Venue> getVenueList() {
        return this.venueList;
    }

    public void add(Venue v) {
        venueList.add(v);
    }

    public void addAll(ArrayList<Venue> v) {
        venueList.addAll(v);
    }

    ArrayList<String> getAllVenueID() {
        ArrayList<String> idList = new ArrayList<>();
        for (Venue v : venueList) {
            idList.add(v.getId());
        }
        return idList;
    }
}
