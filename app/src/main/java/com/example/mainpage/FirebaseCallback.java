package com.example.mainpage;

import com.example.mainpage.food.Food;
import com.example.mainpage.study.Library;
import com.example.mainpage.study.StudyFaculty;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface FirebaseCallback {
    void onFoodCallBack(ArrayList<Food> list);
    void onLibraryCallBack(ArrayList<Library> list);
    void onStudyCallBack(ArrayList<StudyFaculty> list);
}
