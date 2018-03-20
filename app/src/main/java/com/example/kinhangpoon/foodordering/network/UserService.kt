package com.example.kinhangpoon.foodordering.network


import com.example.kinhangpoon.foodordering.model.*
import io.reactivex.Observable
import okhttp3.ResponseBody
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
                     ,@Query("user_add") userAddress:String): Observable<ResponseBody>

    @GET("fos_login.php")
    fun loginUser(@Query("user_phone") user_phone:String,
                  @Query("user_password") user_password:String): Observable<Array<LoginResponse>>

    @GET("fos_food_loc.php")
    fun foodUser(@Query("city") city:String): Observable<FoodResponse>

    @GET("order_request.php")
    fun requestUser(@Query("order_category") order_category:String, @Query("order_name") order_name:String
                     ,@Query("order_quantity") order_quantity:String,@Query("total_order") total_order:String
                     ,@Query("order_delivery_add") order_delivery_add:String, @Query("order_date") order_date:String
                    ,@Query("user_phone") user_phone:String): Observable<ResponseBody>

    @GET("order_confirmation.php")
    fun confirmUser(@Query("order_id") order_id:String): Observable<ConfirmResponse>

    @GET("order_track.php")
    fun trackUser(@Query("order_id") order_id:String): Observable<TrackResponse>

    @GET("order_recent.php")
    fun recordUser(@Query("user_phone") user_phone:String): Observable<RecordResponse>
}