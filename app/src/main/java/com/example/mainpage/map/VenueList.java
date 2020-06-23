package com.example.mainpage.map;

import java.util.ArrayList;

public class VenueList {

    private static ArrayList<Venue> venueList = new ArrayList<>();

    public VenueList() {}

    public Venue get(int position) {
        return venueList.get(position);
    }

    public Venue getFromID(String itemId) {
        for (Venue v : venueList) {
            if (v.getId().equals(itemId)) {
                return v;
            }
        }
        return null;
    }

    public ArrayList<String> getVenueListString() {
        ArrayList<String> list = new ArrayList<>();
        for (Venue v :  venueList) {
            list.add(v.getId());
        }
        return list;
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
