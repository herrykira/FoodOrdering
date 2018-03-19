package com.example.kinhangpoon.foodordering.model

import android.content.Context
import android.util.Log
import com.example.kinhangpoon.foodordering.network.RetrofitInstance
import com.example.kinhangpoon.foodordering.network.UserService
import com.example.kinhangpoon.foodordering.utility.AccountDescription
import com.example.kinhangpoon.foodordering.utility.FoodDescription
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
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
        Log.i("mylog", "call login")
        //Log.i("mylog", "call url: " + call.request().url().toString())
        /*call.enqueue(object : Callback<Any>{
            override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
                //iRegistrationFragment.showToastMessage(response.body().toString())
                //Log.i("mylog", "got response")
                Log.i("mylog", response!!.body().toString().trim())
            }


            override fun onFailure(call: Call<Any>?, t: Throwable?) {
                Log.i("mylog", t!!.message)
            }
        })*/
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {response  ->
                            Log.i("mylog", response.toString())
                        },
                        {error ->
                            Log.i("mylog", error!!.message)
                        }
                )
    }

    override fun requestLogin(user_phone: String, user_password: String) {
        val userService = RetrofitInstance.retrofitInstance!!.create(UserService::class.java)
        val call = userService!!.loginUser(user_phone, user_password)
        Log.i("mylog", "call login")
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {response ->
                            //Log.i("mylog", response[0].userName.toString())
                            AccountDescription.msg = response[0].msg.toString()
                            AccountDescription.UserName = response[0].userName.toString()
                            AccountDescription.UserEmail = response[0].userEmail.toString()
                            AccountDescription.UserAddress = response[0].userAddress.toString()
                            AccountDescription.UserMobile = response[0].userMobile.toString()
                            AccountDescription.login = "login"
                        },
                        {error ->
                            Log.i("mylog", error!!.message)
                        }
                )
    }

    override fun requestFood(city: String) {
        val userService = RetrofitInstance.retrofitInstance!!.create(UserService::class.java)
        val call = userService!!.foodUser(city)
        Log.i("mylog", "call food")
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {response ->
                            Log.i("mylog", response.food[0].foodName.toString())
                            if (city == "delhi") {
                                FoodDescription.foodListDelhi = response.food
                            } else if (city == "banglore") {
                                FoodDescription.foodListBanglore = response.food
                            }
                        },
                        {error ->
                            Log.i("mylog", error!!.message)
                        }
                )
    }
}