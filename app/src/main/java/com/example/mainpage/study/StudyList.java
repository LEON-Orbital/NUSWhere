package com.example.mainpage.study;

import java.util.ArrayList;

public class StudyList {

    private static ArrayList<StudyFaculty> studyList = new ArrayList<>();

    public StudyList(){}

    public StudyFaculty getFaculty(StudyNUSFaculties fac) {
        switch (fac) {
            case BIZ:
                return this.getByName("Business");
            case COM:
                return this.getByName("Computing");
            case DENT:
                return this.getByName("Dentistry");
            case ENGINE:
                return this.getByName("Engineering");
            case FASS:
                return this.getByName("Arts and Social Sciences");
            case MED:
                return this.getByName("Medicine");
            case SCI:
                return this.getByName("Science");
            case SDE:
                return this.getByName("Design and Environment");
            case YST:
                return this.getByName("Music");
            case UTOWN:
                return this.getByName("Utown");

        }
        return null;
    }

    private StudyFaculty getByName(String name) {
        for (StudyFaculty s : studyList) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    public void addAll(ArrayList<StudyFaculty> list) {
        if (!list.isEmpty()) {
            studyList.addAll(list);
        }
    }

    public boolean isEmpty() {
        return studyList.isEmpty();
    }

}
