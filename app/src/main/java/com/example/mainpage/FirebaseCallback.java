package com.example.mainpage;

import com.example.mainpage.food.Food;
import com.example.mainpage.study.Library;

import java.util.ArrayList;

public interface FirebaseCallback {
    void onFoodCallBack(ArrayList<Food> list);
    void onLibraryCallBack(ArrayList<Library> list);
}
