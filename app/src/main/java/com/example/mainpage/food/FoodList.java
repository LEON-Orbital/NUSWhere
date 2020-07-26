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
        ArrayList<Food> allFoodList = new ArrayList<>(foodList);
        return allFoodList;
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

    // takes in a list of food id and returns the list of food
    ArrayList<Food> getFavourites(ArrayList<String> favList) {
        ArrayList<Food> favFoodList = new ArrayList<>();
        for (String s : favList) {
            for (Food f : foodList) {
                if (s.equals(f.getId())) {
                    favFoodList.add(f);
                }
            }
        }
        Collections.sort(favFoodList);
        return favFoodList;
    }

    public boolean isEmpty() {
        return foodList.isEmpty();
    }
}
