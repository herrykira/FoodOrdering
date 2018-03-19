package com.example.kinhangpoon.foodordering.network


import com.example.kinhangpoon.foodordering.model.FoodResponse
import com.example.kinhangpoon.foodordering.model.LoginResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by KinhangPoon on 15/3/2018.
 */
interface
UserService {

    /*@GET("fos_reg.php")
    fun registerUser(@Query("user_name") username:String, @Query("user_email") userEmail:String
                     ,@Query("user_phone") userPhone:String,@Query("user_password") userPassword:String
                    ,@Query("user_add") userAddress:String): Call<Any>*/

    @GET("fos_reg.php")
    fun registerUser(@Query("user_name") username:String, @Query("user_email") userEmail:String
                     ,@Query("user_phone") userPhone:String,@Query("user_password") userPassword:String
                     ,@Query("user_add") userAddress:String): Observable<Any>

    @GET("fos_login.php")
    fun loginUser(@Query("user_phone") user_phone:String,
                  @Query("user_password") user_password:String): Observable<Array<LoginResponse>>

    @GET("fos_food_loc.php")
    fun foodUser(@Query("city") city:String): Observable<FoodResponse>



}