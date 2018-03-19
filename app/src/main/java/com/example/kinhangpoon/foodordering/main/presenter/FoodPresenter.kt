package com.example.kinhangpoon.foodordering.main.presenter

import com.example.kinhangpoon.foodordering.main.SplashActivity
import com.example.kinhangpoon.foodordering.model.DataManager
import com.example.kinhangpoon.foodordering.model.IDataManager

/**
 * Created by hefen on 3/18/2018.
 */
class FoodPresenter(splashActivity: SplashActivity): IFoodPresenter {
    internal var splashActivity: SplashActivity
    internal var idataManager: IDataManager

    init {
        this.splashActivity = splashActivity
        idataManager = DataManager(splashActivity)

    }
    override fun sendFood(city: String) {
        idataManager.requestFood(city)
    }
}