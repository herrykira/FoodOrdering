package com.example.kinhangpoon.foodordering.model

import android.view.View

/**
 * Created by KinhangPoon on 15/3/2018.
 */
interface IDataManager {

    fun requestRegister(name: String, password: String, userEmail: String, userAddress: String, userPhone: String)
    fun requestLogin(user_phone: String, user_password: String)
    fun requestFood(city: String)
    fun requestOrder(order_category: String, order_name: String, order_quantity: String, total_order: String,
                     order_delivery_add: String, order_date: String, user_phone: String)

}