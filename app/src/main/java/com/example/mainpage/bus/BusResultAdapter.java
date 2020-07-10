package com.example.mainpage.bus;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainpage.R;


import java.util.ArrayList;

public class BusResultAdapter extends ArrayAdapter<String> {

    private Context context;
    ArrayList<String> busResultsList;
    ArrayList<String> busCodesList;
    ArrayList<Long> timeResultsList;
    String STATE;
    LayoutInflater inflater;

    public BusResultAdapter(Context c, ArrayList<String> bList, ArrayList<String> cList, ArrayList<Long> tList, String s) {
        super(c, R.layout.bus_result_header, bList);
        this.context = c;
        this.busResultsList = bList;
        this.busCodesList = cList;
        this.timeResultsList = tList;
        this.STATE = s;
        this.inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int pos, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView print;

        int lastIndex = busResultsList.size() - 1;
        String textToPrint;

        // print header
        // if state:2 @ pos 1,last;
        // or state:12 @ pos 0, 2, last;
        // or state:23 @ pos 0, last-2, last
        // or state:123 @ pos 0, 2, last-2, last
        // or state:1 @ pos 0, last
        // or state:3 @ pos 0, last
        if ( (STATE.equals("2") && (pos == 0 || pos == lastIndex)) ||
                (STATE.equals("12") && (pos ==  0 || pos == 2 || pos == lastIndex)) ||
                (STATE.equals("23") && (pos == 0 || pos == (lastIndex-2) || pos == lastIndex)) ||
                (STATE.equals("123") && (pos == 0 || pos == 2 || pos == (lastIndex-2) || pos == lastIndex)) ||
                (STATE.equals("1") && (pos == 0 || pos == lastIndex)) ||
                (STATE.equals("3")&& (pos == 0 || pos == lastIndex)) )
        {
            convertView = inflater.inflate(R.layout.bus_result_header, parent, false);
            print = convertView.findViewById(R.id.busResultHeader);

            textToPrint = busResultsList.get(pos);
        }

        // print directions
        // if state:12 @ pos 1;
        // or state:23 @ pos last-1
        // or state:123 @ pos 1, last-1
        // or state:1 @ pos 1
        // or state:3 @ pos 1
        else if ( (STATE.equals("12") && pos == 1) ||
                (STATE.equals("23") && pos == (lastIndex-1)) ||
                (STATE.equals("123") && (pos == 1 || pos == (lastIndex-1))) ||
                (STATE.equals("1") && pos == 1) ||
                (STATE.equals("3") && pos == 1))
        {
            convertView = inflater.inflate(R.layout.bus_result_directions, parent, false);
            print = convertView.findViewById(R.id.busResultDirection);

            textToPrint = busResultsList.get(pos);

            // print the time label
            TextView time = convertView.findViewById(R.id.busResultTime1);
            String timeText = "";
            if (pos == 1) {
                timeText = timeResultsList.get(0) + " Min";
            } else if (pos == (lastIndex-1)) {
                timeText = timeResultsList.get(timeResultsList.size()-1) + " Min";
            }
            time.setText(timeText);
        }

        // print coloured bus label
        // if state:2 @ pos 1
        // or state:12 @ pos 3
        // or state:23 @ pos 1
        // or state:123 @ pos 3
        else if ( ((STATE.equals("2") || STATE.equals("23")) && (pos == 1)) ||
                ((STATE.equals("12") || STATE.equals("123")) && (pos == 3)) )
        {
            convertView = inflater.inflate(R.layout.bus_result_buslabel, parent, false);
            RecyclerView busLabelRcView = convertView.findViewById(R.id.busCodeRcView);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            busLabelRcView.setLayoutManager(layoutManager);

            BusLabelAdapter busLabelAdapter = new BusLabelAdapter(context, busCodesList);
            busLabelRcView.setAdapter(busLabelAdapter);

            print = convertView.findViewById(R.id.busResultPlaceholder);
            //print.setBackgroundColor(Color.parseColor(this.getColour(busResultsList.get(pos))));
            textToPrint = "";


            // print the time label
            TextView time = convertView.findViewById(R.id.busResultTime2);
            String timeText = "";
            if (pos == 1) {
                timeText = timeResultsList.get(0) + " Min";
            } else {
                timeText = timeResultsList.get(1) + " Min";
            }
            time.setText(timeText);
        }

        // else print the bus stop
        else {
            convertView = inflater.inflate(R.layout.bus_result_busstop, parent, false);
            print = convertView.findViewById(R.id.busResultStop);

            textToPrint = busResultsList.get(pos);
        }

        convertView.setOnClickListener(null);
        print.setText(textToPrint);

        return convertView;
    }


}
