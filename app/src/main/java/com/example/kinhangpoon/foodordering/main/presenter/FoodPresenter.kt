package com.example.kinhangpoon.foodordering.main.presenter

import com.example.kinhangpoon.foodordering.main.MainActivity
import com.example.kinhangpoon.foodordering.main.SplashActivity
import com.example.kinhangpoon.foodordering.model.DataManager
import com.example.kinhangpoon.foodordering.model.IDataManager
import android.content.Context
/**
 * Created by hefen on 3/18/2018.
 */
//class FoodPresenter(splashActivity: SplashActivity): IFoodPresenter {
//class FoodPresenter: IFoodPresenter {
class FoodPresenter(context: Context): IFoodPresenter {

    //internal var splashActivity: SplashActivity? = null
    //internal var mainActivity: MainActivity? = null
    internal var context: Context
    internal var idataManager: IDataManager
    init {
        this.context = context
        idataManager = DataManager(context)
    }
    /*constructor(splashActivity: SplashActivity) {
        this.splashActivity = splashActivity
        idataManager = DataManager(splashActivity)
    }

    constructor(mainActivity: MainActivity) {
        this.mainActivity = mainActivity
        idataManager = DataManager(mainActivity)
    }*/
    /*init {
        this.splashActivity = splashActivity
        idataManager = DataManager(splashActivity)

    }*/
    override fun sendFood(city: String) {
        idataManager.requestFood(city)
    }

    override fun sendOrder(order_category: String, order_name: String, order_quantity: String,
                           total_order: String, order_delivery_add: String, order_date: String, user_phone: String) {
        idataManager.requestOrder(order_category, order_name, order_quantity,
                total_order, order_delivery_add, order_date, user_phone)
    }

}

