package com.example.mainpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder> {

    private Context context;
    private ArrayList<Food> foods;
    private OnItemClickListener mListener;
    private boolean expanded = false;
    private int expandedPosition = -1;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    FoodListAdapter(Context c, ArrayList<Food> f) {
        this.context = c;
        this.foods = f;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.food_row, parent, false);
        return new FoodViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        String image = foods.get(position).getImage();
        String title = foods.get(position).getName();
        String location = foods.get(position).getLocation();
        String halal =  "Halal Certified: " + foods.get(position).getHalalCertified();
        String termTime = foods.get(position).getTermOperatingHours();
        String vacationTime = foods.get(position).getVacationOperatingHours();

        String opHours;
        String opHours2;
        if (termTime.equals(vacationTime)) {
            opHours = "Term Time & Vacation Operating Hours: " + "\n" +  termTime;
            opHours2 = "";
        } else {
            opHours = "Term Time Operating Hours: " + "\n"
                    + termTime;
            opHours2 = "Vacation Time Operating Hours: " + "\n"
                    + vacationTime;
        }

        Picasso.get().load(image).fit().into(holder.image);
        holder.title.setText(title);
        holder.title2.setText(title);
        holder.location.setText(location);
        holder.location2.setText(location);
        holder.halal.setText(halal);
        holder.operatingHours.setText(opHours);
        holder.operatingHours2.setText(opHours2);

        if (expanded) {
            holder.foodShowMoreCard.setVisibility(View.VISIBLE);
        } else {
            holder.foodShowMoreCard.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }


    static class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView location;
        TextView halal;
        TextView operatingHours;
        TextView operatingHours2;
        TextView title2;
        TextView location2;
        CardView foodShowLessCard;
        CardView foodShowMoreCard;

        FoodViewHolder(View v, OnItemClickListener listener) {
            super(v);
            this.image = v.findViewById(R.id.foodImageView);
            this.title = v.findViewById(R.id.foodTitleId);
            this.location = v.findViewById(R.id.foodLocationId);
            this.halal =  v.findViewById(R.id.foodHalalId);
            this.operatingHours = v.findViewById(R.id.foodOperatingHoursId);
            this.operatingHours2 = v.findViewById(R.id.food2OperatingHoursId);
            this.title2 = v.findViewById(R.id.food2TitleId);
            this.location2 = v.findViewById(R.id.food2LocationId);

            this.foodShowLessCard = v.findViewById(R.id.showLessFoodCard);
            this.foodShowMoreCard = v.findViewById(R.id.showMoreFoodCard);

            foodShowLessCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public void expand(int position) {
        this.expanded = true;
        this.expandedPosition = position;
    }

    public void collapse() {
        this.expanded = false;
        this.expandedPosition = -1;
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    public int getExpandedPosition() {
        return this.expandedPosition;
    }

}
