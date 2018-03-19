package com.example.kinhangpoon.foodordering.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("UserMobile")
	private String userMobile;

	@SerializedName("UserName")
	private String userName;

	@SerializedName("UserAddress")
	private String userAddress;

	@SerializedName("UserEmail")
	private String userEmail;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setUserMobile(String userMobile){
		this.userMobile = userMobile;
	}

	public String getUserMobile(){
		return userMobile;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserAddress(String userAddress){
		this.userAddress = userAddress;
	}

	public String getUserAddress(){
		return userAddress;
	}

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"msg = '" + msg + '\'' + 
			",userMobile = '" + userMobile + '\'' + 
			",userName = '" + userName + '\'' + 
			",userAddress = '" + userAddress + '\'' + 
			",userEmail = '" + userEmail + '\'' + 
			"}";
		}
}