package com.example.mainpage.bus;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainpage.R;

import java.util.ArrayList;

public class BusLabelAdapter extends RecyclerView.Adapter<BusLabelAdapter.BusLabelViewHolder> {

    private Context context;
    private ArrayList<String> busCodesList;


    BusLabelAdapter(Context c, ArrayList<String> cList) {
        this.context = c;
        this.busCodesList = cList;
    }

    @NonNull
    @Override
    public BusLabelAdapter.BusLabelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.bus_result_buslabel_column, parent, false);
        return new BusLabelAdapter.BusLabelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BusLabelAdapter.BusLabelViewHolder holder, int position) {
        String label = busCodesList.get(position);

        holder.label.setText(label);
        holder.label.setBackgroundColor(Color.parseColor(this.getColour(label)));
    }

    @Override
    public int getItemCount() {
        return busCodesList.size();
    }

    class BusLabelViewHolder extends RecyclerView.ViewHolder {
        TextView label;

        BusLabelViewHolder(View v) {
            super(v);
            this.label = v.findViewById(R.id.busResultCode);
        }
    }

    String getColour(String busCode) {
        switch (busCode) {
            case "A1":
                return "#ffa84f";
            case "A2":
                return "#f86848";
            case "B1":
                return "#eac2f4";
            case "B2":
                return "#ff62ab";
            case "C":
                return "#ffd860";
            case "D1":
                return "#a1d5ec";
            default:
                return "#cade80";
        }
    }
}
