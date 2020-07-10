package com.example.mainpage.bus;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BusService {

    private static HashMap<String, ArrayList<BusStop>> busRoutes = new HashMap<>();
    private ArrayList<String> busCodes = new ArrayList<>();

    public BusService() {}

    public void put(String id, ArrayList<BusStop> bsList) {
        busRoutes.put(id, bsList);
    }

    public void add(String id, BusStop bs) {
        Objects.requireNonNull(busRoutes.get(id)).add(bs);
    }

    public HashMap<String, ArrayList<BusStop>> getBusRoutes() {
        return busRoutes;
    }

    public BusStop getByName(String id, String name) {
        for (BusStop bs : Objects.requireNonNull(busRoutes.get(id))) {
            if (bs.getName().equals(name)) {
                return bs;
            }
        }
        return null;
    }

    public List<BusStop> getInBetween(String id, String start, String end) {
        if (id.equals("")) {
            return new ArrayList<>();
        }

        ArrayList<BusStop> list = busRoutes.get(id);

        ArrayList<String> nameList = this.getNames(list);
        int first = nameList.indexOf(start);
        int last = nameList.indexOf(end);

        if (first == last) {
            return new ArrayList<>();
        }
        return list.subList(first+1, last);
    }

    public ArrayList<String> getNames(List<BusStop> bsList) {
        ArrayList<String> result = new ArrayList<>();
        for (BusStop bs : bsList) {
            result.add(bs.getName());
        }
        return result;
    }

    public int getTotalTime(List<BusStop> list) {
        int result = 0;
        for (BusStop bs : list) {
            result += bs.getTime();
        }
        return result;
    }

    public boolean passed(String id, ArrayList<String> list, String nearestStart, String nearestEnd, long numOfStops) {

        Log.d("BusService: checkPASSED", String.valueOf(numOfStops));

        // two criterias to fulfil:
        // 1) both bus stops must be in the list
        // 2) the end bus stop comes after the start bus stop

        // check 1)
        if (list.contains(nearestStart) && list.contains(nearestEnd)) {
            // check 2)
            Long startNo = this.getByName(id, nearestStart).getBusStopNo();
            Long endNo = this.getByName(id, nearestEnd).getBusStopNo();
            if (startNo <= endNo) {
                if ((endNo - startNo) <= numOfStops) {
                    Log.d("BusService: checkPASSED", list.toString());
                    return true;
                }
            }
        }
        Log.d("BusService: checkFAILED", list.toString());
        return false;
    }

    public HashMap<String, String> getOthers(BusVenue startBS, BusVenue endBS) {

        // list of the bus codes that can be taken
        busCodes.clear();

        HashMap<String, String> results = new HashMap<>();

        // get the nearest bus stops for both the input start and end locations
        String nearestStart1 = startBS.getB_nearestBusStop();
        String nearestStart2 = startBS.getE_nearestBusStop2();
        String nearestEnd1 = endBS.getB_nearestBusStop();
        String nearestEnd2 = endBS.getE_nearestBusStop2();
        Log.d("BusService: Dest: ", nearestEnd1);

        String nearestStart = "";
        String nearestEnd = "";

        long numOfStops = Integer.MAX_VALUE;
        boolean done = false;

        for (Map.Entry<String, ArrayList<BusStop>> entry : busRoutes.entrySet()) {
            String id = entry.getKey();
            ArrayList<String> list = this.getNames(entry.getValue());
            Log.d("BusService: Bus Code", id);

            // pass the bus id ("A1"), the nearestStart ("LT27") and the nearestEnd ("OppUHC")
            // the method checks if the provided bus stops pass the criterias
            if (this.passed(id, list, nearestStart1, nearestEnd1, numOfStops)) {

                numOfStops = this.getByName(id, nearestEnd1).getBusStopNo() - this.getByName(id, nearestStart1).getBusStopNo();
                done = true;
                nearestStart = nearestStart1;
                nearestEnd = nearestEnd1;

            } else if (this.passed(id, list, nearestStart1, nearestEnd2, numOfStops)){

                numOfStops = this.getByName(id, nearestEnd2).getBusStopNo() - this.getByName(id, nearestStart1).getBusStopNo();
                done = true;
                nearestStart = nearestStart1;
                nearestEnd = nearestEnd2;

            } else if (this.passed(id, list, nearestStart2, nearestEnd1, numOfStops)) {

                numOfStops = this.getByName(id, nearestEnd1).getBusStopNo() - this.getByName(id, nearestStart2).getBusStopNo();
                done = true;
                nearestStart = nearestStart2;
                nearestEnd = nearestEnd1;

            } else if (this.passed(id, list, nearestStart2, nearestEnd2, numOfStops)) {

                numOfStops = this.getByName(id, nearestEnd2).getBusStopNo() - this.getByName(id, nearestStart2).getBusStopNo();
                done = true;
                nearestStart = nearestStart2;
                nearestEnd = nearestEnd2;

            } else {
                done = false;
            }

            if (done) {
                results.put("start", nearestStart);
                results.put("end", nearestEnd);

                if (id.length() > 2) {
                    id = id.substring(0, 2);
                }

                if (!busCodes.contains(id)) {
                    busCodes.add(id);
                }
                // results.put("bus", id);
            }
        }
        return results;
    }

    public ArrayList<String> getBusCodes() {
        return busCodes;
    }
}