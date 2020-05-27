package com.example.mainpage;

import java.util.ArrayList;

public class LibraryList {

    private static ArrayList<Library> libraryList = new ArrayList<>();

    LibraryList(){}

    public void add(Library lib) {
        libraryList.add(lib);
    }

    void addAll(ArrayList<Library> list) {
        libraryList.addAll(list);
    }
}
