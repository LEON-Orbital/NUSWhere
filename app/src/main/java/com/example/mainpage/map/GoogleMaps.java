package com.example.mainpage.map;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.mainpage.MainActivity;
import com.example.mainpage.R;
import com.example.mainpage.bus.BusActivity;
import com.example.mainpage.food.FoodActivity;
import com.example.mainpage.study.StudyActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Collections;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class GoogleMaps extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {
    Location currentLocation;
    LatLng currentLatLng;
    LatLng latLngNUS;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    private GoogleMap mMap;
    Marker venueMarker;
    Boolean markerPresent = false; // to prevent null errors for the remove() method
    //SupportMapFragment mapFragment;
    double locX;
    double locY;
    String roomName;
    String floor;
    Boolean foundLocation = false; // check if the location is found alr or not

    TextView gMapsTopBar;
    SpinnerDialog spinnerDialog;
    ImageButton gMapsSearchButton;
    ImageButton gMapsLocationButton;
    CardView gMapsCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        ImageButton mapActivity = findViewById(R.id.mapBtn);
        mapActivity.setImageResource(R.drawable.map_button);
        
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();

        VenueList venueList = new VenueList();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        // set font for top bar
        gMapsTopBar = findViewById(R.id.gMapsTopBar);
        gMapsTopBar.setTypeface(ResourcesCompat.getFont(this, R.font.playfair_display));

        // get the list of venues for the search bar
        ArrayList<String> mapList = venueList.getVenueListString();
        Collections.sort(mapList);

        // button for search bar dialog
        gMapsSearchButton = findViewById(R.id.gMapsSearchButton);
        gMapsSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });
        gMapsTopBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });

        // create search bar spinner dialog box
        spinnerDialog = new SpinnerDialog(GoogleMaps.this, mapList,
                "Select venue", R.style.DialogAnimations_SmileWindow);
        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                if (markerPresent) {
                    venueMarker.remove();
                }

                Venue v = venueList.getFromID(item);
                if (v != null) {
                    locY = v.getLocY();
                    locX = v.getLocX();
                    roomName = v.getRoomName();
                    floor = v.getFloor().toString();
                    foundLocation = true;
                } else {
                    // if room not found, foundLocation remains false + Toast message
                    foundLocation = false;
                    roomName = "Location not found";
                    floor = "  ";
                    Toast.makeText(getApplicationContext(), "Location not found", Toast.LENGTH_SHORT).show();

                }

                TextView roomNameTV = findViewById(R.id.roomNameHere);
                TextView floorTV = findViewById(R.id.floorNumHere);
                roomNameTV.setText(roomName);
                floorTV.setText(floor);
                if (foundLocation) {
                    latLngNUS = new LatLng(locY, locX);
                    venueMarker = mMap.addMarker(new MarkerOptions().position(latLngNUS).title(item));
                    markerPresent = true;  // set markerPresent to true
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngNUS, 18));
                }
            }
        });

        // button to return to current location
        gMapsLocationButton = findViewById(R.id.gMapsLocationButton);
        gMapsLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 18));
            }
        });

        // cardview to return to searched location
        gMapsCardView = findViewById(R.id.gMapsCardView);
        gMapsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (foundLocation) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngNUS, 18));
                }
            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Searchable venues consist of currently used venues in NUS only.")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Does nothing, clicking it dismisses the message
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();


        ImageButton homeActivity = findViewById(R.id.homeBtn);
        ImageButton foodActivity = findViewById(R.id.foodBtn);
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);

        homeActivity.setOnClickListener(this);
        foodActivity.setOnClickListener(this);
        studyActivity.setOnClickListener(this);
        busActivity.setOnClickListener(this);
    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                currentLocation = location;
                SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                supportMapFragment.getMapAsync(GoogleMaps.this);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        currentLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(currentLatLng).title("Your Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap = googleMap;
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 18));
        googleMap.addMarker(markerOptions);                                                         // move to user's current location and set a marker
        //googleMap.setPadding(0, 150, 0, 0);                                           // left, top, right, bottom

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {  // for user location permission
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLastLocation();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.homeBtn:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case R.id.foodBtn:
                startActivity(new Intent(getApplicationContext(), FoodActivity.class));
                break;
            case R.id.studyBtn:
                startActivity(new Intent(getApplicationContext(), StudyActivity.class));
                break;
            case R.id.busBtn:
                startActivity(new Intent(getApplicationContext(), BusActivity.class));
                break;

        }
    }
}
