package com.example.mainpage.bus;

import java.util.ArrayList;
import java.util.HashMap;

public class BusVenueList {

    private static ArrayList<BusVenue> busVenueList = new ArrayList<>();

    public BusVenueList(){}

    public void add(BusVenue bv) {
        busVenueList.add(bv);
    }

    public void addAll(ArrayList<BusVenue> bvList) {
        busVenueList.addAll(bvList);
    }

    public ArrayList<String> getLocations() {
        ArrayList<String> locList = new ArrayList<>();
        for (BusVenue bv :  busVenueList) {
            locList.add(bv.getA_location());
        }
        return locList;
    }

    public BusVenue getVenuesFromString(String name) {
        for (BusVenue bv : busVenueList) {
            if (bv.getA_location().equals(name)) {
                return bv;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return busVenueList.isEmpty();
    }
}
