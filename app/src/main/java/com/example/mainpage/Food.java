package com.example.mainpage;

public class Food implements Comparable<Food> {

    private String id;
    private String name;
    private String location;
    private String type;
    private String faculty;
    private String termOperatingHours;
    private String vacationOperatingHours;
    private String halalCertified;
    private String image;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getTermOperatingHours() {
        return termOperatingHours;
    }

    public String getVacationOperatingHours() {
        return vacationOperatingHours;
    }

    public String getHalalCertified() {
        return halalCertified;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int compareTo(Food f) {
        return this.getName().compareTo(f.getName());
    }

}

