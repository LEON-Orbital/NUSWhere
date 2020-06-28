package com.example.mainpage.bus;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mainpage.R;
import com.example.mainpage.food.FoodActivity;
import com.example.mainpage.map.MapActivity;
import com.example.mainpage.study.StudyActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BusResultActivity extends AppCompatActivity implements View.OnClickListener {

    BusService busService = new BusService();

    String startResultText;
    String endResultText;
    String startBusHeader = "";
    String endBusHeader = "";
    String busCode = "";

    String STATE = ""; //(insert 12/23/2/123/1/3 here)

    ArrayList<String> resultsToPrint = new ArrayList<>();
    ArrayList<Long> timeToPrint = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_result);

        // get the text input by user
        startResultText = getIntent().getExtras().getString("first");
        endResultText = getIntent().getExtras().getString("second");

        // set the top bar text
        TextView startResult = findViewById(R.id.startResult);
        TextView endResult = findViewById(R.id.endResult);

        startResult.setText(startResultText);
        endResult.setText(endResultText);

        // get the BusVenue objects from the string
        BusVenueList bvList = new BusVenueList();
        BusVenue startRoute = bvList.getVenuesFromString(startResultText);
        BusVenue endRoute = bvList.getVenuesFromString(endResultText);

        // getOthers: get the start bus stop and end bus stop hashmap<string, string> ["start":"LT27", "end":"CLB", "bus":"A1"]
        // params: 2 BusVenues
        // returns: the bus code and the start and end bus stop
        HashMap<String, String> headers;
        if (startRoute == null || endRoute == null) {
            headers = new HashMap<>();
        } else {
            headers = busService.getOthers(startRoute, endRoute);
        }

        if (!headers.isEmpty()) {
            busCode = headers.get("bus");
            startBusHeader = headers.get("start");
            endBusHeader = headers.get("end");
            Log.d("BusResultActvy buscode", busCode);
        }

        /////////////// FOR THE MIDDLE BUS ROUTE CHUNK/////////////////
        // get a list of bus stops in between the two (excluding)
        List<BusStop> list = busService.getInBetween(busCode, startBusHeader, endBusHeader); // if NA, return empty list
        // get the list of names and total time --- put list into adapter??
        ArrayList<String> busServiceStopList = busService.getNames(list); // if NA, return empty list
        int totalTime = busService.getTotalTime(list); // if NA, return 0

        // get the relevant information to inflate
        if (!headers.isEmpty()) {
            this.changeState();
        }

        // add stuff to the arraylist based on state
        // if NA, do nothing
        if (STATE.equals("2")) {

            ///////////////////////////////////////////////////////////
            // add middle chunk
            resultsToPrint.add(startBusHeader);
            resultsToPrint.add(busCode);
            resultsToPrint.addAll(busServiceStopList);
            resultsToPrint.add(endBusHeader);
            ///////////////////////////////////////////////////////////

            timeToPrint.add((long) totalTime);

        }
        else if (STATE.equals("12")) {

            ///////////////////////////////////////////////////////////
            int startTime;
            // add top chunk
            resultsToPrint.add(startResultText);
            if (startRoute.getB_nearestBusStop().equals(startBusHeader)) {
                resultsToPrint.add(startRoute.getD_directions1());
                startTime = startRoute.getC_time();
            } else {
                resultsToPrint.add(startRoute.getG_directions2());
                startTime = startRoute.getF_time();
            }
            // add middle chunk
            resultsToPrint.add(startBusHeader);
            resultsToPrint.add(busCode);
            resultsToPrint.addAll(busServiceStopList);
            resultsToPrint.add(endBusHeader);
            ///////////////////////////////////////////////////////////

            timeToPrint.add((long) startTime);
            timeToPrint.add((long) totalTime);

        }
        else if (STATE.equals("23")) {

            ///////////////////////////////////////////////////////////
            // add middle chunk
            resultsToPrint.add(startBusHeader);
            resultsToPrint.add(busCode);
            resultsToPrint.addAll(busServiceStopList);
            resultsToPrint.add(endBusHeader);

            ///////////////////////////////////////////////////////////
            int endTime;
            //add bottom chunk
            if (endRoute.getB_nearestBusStop().equals(endBusHeader)) {
                resultsToPrint.add(endRoute.getD_directions1());
                endTime = endRoute.getC_time();
            } else {
                resultsToPrint.add(endRoute.getG_directions2());
                endTime = endRoute.getF_time();
            }
            resultsToPrint.add(endResultText);
            ///////////////////////////////////////////////////////////

            timeToPrint.add((long) totalTime);
            timeToPrint.add((long) endTime);

        }
        else if (STATE.equals("123")) {

            ///////////////////////////////////////////////////////////
            int startTime;
            // add top chunk
            resultsToPrint.add(startResultText);
            if (startRoute.getB_nearestBusStop().equals(startBusHeader)) {
                resultsToPrint.add(startRoute.getD_directions1());
                startTime = startRoute.getC_time();
            } else {
                resultsToPrint.add(startRoute.getG_directions2());
                startTime = startRoute.getF_time();
            }

            ///////////////////////////////////////////////////////////
            // add middle chunk
            resultsToPrint.add(startBusHeader);
            resultsToPrint.add(busCode);
            resultsToPrint.addAll(busServiceStopList);
            resultsToPrint.add(endBusHeader);

            ///////////////////////////////////////////////////////////
            int endTime;
            //add bottom chunk
            if (endRoute.getB_nearestBusStop().equals(endBusHeader)) {
                resultsToPrint.add(endRoute.getD_directions1());
                endTime = endRoute.getC_time();
            } else {
                resultsToPrint.add(endRoute.getG_directions2());
                endTime = endRoute.getF_time();
            }
            resultsToPrint.add(endResultText);
            ///////////////////////////////////////////////////////////

            timeToPrint.add((long) startTime);
            timeToPrint.add((long) totalTime);
            timeToPrint.add((long) endTime);

        }
        else if (STATE.equals("1")) {
            ///////////////////////////////////////////////////////////
            int startTime;
            // add top chunk
            resultsToPrint.add(startResultText);
            if (startRoute.getB_nearestBusStop().equals(startBusHeader)) {
                resultsToPrint.add(startRoute.getD_directions1());
                startTime = startRoute.getC_time();
            } else {
                resultsToPrint.add(startRoute.getG_directions2());
                startTime = startRoute.getF_time();
            }

            resultsToPrint.add(endResultText);

            ///////////////////////////////////////////////////////////

            timeToPrint.add((long) startTime);
        }
        else if (STATE.equals("3")) {
            ///////////////////////////////////////////////////////////
            resultsToPrint.add(startResultText);
            int endTime;
            //add bottom chunk
            if (endRoute.getB_nearestBusStop().equals(endBusHeader)) {
                resultsToPrint.add(endRoute.getD_directions1());
                endTime = endRoute.getC_time();
            } else {
                resultsToPrint.add(endRoute.getG_directions2());
                endTime = endRoute.getF_time();
            }
            resultsToPrint.add(endResultText);
            ///////////////////////////////////////////////////////////

            timeToPrint.add((long) endTime);
        }

        Log.d("checkResultsToPrint", resultsToPrint.toString());
        Log.d("checkTimeToPrint", timeToPrint.toString());

        // set total estimated time
        TextView busResultTotalTime = findViewById(R.id.busResultTotalTime);
        String timeText = timeToPrint.stream().mapToLong(Long::longValue).sum() + " Min";
        busResultTotalTime.setText(timeText);

        // set adapter to listview
        ListView busResultListView = findViewById(R.id.busResultListView);
        if (headers.isEmpty()) {
            Toast toast = Toast.makeText(BusResultActivity.this, "Could not find route", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 675);
            toast.show();
        } else {
            BusResultAdapter brAdapter = new BusResultAdapter(BusResultActivity.this, resultsToPrint, timeToPrint, STATE);
            busResultListView.setAdapter(brAdapter);
        }

        ///// IF new numBusStop == old numBusStop, return true and return multiple busCodes
        ///// (presumably will have the same routes so will be ok)


    }

    // check which positions to inflate view upon
    // ((( if (startResultText == startBusHeader), dont need the first two rows )))
    // 4 possible results:
    // 1) startResultText == startBusHeader && endResultText == endBusHeader: print chunk 2 only
    // 2) startResultText == startBusHeader: print chunk 2,3 only
    // 3) endResultText == endBusHeader: print chunk 1,2 only
    // 4) all diff: print chunk 1,2,3
    public void changeState() {
        if (startResultText.equals(startBusHeader)) {
            if (startBusHeader.equals(endBusHeader)) { // AKA if no need take bus
                Log.d("Changing State", "3");
                STATE = "3";
            } else if (endResultText.equals(endBusHeader)) {
                Log.d("Changing State", "2");
                STATE = "2";
            } else {
                Log.d("Changing State", "23");
                STATE = "23";
            }
        } else {
            if (startBusHeader.equals(endBusHeader)) { // AKA if no need take bus
                Log.d("Changing State", "1");
                STATE = "1";
            } else if (endResultText.equals(endBusHeader)) {
                Log.d("Changing State", "12");
                STATE = "12";
            } else {
                Log.d("Changing State", "123");
                STATE = "123";
            }
        }


        ImageButton backActivity = findViewById(R.id.backBtn);
        ImageButton foodActivity = findViewById(R.id.foodBtn);
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);
        ImageButton mapActivity = findViewById(R.id.mapBtn);

        backActivity.setOnClickListener(this);
        foodActivity.setOnClickListener(this);
        studyActivity.setOnClickListener(this);
        busActivity.setOnClickListener(this);
        mapActivity.setOnClickListener(this);
    }

    public String getState() {
        return STATE;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
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
            case R.id.mapBtn:
                startActivity(new Intent(getApplicationContext(), MapActivity.class));
                break;
        }
    }
}