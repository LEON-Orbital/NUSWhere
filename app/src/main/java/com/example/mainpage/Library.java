package com.example.mainpage;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Library implements Parcelable {

    private String address;
    private ArrayList<String> images;
    private String mainimage;
    private String name;
    private String nearbyBusStops;
    private String opHoursSat;
    private String opHoursSemMonFri;
    private String opHoursSunPH;
    private String opHoursVacayMonFri;

    public Library(String address, ArrayList<String> images, String mainimage, String name, String nearbyBusStops, String opHoursSat, String opHoursSemMonFri, String opHoursSunPH, String opHoursVacayMonFri) {
        this.address = address;
        this.images = images;
        this.mainimage = mainimage;
        this.name = name;
        this.nearbyBusStops = nearbyBusStops;
        this.opHoursSat = opHoursSat;
        this.opHoursSemMonFri = opHoursSemMonFri;
        this.opHoursSunPH = opHoursSunPH;
        this.opHoursVacayMonFri = opHoursVacayMonFri;
    }

    /////////////GETTERS
    public String getAddress() {
        return address;
    }

    public ArrayList<String> getImages() { return images; }

    public String getMainimage() {
        return mainimage;
    }

    public String getName() {
        return name;
    }

    public String getNearbyBusStops() {
        return nearbyBusStops;
    }

    public String getOpHoursSemMonFri() {
        return opHoursSemMonFri;
    }
    public String getOpHoursSat() {
        return opHoursSat;
    }

    public String getOpHoursSunPH() {
        return opHoursSunPH;
    }

    public String getOpHoursVacayMonFri() {
        return opHoursVacayMonFri;
    }


    public String getOpHours() {
        return "Mon-Fri: Semester: " + this.opHoursSemMonFri + "\n"
                + "Vacation: " + this.opHoursVacayMonFri + "\n"
                + "Sat: " + this.opHoursSat + "\n"
                +"Sun/PH: " + this.opHoursSunPH;
    }

    ////////////////SETTERS
    public void setAddress(String address) {
        this.address = address;
    }

    public void addImage(String url) {
        images.add(url);
    }

    public void setMainimage(String mainimage) {
        this.mainimage = mainimage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNearbyBusStops(String nearbyBusStops) {
        this.nearbyBusStops = nearbyBusStops;
    }

    public void setOpHoursSat(String opHoursSat) {
        this.opHoursSat = opHoursSat;
    }

    public void setOpHoursSemMonFri(String opHoursSemMonFri) {
        this.opHoursSemMonFri = opHoursSemMonFri;
    }

    public void setOpHoursSunPH(String opHoursSunPH) {
        this.opHoursSunPH = opHoursSunPH;
    }

    public void setOpHoursVacayMonFri(String opHoursVacayMonFri) {
        this.opHoursVacayMonFri = opHoursVacayMonFri;
    }



    //////////////////PARCEL STUFF
    Library(Parcel source) {
        this.address = source.readString();
        this.images = source.createStringArrayList();
        this.mainimage = source.readString();
        this.name = source.readString();
        this.nearbyBusStops = source.readString();
        this.opHoursSat = source.readString();
        this.opHoursSemMonFri = source.readString();
        this.opHoursSunPH = source.readString();
        this.opHoursVacayMonFri = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeStringList(images);
        dest.writeString(mainimage);
        dest.writeString(name);
        dest.writeString(nearbyBusStops);
        dest.writeString(opHoursSat);
        dest.writeString(opHoursSemMonFri);
        dest.writeString(opHoursSunPH);
        dest.writeString(opHoursVacayMonFri);
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
