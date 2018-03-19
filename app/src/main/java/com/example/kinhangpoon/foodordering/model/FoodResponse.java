package com.example.kinhangpoon.foodordering.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FoodResponse{

	@SerializedName("Food")
	private List<FoodItem> food;

	public void setFood(List<FoodItem> food){
		this.food = food;
	}

	public List<FoodItem> getFood(){
		return food;
	}

	@Override
 	public String toString(){
		return
			"FoodResponse{" +
			"food = '" + food + '\'' +
			"}";
		}
}