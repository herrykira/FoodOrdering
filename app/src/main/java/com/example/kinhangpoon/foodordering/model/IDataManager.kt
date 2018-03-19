package com.example.kinhangpoon.foodordering.model

/**
 * Created by KinhangPoon on 15/3/2018.
 */
interface IDataManager {

    fun requestRegister(name: String, password: String, userEmail: String, userAddress: String, userPhone: String)
    fun requestLogin(user_phone: String, user_password: String)
    fun requestFood(city: String)
}