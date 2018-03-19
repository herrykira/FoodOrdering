package com.example.kinhangpoon.foodordering.model;

import com.google.gson.annotations.SerializedName;

public class FoodItem{

	@SerializedName("FoodCategory")
	private String foodCategory;

	@SerializedName("FoodRecepiee")
	private String foodRecepiee;

	@SerializedName("FoodPrice")
	private String foodPrice;

	@SerializedName("FoodThumb")
	private String foodThumb;

	@SerializedName("FoodId")
	private String foodId;

	@SerializedName("FoodName")
	private String foodName;

	public void setFoodCategory(String foodCategory){
		this.foodCategory = foodCategory;
	}

	public String getFoodCategory(){
		return foodCategory;
	}

	public void setFoodRecepiee(String foodRecepiee){
		this.foodRecepiee = foodRecepiee;
	}

	public String getFoodRecepiee(){
		return foodRecepiee;
	}

	public void setFoodPrice(String foodPrice){
		this.foodPrice = foodPrice;
	}

	public String getFoodPrice(){
		return foodPrice;
	}

	public void setFoodThumb(String foodThumb){
		this.foodThumb = foodThumb;
	}

	public String getFoodThumb(){
		return foodThumb;
	}

	public void setFoodId(String foodId){
		this.foodId = foodId;
	}

	public String getFoodId(){
		return foodId;
	}

	public void setFoodName(String foodName){
		this.foodName = foodName;
	}

	public String getFoodName(){
		return foodName;
	}

	@Override
 	public String toString(){
		return 
			"FoodItem{" + 
			"foodCategory = '" + foodCategory + '\'' + 
			",foodRecepiee = '" + foodRecepiee + '\'' + 
			",foodPrice = '" + foodPrice + '\'' + 
			",foodThumb = '" + foodThumb + '\'' + 
			",foodId = '" + foodId + '\'' + 
			",foodName = '" + foodName + '\'' + 
			"}";
		}
}