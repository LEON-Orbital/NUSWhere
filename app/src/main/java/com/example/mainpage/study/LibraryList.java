package com.example.mainpage.study;

import java.util.ArrayList;

public class LibraryList {

    private static ArrayList<Library> libraryList = new ArrayList<>();

    public LibraryList(){}

    public void add(Library lib) {
        libraryList.add(lib);
    }

    public void addAll(ArrayList<Library> list) {
        libraryList.addAll(list);
    }

    // FIND THE LIBRARY BASED ON THE LIBRARY NAME
    public Library getLibrary(String libName) {
        for (Library lib : libraryList) {
            if (lib.getName().equals(libName)){
                return lib;
            }
        }
        return null;
    }
}
