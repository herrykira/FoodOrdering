package com.example.kinhangpoon.foodordering.network


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by KinhangPoon on 15/3/2018.
 */
interface
UserService {

    @GET("fos_reg.php")
    fun registerUser(@Query("user_name") username:String, @Query("user_email") userEmail:String
                     ,@Query("user_phone") userPhone:String,@Query("user_password") userPassword:String
                    ,@Query("user_add") userAddress:String): Call<Any>
}