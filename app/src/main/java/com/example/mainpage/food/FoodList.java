package com.example.mainpage.food;

import com.example.mainpage.food.Food;

import java.util.ArrayList;
import java.util.Collections;

public class FoodList {

    private static ArrayList<Food> foodList = new ArrayList<>();

    public FoodList() {}

    public void add(Food f) {
        foodList.add(f);
    }

    public void addAll(ArrayList<Food> foods) {
        foodList.addAll(foods);
    }

    ArrayList<Food> getAll() {
        Collections.sort(foodList);
        return foodList;
    }

    ArrayList<Food> getByFaculty(String fac) {
        ArrayList<Food> newFoodList = new ArrayList<>();
        for (Food f : foodList) {
            if (f.getFaculty().equals(fac)) {
                newFoodList.add(f);
            }
        }
        Collections.sort(newFoodList);
        return newFoodList;
    }

    ArrayList<Food> getByType(String type) {
        ArrayList<Food> newFoodList = new ArrayList<>();
        for (Food f : foodList) {
            if (f.getType().equals(type)) {
                newFoodList.add(f);
            }
        }
        Collections.sort(newFoodList);
        return newFoodList;
    }

    ArrayList<Food> getLateNight() {
        ArrayList<Food> newFoodList = new ArrayList<>();
        for (Food f : foodList) {
            if (f.getSupper().equals("Y")) {
                newFoodList.add(f);
            }
        }
        Collections.sort(newFoodList);
        return newFoodList;
    }

}
