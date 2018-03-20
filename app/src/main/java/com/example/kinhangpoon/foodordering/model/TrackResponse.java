package com.example.kinhangpoon.foodordering.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TrackResponse{

	@SerializedName("Order Detail")
	private List<OrderDetailItem> orderDetail;

	public void setOrderDetail(List<OrderDetailItem> orderDetail){
		this.orderDetail = orderDetail;
	}

	public List<OrderDetailItem> getOrderDetail(){
		return orderDetail;
	}

	@Override
 	public String toString(){
		return 
			"TrackResponse{" + 
			"order Detail = '" + orderDetail + '\'' + 
			"}";
		}
}