package com.example.kinhangpoon.foodordering.main.presenter

/**
 * Created by hefen on 3/18/2018.
 */
interface IFoodPresenter {
    fun sendFood(city:String)
    fun sendOrder(order_category: String, order_name: String, order_quantity: String, total_order: String,
                  order_delivery_add: String, order_date: String, user_phone: String)
}