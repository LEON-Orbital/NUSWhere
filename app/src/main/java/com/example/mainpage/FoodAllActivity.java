package com.example.mainpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class FoodAllActivity extends AppCompatActivity {

    RecyclerView rcView;
    DatabaseReference dbRef;
    FoodList foodList;
    FoodListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_all);

        rcView = (RecyclerView) findViewById(R.id.allFoodRecView);
        rcView.setLayoutManager(new LinearLayoutManager(this));


        readData(new FirebaseCallback() {
            @Override
            public void onCallBack(ArrayList<Food> list) {
                adapter = new FoodListAdapter(FoodAllActivity.this, list);
                rcView.setAdapter(adapter);
            }
        });
    }

    private void readData(final FirebaseCallback fbCallback) {
        dbRef = FirebaseDatabase.getInstance().getReference().child("Food");
        foodList = new FoodList();
        ValueEventListener vel = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dS : dataSnapshot.getChildren()) {
                    Food f = dS.getValue(Food.class);
                    foodList.add(f);
                }
                fbCallback.onCallBack(foodList.getAll());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(FoodAllActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        };
        dbRef.addValueEventListener(vel);
    }


    private interface FirebaseCallback {
        void onCallBack(ArrayList<Food> list);
    }

}
