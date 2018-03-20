package com.example.kinhangpoon.foodordering.model;

import com.google.gson.annotations.SerializedName;

public class OrderDetailItem{

	@SerializedName("OrderName")
	private String orderName;

	@SerializedName("OrderStatus")
	private String orderStatus;

	@SerializedName("OrderId")
	private String orderId;

	@SerializedName("TotalOrder")
	private String totalOrder;

	@SerializedName("OrderDeliverAdd")
	private String orderDeliverAdd;

	@SerializedName("OrderQuantity")
	private String orderQuantity;

	@SerializedName("OrderDate")
	private String orderDate;

	public void setOrderName(String orderName){
		this.orderName = orderName;
	}

	public String getOrderName(){
		return orderName;
	}

	public void setOrderStatus(String orderStatus){
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus(){
		return orderStatus;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public void setTotalOrder(String totalOrder){
		this.totalOrder = totalOrder;
	}

	public String getTotalOrder(){
		return totalOrder;
	}

	public void setOrderDeliverAdd(String orderDeliverAdd){
		this.orderDeliverAdd = orderDeliverAdd;
	}

	public String getOrderDeliverAdd(){
		return orderDeliverAdd;
	}

	public void setOrderQuantity(String orderQuantity){
		this.orderQuantity = orderQuantity;
	}

	public String getOrderQuantity(){
		return orderQuantity;
	}

	public void setOrderDate(String orderDate){
		this.orderDate = orderDate;
	}

	public String getOrderDate(){
		return orderDate;
	}

	@Override
 	public String toString(){
		return 
			"OrderDetailItem{" + 
			"orderName = '" + orderName + '\'' + 
			",orderStatus = '" + orderStatus + '\'' + 
			",orderId = '" + orderId + '\'' + 
			",totalOrder = '" + totalOrder + '\'' + 
			",orderDeliverAdd = '" + orderDeliverAdd + '\'' + 
			",orderQuantity = '" + orderQuantity + '\'' + 
			",orderDate = '" + orderDate + '\'' + 
			"}";
		}
}