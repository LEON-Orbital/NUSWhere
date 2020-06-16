package com.example.mainpage.map;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.mainpage.R;
import com.example.mainpage.bus.BusActivity;
import com.example.mainpage.food.FoodActivity;
import com.example.mainpage.study.StudyActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoogleMaps extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener{
    LocationManager locationManager;
    LocationListener locationListener;
    LatLng userLatLong;

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;


    private GoogleMap mMap;
    Marker venuneMarker;
    Boolean markerPresent = false; // to prevent null errors for the remove() method
    SupportMapFragment mapFragment;
    SearchView searchView;
    double locX;
    double locY;
    String roomName;
    String floor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        ImageButton backActivity = findViewById(R.id.backBtn);
        ImageButton foodActivity = findViewById(R.id.foodBtn);
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);
        ImageButton mapActivity = findViewById(R.id.busRouteButton);
        mapActivity.setOnClickListener(this);
        backActivity.setOnClickListener(this);
        foodActivity.setOnClickListener(this);
        studyActivity.setOnClickListener(this);
        busActivity.setOnClickListener(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();

        VenueList venueList = new VenueList();

        searchView = findViewById(R.id.sv_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (markerPresent == true) {
                    venuneMarker.remove();
                }

                String location = searchView.getQuery().toString().toLowerCase();
                //List<Address> addressList = null;

                if (location != null || !location.equals("")) {
                    //Geocoder geocoder = new Geocoder(GoogleMaps.this);
                    //addressList = geocoder.getFromLocationName(location, 1);

                    for (Venue v : venueList.getVenueList()) {   // search for matching venueId from the API
                        if (location.equals(v.getId().toLowerCase())) {
                            locY = v.getLocY();
                            locX = v.getLocX();
                            roomName = v.getRoomName();
                            floor = v.getFloor().toString();
                            break;
                        }
                    }
/*
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));*/

                    TextView roomNameTV = findViewById(R.id.roomNameHere);
                    TextView floorTV = findViewById(R.id.floorNumHere);
                    roomNameTV.setText(roomName);
                    floorTV.setText(floor);
                    LatLng latLngNUS = new LatLng(locY, locX);
                    venuneMarker = mMap.addMarker(new MarkerOptions().position(latLngNUS).title(location));
                    markerPresent = true;  // set markerPresent to true
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngNUS, 18));

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mapFragment.getMapAsync(this);
    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    mapFragment.getMapAsync(GoogleMaps.this);
                }
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        /*
        LatLng currentLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(currentLatLng).title("Your Location");
        mMap = googleMap;
        mMap.animateCamera(CameraUpdateFactory.newLatLng(currentLatLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
        mMap.addMarker(markerOptions);
*/

        CameraUpdate defaultView = CameraUpdateFactory.newLatLngZoom(new LatLng(1.2966, 103.7764),15);
        mMap = googleMap;
        mMap.moveCamera(defaultView);  // set default view for the gMaps to be NUS


/*
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // store user latlong
                userLatLong = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(userLatLong).title("Your Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(userLatLong));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        // asking for map permission with user / location permission
        //askLocationPermission();*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLastLocation();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            //// NAV BAR ////
            case R.id.backBtn:
                finish();
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

            //// MAP BUTTONS ////
            case R.id.busRouteButton:
                startActivity(new Intent(getApplicationContext(), MapActivity.class));
                break;

        }
    }
}
