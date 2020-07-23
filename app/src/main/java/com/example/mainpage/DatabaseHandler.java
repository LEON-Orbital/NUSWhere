package com.example.mainpage;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mainpage.bus.BusVenue;
import com.example.mainpage.food.Food;
import com.example.mainpage.study.Library;
import com.example.mainpage.study.StudyFaculty;
import com.example.mainpage.study.StudySpot;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

class DatabaseHandler {

    private ArrayList<Food> foodList = new ArrayList<>();
    private ArrayList<Library> libraryList = new ArrayList<>();
    private ArrayList<StudyFaculty> studyList = new ArrayList<>();
    private ArrayList<BusVenue> busVenueList = new ArrayList<>();

    void readFoodData(final FirebaseCallback fbCallback, final Context context) {
        DatabaseReference dbFoodRef = FirebaseDatabase.getInstance().getReference().child("Food");
        DatabaseReference dbLibRef = FirebaseDatabase.getInstance().getReference().child("Libraries");
        DatabaseReference dbStudyRef = FirebaseDatabase.getInstance().getReference().child("Study");
        DatabaseReference dbBusRef = FirebaseDatabase.getInstance().getReference().child("BusRoutes");


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
                Toast.makeText(context, "Could not initialise Food list", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, "Could not initialise Library list", Toast.LENGTH_SHORT).show();
            }
        };

        ValueEventListener velStudy = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dS : dataSnapshot.getChildren()) {

                    String image = (String) dS.child("image").getValue();
                    String name = (String) dS.child("name").getValue();
                    ArrayList<StudySpot> spotList = new ArrayList<>();

                      ///// CAN REMOVE THIS AFTER ALL ARE ADDED /// ###################################################################################################################################
                    if (name.equals("Arts and Social Sciences") ||
                            name.equals("Business") ||
                            name.equals("Computing") ||
                            name.equals("Medicine") ||
                            name.equals("Science") ||
                            name.equals("Utown")) {

                        for (DataSnapshot studyArea : dS.child("studyAreas").getChildren()) {

                            ArrayList<String> images = new ArrayList<>();
                            for (DataSnapshot imageURL : studyArea.child("images").getChildren()) {
                                images.add((String)imageURL.getValue());
                            }
                            String location = (String) studyArea.child("location").getValue();
                            String spotName = (String) studyArea.child("name").getValue();
                            String nearbyBusStops = (String) studyArea.child("nearbyBusStops").getValue();
                            String opHours = (String) studyArea.child("opHours").getValue();
                            Long seatingCap = (Long) studyArea.child("seatingCap").getValue();

                            StudySpot newStudySpot = new StudySpot(images, location, spotName, nearbyBusStops, opHours, seatingCap);
                            spotList.add(newStudySpot);
                        }
                    }
                    StudyFaculty newStudyFac = new StudyFaculty(image, name, spotList);
                    studyList.add(newStudyFac);
                }
                fbCallback.onStudyCallBack(studyList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Could not initialise Study list", Toast.LENGTH_SHORT).show();
            }
        };

        ValueEventListener velBus = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dS : dataSnapshot.getChildren()) {
                    BusVenue bv = dS.getValue(BusVenue.class);
                    busVenueList.add(bv);
                }
                Log.d("CheckDatabaseHandler", String.valueOf(busVenueList.size()));
                fbCallback.onBusCallBack(busVenueList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Could not initialise Bus Route list", Toast.LENGTH_SHORT).show();

            }
        };


        dbFoodRef.addValueEventListener(velFood);
        dbLibRef.addValueEventListener(velLibrary);
        dbStudyRef.addValueEventListener(velStudy);
        dbBusRef.addValueEventListener(velBus);
    }
}
