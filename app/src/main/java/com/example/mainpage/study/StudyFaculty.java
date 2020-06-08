package com.example.mainpage.study;

import java.util.ArrayList;

public class StudyFaculty {

    String image;
    String name;
    ArrayList<StudySpot> studyAreas;

    public StudyFaculty(String image, String name, ArrayList<StudySpot> studyAreas) {
        this.image = image;
        this.name = name;
        this.studyAreas = studyAreas;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public ArrayList<StudySpot> getStudyAreas() {
        return studyAreas;
    }

}
