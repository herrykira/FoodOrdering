package com.example.kinhangpoon.foodordering.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by KinhangPoon on 15/3/2018.
 */
object RetrofitInstance {
    internal var gson = GsonBuilder()
            .setLenient()
            .create()

    val BASE_URL ="http://rjtmobile.com/ansari/fos/fosapp/"
    internal var retrofit: Retrofit? = null

    val retrofitInstance:Retrofit?
    get(){
        retrofit = retrofit2.Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL).build()
        return retrofit
    }
}