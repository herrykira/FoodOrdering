package com.example.kinhangpoon.foodordering.model

import android.content.Context
import android.util.Log
import com.example.kinhangpoon.foodordering.network.RetrofitInstance
import com.example.kinhangpoon.foodordering.network.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by KinhangPoon on 15/3/2018.
 */
class DataManager(context: Context): IDataManager {
    internal var context:Context
    init {
        this.context = context
    }

    override fun requestRegister(name: String, password: String, userEmail: String, userAddress: String, userPhone: String) {
        val userService = RetrofitInstance.retrofitInstance!!.create(UserService::class.java)
        val call = userService!!.registerUser(name,userEmail,userPhone,password,userAddress)

        Log.i("mylog", "call url: " + call.request().url().toString())
        call.enqueue(object : Callback<Any>{
            override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
                //iRegistrationFragment.showToastMessage(response.body().toString())
                //Log.i("mylog", "got response")
                Log.i("mylog", response!!.body().toString().trim())
            }


            override fun onFailure(call: Call<Any>?, t: Throwable?) {
                Log.i("mylog", t!!.message)
            }
        })
    }
}