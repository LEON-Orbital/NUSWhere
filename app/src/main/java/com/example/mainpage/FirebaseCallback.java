package com.example.mainpage;

import java.util.ArrayList;

public interface FirebaseCallback {
    void onFoodCallBack(ArrayList<Food> list);
    void onLibraryCallBack(ArrayList<Library> list);
}
