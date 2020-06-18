package com.example.mainpage.bus;

public class BusStop implements Comparable<BusStop> {

    String name;
    Long busStopNo;
    Long time;

    public BusStop(String name, Long busStopNo, Long time) {
        this.name = name;
        this.busStopNo = busStopNo;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Long getBusStopNo() {
        return busStopNo;
    }

    public Long getTime() {
        return time;
    }

    @Override
    public int compareTo(BusStop bs) {
        long diff = this.busStopNo - bs.getBusStopNo();
        return (int) diff;
    }
}
