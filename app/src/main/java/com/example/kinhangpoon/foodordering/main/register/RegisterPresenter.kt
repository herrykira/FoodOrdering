package com.example.kinhangpoon.foodordering.main.register

import android.view.View

/**
 * Created by KinhangPoon on 14/3/2018.
 */
interface RegisterPresenter {
    fun createView(view: View?)

    fun sendRegister(name:String?,password:String?,userEmail:String?,userAddress:String?,userPhone:String?)
}