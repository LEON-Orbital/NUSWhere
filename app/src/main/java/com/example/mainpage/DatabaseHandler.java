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

class DatabaseHandler {

    private ArrayList<Food> foodList = new ArrayList<>();

    void readData(final FirebaseCallback fbCallback, final Context context) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Food");
        ValueEventListener vel = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dS : dataSnapshot.getChildren()) {
                    Food f = dS.getValue(Food.class);
                    foodList.add(f);
                }
                fbCallback.onCallBack(foodList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        };
        dbRef.addValueEventListener(vel);
    }
}
