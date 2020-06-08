package com.example.mainpage.study;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class StudySpot implements Parcelable {

    ArrayList<String> images;
    String location;
    String name;
    String nearbyBusStops;
    String opHours;
    Long seatingCap;

    public StudySpot(ArrayList<String> images, String location, String name, String nearbyBusStops, String opHours, Long seatingCap) {
        this.images = images;
        this.location = location;
        this.name = name;
        this.nearbyBusStops = nearbyBusStops;
        this.opHours = opHours;
        this.seatingCap = seatingCap;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getNearbyBusStops() {
        return nearbyBusStops;
    }

    public String getOpHours() {
        return opHours;
    }

    public Long getSeatingCap() {
        return seatingCap;
    }


    //////////////////// PARCEL STUFF

    StudySpot(Parcel source) {
        this.images = source.createStringArrayList();
        this.location = source.readString();
        this.name = source.readString();
        this.nearbyBusStops = source.readString();
        this.opHours = source.readString();
        this.seatingCap = source.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(images);
        dest.writeString(location);
        dest.writeString(name);
        dest.writeString(nearbyBusStops);
        dest.writeString(opHours);
        dest.writeLong(seatingCap);
    }

    public static final Creator<StudySpot> CREATOR = new Creator<StudySpot>() {
        @Override
        public StudySpot createFromParcel(Parcel in) {
            return new StudySpot(in);
        }

        @Override
        public StudySpot[] newArray(int size) {
            return new StudySpot[size];
        }
    };

}
