package com.example.mainpage;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FoodList {

    ArrayList<Food> foods = new ArrayList<Food>();
    DatabaseReference dbRef;

    public FoodList() {
        this.foods = foods;
    }

    public void add(Food f) {
        foods.add(f);
    }

    // for all food
    public ArrayList<Food> getAll() {
        Collections.sort(foods);
        return foods;
    }

    public ArrayList<Food> getFoodCourts() {
        return foods;
    }
    public ArrayList<Food> getFoodStalls() {
        return foods;
    }
    public ArrayList<Food> getRestaurants() {
        return foods;
    }
    public ArrayList<Food> getCafeBakeries() {
        return foods;
    }
    public ArrayList<Food> getLateNightFood() {
        return foods;
    }


}
