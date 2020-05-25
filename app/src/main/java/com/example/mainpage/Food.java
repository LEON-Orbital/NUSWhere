package com.example.mainpage;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Comparable<Food>, Parcelable {

    private String id;
    private String name;
    private String location;
    private String type;
    private String faculty;
    private String termOperatingHours;
    private String vacationOperatingHours;
    private String halalCertified;
    private String image;

    Food() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getFaculty() {
        return faculty;
    }

    String getTermOperatingHours() {
        return termOperatingHours;
    }

    String getVacationOperatingHours() {
        return vacationOperatingHours;
    }

    String getHalalCertified() {
        return halalCertified;
    }

    String getImage() {
        return image;
    }

    @Override
    public int compareTo(Food f) {
        return this.getName().compareTo(f.getName());
    }

    // implement parcelable stuff
    public Food(Parcel source) {
        this.id = source.readString();
        this.name = source.readString();
        this.location = source.readString();
        this.type = source.readString();
        this.faculty = source.readString();
        this.termOperatingHours = source.readString();
        this.vacationOperatingHours = source.readString();
        this.halalCertified = source.readString();
        this.image = source.readString();
    }

    // from parcelable interface
    @Override
    public int describeContents() {
        return 0;
    }

    //from parcelable interface
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(type);
        dest.writeString(faculty);
        dest.writeString(termOperatingHours);
        dest.writeString(vacationOperatingHours);
        dest.writeString(halalCertified);
        dest.writeString(image);
    }

    // all parcelables must have a creator that implements these two methods
    public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>() {
        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }

        @Override
        public Food createFromParcel(Parcel source) {
            return new Food(source);
        }
    };

}

