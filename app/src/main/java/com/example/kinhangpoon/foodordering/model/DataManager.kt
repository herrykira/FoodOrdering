package com.example.kinhangpoon.foodordering.model

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import android.app.Activity
import com.example.kinhangpoon.foodordering.main.MainActivity


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
                            Log.i("mylog", response.string())
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
        /*call.subscribeOn(Schedulers.io())
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
                )*/
        call
                .doOnNext { response ->
                    AccountDescription.msg = response[0].msg.toString()
                    AccountDescription.UserName = response[0].userName.toString()
                    AccountDescription.UserEmail = response[0].userEmail.toString()
                    AccountDescription.UserAddress = response[0].userAddress.toString()
                    AccountDescription.UserMobile = response[0].userMobile.toString()
                    AccountDescription.login = "login"
                }
                .flatMap { response ->
                    return@flatMap userService!!.recordUser(AccountDescription.UserMobile)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {response -> FoodDescription.recordList = response.orderDetail},
                        {error ->
                            Log.i("mylog", error!!.message)}
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

    override fun requestOrder(order_category: String, order_name: String, order_quantity: String, total_order: String, order_delivery_add: String, order_date: String, user_phone: String) {
        val userService = RetrofitInstance.retrofitInstance!!.create(UserService::class.java)
        val call = userService!!.requestUser(order_category, order_name, order_quantity, total_order, order_delivery_add, order_date, user_phone)
        Log.i("mylog", "send requestOrder")
        call
                .doOnNext { response -> getConfirmNum(response.string()) }
                .flatMap { response ->
                    return@flatMap userService!!.confirmUser(FoodDescription.confirmNum!!)
                }
                .doOnNext { response -> Log.i("mylog", "final confirm " + response.orderDetail[0].orderId)
                                        FoodDescription.confirmOrderDetail = response.orderDetail[0]
                }
                .flatMap { response ->
                    return@flatMap userService!!.recordUser(AccountDescription.UserMobile)
                }
                .doOnNext { response -> FoodDescription.recordList = response.orderDetail }
                .flatMap { response ->
                    return@flatMap userService!!.trackUser(FoodDescription.confirmNum!!)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {response ->
                            FoodDescription.trackOrderDetail = response.orderDetail[0]
                            /*Log.i("mylog", "track: orderId " + response.orderDetail[0].orderId)
                            Log.i("mylog", "track: orderDate " + response.orderDetail[0].orderDate)
                            Log.i("mylog", "track: orderStatus " + response.orderDetail[0].orderStatus)
                            Log.i("mylog", "track: orderTotal " + response.orderDetail[0].totalOrder)
                            Toast.makeText(context, "Order ID: " + response.orderDetail[0].orderId
                                                    + "\nOrder Status: " + response.orderDetail[0].orderStatus
                                                    + "\nMoney Paid: " + response.orderDetail[0].orderStatus
                                                    + "\nOrder Date: " + response.orderDetail[0].orderDate, Toast.LENGTH_LONG).show()*/
                            Toast.makeText(context, "Transaction completed, we'll deliver the food soon", Toast.LENGTH_LONG).show()
                            val activity = context as MainActivity
                            activity.addReceiptFragment()
                        },
                        {error ->
                            Log.i("mylog", error!!.message)
                        }
                )
    }
    fun getConfirmNum(response: String) {
        var str_list: List<String> = response.trim().split(":")
        var str_size: Int = str_list.size
        Log.i("mylog", "raw response " + response)
        FoodDescription.confirmNum = ""
        if (str_size == 2) {
            FoodDescription.confirmNum = str_list[1].trim()
            Log.i("mylog", "get confirm " + FoodDescription.confirmNum)
        } else {
            Log.i("mylog", "no confirm num")
        }
    }
}