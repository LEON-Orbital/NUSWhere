package com.example.mainpage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.mainpage.API.NUSModsAPI;
import com.example.mainpage.bus.BusActivity;
import com.example.mainpage.bus.BusVenue;
import com.example.mainpage.bus.BusVenueList;
import com.example.mainpage.food.Food;
import com.example.mainpage.food.FoodActivity;
import com.example.mainpage.food.FoodList;
import com.example.mainpage.map.GoogleMaps;
import com.example.mainpage.study.Library;
import com.example.mainpage.study.LibraryList;
import com.example.mainpage.study.StudyActivity;
import com.example.mainpage.study.StudyFaculty;
import com.example.mainpage.study.StudyList;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.mainpage.Constants.ERROR_DIALOG_REQUEST;
import static com.example.mainpage.Constants.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;
import static com.example.mainpage.Constants.PERMISSIONS_REQUEST_ENABLE_GPS;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    FoodList foodList;
    LibraryList libraryList;
    StudyList studyList;
    BusVenueList busVenueList;

    private static final String TAG = "";
    private boolean mLocationPermissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialises classes with static variables
        foodList = new FoodList();
        libraryList = new LibraryList();
        studyList = new StudyList();
        busVenueList = new BusVenueList();


        // retrieves the data and stores it inside static variables
        new DatabaseHandler().readFoodData(new FirebaseCallback() {
            @Override
            public void onFoodCallBack(ArrayList<Food> list) {
                foodList.addAll(list);  // adds all the food data into the static variable in FoodList class
            }

            @Override
            public void onLibraryCallBack(ArrayList<Library> list) {
                libraryList.addAll(list);
            }

            @Override
            public void onStudyCallBack(ArrayList<StudyFaculty> list) {
                studyList.addAll(list);
            }

            @Override
            public void onBusCallBack(ArrayList<BusVenue> list) {
                busVenueList.addAll(list);
            }
        }, MainActivity.this);

        //  CHECK FOR INTERNET CONNECTION HERE
        ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED    // It has to be single | not double || else java wont check the second condition !!
                | conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ) {
            // do nothing if user is online
            // CHECK IF LOCATION PERMISSION IS GRANTED
            if (checkMapServices()) {
                if (mLocationPermissionGranted) {
                    // do nothing if permission granted
                } else {
                    getLocationPermission();
                }
            }

            new JSONTask().execute();
        }
        else if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                | conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("This application requires internet to work, please ensure that your internet is on.")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Does nothing, clicking it dismisses the message
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();
        }
/*
        // CHECK IF LOCATION PERMISSION IS GRANTED
        if (checkMapServices()) {
            if (mLocationPermissionGranted) {
                // do nothing if permission granted
            } else {
                getLocationPermission();
            }
        }

        new JSONTask().execute();*/

        ImageButton foodActivity = findViewById(R.id.foodBtn);
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);
        ImageButton mapActivity = findViewById(R.id.mapBtn);

        foodActivity.setOnClickListener(this);
        studyActivity.setOnClickListener(this);
        busActivity.setOnClickListener(this);
        mapActivity.setOnClickListener(this);
    }

    public boolean checkMapServices(){
        if(isServicesOK()){
            if(isMapsEnabled()){
                return true;
            }
        }
        return false;
    }

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occurred but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Map feature requires GPS to work properly, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        Intent enableGpsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(enableGpsIntent, PERMISSIONS_REQUEST_ENABLE_GPS);
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean isMapsEnabled(){
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
            return false;
        }
        return true;
    }

    public void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            // DO SOME STUFF HERE
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: called.");
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ENABLE_GPS: {
                if(!mLocationPermissionGranted){
                    getLocationPermission();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.foodBtn:
                startActivity(new Intent(getApplicationContext(), FoodActivity.class));
                break;
            case R.id.studyBtn:
                startActivity(new Intent(getApplicationContext(), StudyActivity.class));
                break;
            case R.id.busBtn:
                startActivity(new Intent(getApplicationContext(), BusActivity.class));
                break;
            case R.id.mapBtn:
                // task will execute in the background and do the OnPostExecute stuff when its done
                //new JSONTask().execute();
                // **AFTER CHANGING OUR ONPOSTEXECUTE METHOD** switch screen while app is fetching venues in the background
                startActivity(new Intent(MainActivity.this, GoogleMaps.class));
                break;
        }
    }

    public class JSONTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                NUSModsAPI.fetchVenues();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // this method performs an action after the rest of the async task is done.
            // right now its opening the map activity only after all venue data has been loaded.
            // but that takes long so preferably we will insert the relevant method below
            // BUT FOR NOW I did this bc we haven't done anything with the lat and long yet.
            //startActivity(new Intent(MainActivity.this, GoogleMaps.class));
            //insert a setLocationMarker method here or smth
        }
    }
}

