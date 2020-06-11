package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mainpage.API.NUSModsAPI;
import com.example.mainpage.bus.BusActivity;
import com.example.mainpage.food.Food;

import java.io.IOException;
import java.util.ArrayList;

import com.example.mainpage.food.FoodActivity;
import com.example.mainpage.food.FoodList;
import com.example.mainpage.map.GoogleMaps;
import com.example.mainpage.map.MapActivity;
import com.example.mainpage.study.Library;
import com.example.mainpage.study.LibraryList;
import com.example.mainpage.study.StudyActivity;
import com.example.mainpage.study.StudyFaculty;
import com.example.mainpage.study.StudyList;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    FoodList foodList;
    LibraryList libraryList;
    StudyList studyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialises classes with static variables
        foodList = new FoodList();
        libraryList = new LibraryList();
        studyList = new StudyList();

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
        }, MainActivity.this);


        ImageButton foodActivity = findViewById(R.id.foodBtn);
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);
        ImageButton mapActivity = findViewById(R.id.mapBtn);

        foodActivity.setOnClickListener(this);
        studyActivity.setOnClickListener(this);
        busActivity.setOnClickListener(this);
        mapActivity.setOnClickListener(this);
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
                new JSONTask().execute();
                // **AFTER CHANGING OUR ONPOSTEXECUTE METHOD** switch screen while app is fetching venues in the background
                // startActivity(new Intent(MainActivity.this, MapActivity.class));
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
            // BUT FOR NOW bc we haven't done anything with the lat and long yet.
            startActivity(new Intent(MainActivity.this, MapActivity.class));
            //insert a setLocationMarker method here or smth
        }
    }
}

