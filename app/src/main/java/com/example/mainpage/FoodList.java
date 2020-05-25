package com.example.mainpage;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class FoodList {

    private static ArrayList<Food> foodList = new ArrayList<>();

    FoodList() {}

    public void add(Food f) {
        foodList.add(f);
    }

    void addAll(ArrayList<Food> foods) {
        foodList.addAll(foods);
    }

    void replace(ArrayList<Food> foods) {
        foodList = foods;
    }

    ArrayList<Food> getByCategory(FoodCategory cat) {  // switch statement for categories
        ArrayList<Food> newFoodList = new ArrayList<>();
        switch (cat) {
            case ALL: newFoodList.addAll(foodList);

        }
        Collections.sort(newFoodList);
        return newFoodList;
    }


}
