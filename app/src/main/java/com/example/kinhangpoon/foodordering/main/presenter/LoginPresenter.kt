package com.example.kinhangpoon.foodordering.main.presenter

import android.util.Log
import com.example.kinhangpoon.foodordering.main.SplashActivity
import com.example.kinhangpoon.foodordering.main.register.RegisterView
import com.example.kinhangpoon.foodordering.model.DataManager
import com.example.kinhangpoon.foodordering.model.IDataManager

/**
 * Created by hefen on 3/18/2018.
 */
class LoginPresenter(splashActivity: SplashActivity): ILoginPresenter {
    internal var splashActivity: SplashActivity
    internal var idataManager: IDataManager

    init {
        this.splashActivity = splashActivity
        idataManager = DataManager(splashActivity)

    }
    override fun sendLogin(user_phone: String, user_password: String) {
        //Log.i("mylog", "presenter call login")
        idataManager.requestLogin(user_phone, user_password)
    }
}