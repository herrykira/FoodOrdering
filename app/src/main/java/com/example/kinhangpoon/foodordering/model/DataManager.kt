package com.example.kinhangpoon.foodordering.model

import android.content.Context
import com.example.kinhangpoon.foodordering.network.RetrofitInstance
import com.example.kinhangpoon.foodordering.network.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by KinhangPoon on 15/3/2018.
 */
class DataManager(context: Context?): IDataManager {
    override fun requestRegister(name: String, password: String, userEmail: String, userAddress: String, userPhone: String) {
        val userService = RetrofitInstance.retrofitInstance!!.create(UserService::class.java)
        val call = userService!!.registerUser(name,userEmail,userPhone,password,userAddress)
        call.enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>?, response: Response<String>?) {

            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {

            }
        })
    }
}