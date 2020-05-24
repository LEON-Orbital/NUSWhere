package com.example.mainpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder> {

    private Context context;
    private ArrayList<Food> foods;

    FoodListAdapter(Context c, ArrayList<Food> f) {
        this.context = c;
        this.foods = f;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodViewHolder(LayoutInflater.from(context).inflate(R.layout.food_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        String title = foods.get(position).getName();
        String location = "Location: " + foods.get(position).getLocation();
        String halal =  "Halal Certified: " + foods.get(position).getHalalCertified();
        String termTime = foods.get(position).getTermOperatingHours();
        String vacationTime = foods.get(position).getVacationOperatingHours();
        String image = foods.get(position).getImage();

        String opHours;
        if (termTime.equals(vacationTime)) {
            opHours = "Term Time & Vacation Operating Hours: " + "\n" +  termTime;
        } else {
            opHours = "Term Time Operating Hours: " + "\n"
                    + termTime + "\n"
                    + "Vacation Time Operating Hours: " + "\n"
                    + vacationTime;
        }

        holder.title.setText(title);
        holder.location.setText(location);
        holder.halal.setText(halal);
        holder.operatingHours.setText(opHours);
        Picasso.get().load(image).fit().into(holder.image);

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }


    static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView location;
        TextView halal;
        TextView operatingHours;
        ImageView image;

        FoodViewHolder(View v) {
            super(v);
            this.title = v.findViewById(R.id.foodTitleId);
            this.location = v.findViewById(R.id.foodLocationId);
            this.halal =  v.findViewById(R.id.foodHalalId);
            this.operatingHours = v.findViewById(R.id.foodOperatingHoursId);
            this.image = v.findViewById(R.id.foodImageView);
        }
    }


}
