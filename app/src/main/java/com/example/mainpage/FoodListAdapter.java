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

import java.util.ArrayList;


public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder> {

    Context context;
    ArrayList<Food> foods;

    public FoodListAdapter(Context c, ArrayList<Food> f) {
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
        String desc = "";

        desc += "Location: " + foods.get(position).getLocation() + "\n";
        desc +=  "Halal Certified: " + foods.get(position).getHalalCertified() + "\n";

        String termTime = foods.get(position).getTermOperatingHours();
        String vacationTime = foods.get(position).getVacationOperatingHours();
        String image = foods.get(position).getImage();

        if (termTime.equals(vacationTime)) {
            desc += "Term Time & Vacation Operating Hours: " + "\n" +  termTime;
        } else {
            desc += "Term Time Operating Hours: " + "\n"
                    + termTime + "\n"
                    + "Vacation Time Operating Hours: " + "\n"
                    + vacationTime;
        }

        holder.title.setText(title);
        holder.desc.setText(desc);
        Picasso.get().load(image).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }


    class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        ImageView image;

        public FoodViewHolder(View v) {
            super(v);
            this.title = (TextView) v.findViewById(R.id.foodTitleId);
            this.desc = (TextView) v.findViewById(R.id.foodDescId);
            this.image = (ImageView) v.findViewById(R.id.foodImageView);
        }
    }


}
