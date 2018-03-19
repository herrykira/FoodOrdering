package com.example.kinhangpoon.foodordering.main.register

import android.view.View
import com.example.kinhangpoon.foodordering.model.DataManager
import com.example.kinhangpoon.foodordering.model.IDataManager

/**
 * Created by KinhangPoon on 14/3/2018.
 */
class RegisterPresenterImpl ( registerFragment: RegisterFragment) : RegisterPresenter {
    internal var registerView:RegisterView
    internal var idataManager:IDataManager
    init {
        registerView = registerFragment
        idataManager = DataManager(registerFragment.context!!)

    }
    override fun sendRegister(name: String, password: String, userEmail: String, userAddress: String, userPhone: String) {
        idataManager.requestRegister(name,password,userEmail,userAddress,userPhone)
    }

    override fun createView(view: View?) {
        registerView.updateView(view)
    }

}