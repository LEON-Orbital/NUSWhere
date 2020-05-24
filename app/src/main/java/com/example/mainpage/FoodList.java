package com.example.mainpage;

import android.content.Context;
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

    private ArrayList<Food> foods = new ArrayList<>();

    FoodList() {
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

    private ArrayList<Food> getByCategory(Category cat) {
        ArrayList<Food> newFoodList = new ArrayList<>();
        switch (cat) {
            case ALL: newFoodList.addAll(foods);
        }
        Collections.sort(newFoodList);
        return newFoodList;
    }


    void readData(final FirebaseCallback fbCallback, final Context context, final Category cat) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Food");
        ValueEventListener vel = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dS : dataSnapshot.getChildren()) {
                    Food f = dS.getValue(Food.class);
                    foods.add(f);
                }
                ArrayList<Food> foodList = getByCategory(cat);
                fbCallback.onCallBack(foodList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        };
        dbRef.addValueEventListener(vel);
    }

    enum Category {
        ALL,
        BUSINESS,
        COMPUTING,
        ENGINEERING,
        FASS,
        MEDICINE,
        SCIENCE,
        SDE,
        UTOWN,
        YSTCM,
        FOODCOURT,
        FOODSTALL,
        RESTAURANT,
        CAFEBAKERY,
        LATENIGHT }


}
