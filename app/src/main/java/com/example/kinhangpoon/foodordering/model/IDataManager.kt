package com.example.kinhangpoon.foodordering.model

import android.view.View

/**
 * Created by KinhangPoon on 15/3/2018.
 */
interface IDataManager {

    fun requestRegister(name: String, password: String, userEmail: String, userAddress: String, userPhone: String)
}