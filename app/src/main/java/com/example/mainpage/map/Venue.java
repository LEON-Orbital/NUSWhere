package com.example.mainpage.map;

public class Venue implements Comparable<Venue> {

    String id;
    String roomName;
    Integer floor;
    Double locX; // longitude
    Double locY; // latitude

    public Venue(String id, String roomName, Integer floor, Double locX, Double locY) {
        this.id = id;
        this.roomName = roomName;
        this.floor = floor;
        this.locX = locX;
        this.locY = locY;
    }

    public String getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    public Integer getFloor() {
        return floor;
    }

    public Double getLocX() {
        return locX;
    }

    public Double getLocY() {
        return locY;
    }

    @Override
    public int compareTo(Venue o) {
        return this.getId().compareTo(o.getId());
    }
}

