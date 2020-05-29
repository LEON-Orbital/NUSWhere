package com.example.mainpage;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Library implements Parcelable {

    private String address = "";
    private ArrayList<String> images = new ArrayList<>();
    private String name = "";
    private String nearbyBusStops = "";
    private String opHoursMonFri = "";
    private String opHoursSat = "";
    private String opHoursSunPH = "";
    private String vacationOpHours = "";

    public Library(String address, ArrayList<String> images, String name, String nearbyBusStops, String opHoursMonFri, String opHoursSat, String opHoursSunPH, String vacationOpHours) {
        this.address = address;
        this.images = images;
        this.name = name;
        this.nearbyBusStops = nearbyBusStops;
        this.opHoursMonFri = opHoursMonFri;
        this.opHoursSat = opHoursSat;
        this.opHoursSunPH = opHoursSunPH;
        this.vacationOpHours = vacationOpHours;
    }

    /////////////GETTERS
    public String getAddress() {
        return address;
    }

    public ArrayList<String> getImages() { return images; }

    public String getName() {
        return name;
    }

    public String getNearbyBusStops() {
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

    ////////////////SETTERS
    public void setAddress(String address) {
        this.address = address;
    }

    public void addImage(String url) {
        images.add(url);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNearbyBusStops(String nearbyBusStops) {
        this.nearbyBusStops = nearbyBusStops;
    }

    public void setOpHoursMonFri(String opHoursMonFri) {
        this.opHoursMonFri = opHoursMonFri;
    }

    public void setOpHoursSat(String opHoursSat) {
        this.opHoursSat = opHoursSat;
    }

    public void setOpHoursSunPH(String opHoursSunPH) {
        this.opHoursSunPH = opHoursSunPH;
    }

    public void setVacationOpHours(String vacationOpHours) {
        this.vacationOpHours = vacationOpHours;
    }


    //////////////////PARCEL STUFF
    Library(Parcel source) {
        this.address = source.readString();
        this.images = source.createStringArrayList();
        this.name = source.readString();
        this.nearbyBusStops = source.readString();
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
        dest.writeString(nearbyBusStops);
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
