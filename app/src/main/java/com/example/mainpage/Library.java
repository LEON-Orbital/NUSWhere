package com.example.mainpage;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Library implements Parcelable {
    private String address;
    private ArrayList<String> images;
    private String name;
    private ArrayList<String> nearbyBusStops;
    private String opHoursMonFri;
    private String opHoursSat;
    private String opHoursSunPH;
    private String vacationOpHours;

    Library(){}

    public String getAddress() {
        return address;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getNearbyBusStops() {
        return nearbyBusStops;
    }

    public String getOpHoursMonFri() {
        return opHoursMonFri;
    }

    public String getOpHoursSat() {
        return opHoursSat;
    }

    public String getOpHoursSunPH() {
        return opHoursSunPH;
    }

    public String getVacationOpHours() {
        return vacationOpHours;
    }

    Library(Parcel source) {
        this.address = source.readString();
        this.images = source.createStringArrayList();
        this.name = source.readString();
        this.nearbyBusStops = source.createStringArrayList();
        this.opHoursMonFri = source.readString();
        this.opHoursSat = source.readString();
        this.opHoursSunPH = source.readString();
        this.vacationOpHours = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeStringList(images);
        dest.writeString(name);
        dest.writeStringList(nearbyBusStops);
        dest.writeString(opHoursMonFri);
        dest.writeString(opHoursSat);
        dest.writeString(opHoursSunPH);
        dest.writeString(vacationOpHours);
    }

    public static final Creator<Library> CREATOR = new Creator<Library>() {
        @Override
        public Library createFromParcel(Parcel in) {
            return new Library(in);
        }

        @Override
        public Library[] newArray(int size) {
            return new Library[size];
        }
    };

}
