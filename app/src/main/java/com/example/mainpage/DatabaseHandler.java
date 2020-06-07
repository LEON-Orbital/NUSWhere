package com.example.mainpage;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mainpage.food.Food;
import com.example.mainpage.study.Library;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

class DatabaseHandler {

    private ArrayList<Food> foodList = new ArrayList<>();
    private ArrayList<Library> libraryList = new ArrayList<>();

    void readFoodData(final FirebaseCallback fbCallback, final Context context) {
        DatabaseReference dbFoodRef = FirebaseDatabase.getInstance().getReference().child("Food");
        DatabaseReference dbLibRef = FirebaseDatabase.getInstance().getReference().child("Libraries");

        ValueEventListener velFood = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dS : dataSnapshot.getChildren()) {
                    Food f = dS.getValue(Food.class);
                    foodList.add(f);
                }
                fbCallback.onFoodCallBack(foodList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        };

        ValueEventListener velLibrary = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dS : dataSnapshot.getChildren()) {

                    String address = (String) dS.child("address").getValue();
                    ArrayList<String> images = new ArrayList<>();
                    for (DataSnapshot dSimages : dS.child("images").getChildren()) {
                        images.add( (String) dSimages.getValue());
                    }
                    String mainimage = (String) dS.child("mainimage").getValue();
                    String name = (String) dS.child("name").getValue();
                    String nearbyBusStops = (String) dS.child("nearbyBusStops").getValue();
                    String opHoursSat = (String) dS.child("opHoursSat").getValue();
                    String opHoursSemMonFri = (String) dS.child("opHoursSemMonFri").getValue();
                    String opHoursSunPH = (String) dS.child("opHoursSunPH").getValue();
                    String opHoursVacayMonFri = (String) dS.child("opHoursVacayMonFri").getValue();

                    Library newLib = new Library(address, images, mainimage, name, nearbyBusStops, opHoursSat, opHoursSemMonFri, opHoursSunPH, opHoursVacayMonFri);

                    libraryList.add(newLib);
                }
                fbCallback.onLibraryCallBack(libraryList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        };

        dbFoodRef.addValueEventListener(velFood);
        dbLibRef.addValueEventListener(velLibrary);
    }
}
