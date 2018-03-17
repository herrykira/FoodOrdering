package com.example.kinhangpoon.foodordering.main.register

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.kinhangpoon.foodordering.R
import com.example.kinhangpoon.foodordering.model.IDataManager

/**
 * Created by KinhangPoon on 14/3/2018.
 */
class RegisterFragment : Fragment(),RegisterView {

    private var registerPresenter: RegisterPresenter?=null
    private var username:EditText? = null
    private var password:EditText? = null
    private var email:EditText? = null
    private var address:EditText? = null
    private var phone:EditText? = null
    private var buttonRegister:Button? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        registerPresenter = RegisterPresenterImpl(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View? = inflater?.inflate(R.layout.register_fragment,container,false)
        registerPresenter?.createView(view);
        return view
    }
    override fun updateView(view: View?) {
        username = view?.findViewById<EditText>(R.id.editText_name_register) as EditText
        password = view?.findViewById<EditText>(R.id.editText_password_register) as EditText
        email = view?.findViewById<EditText>(R.id.editText_email_register) as EditText
        address = view?.findViewById<EditText>(R.id.editText_address_register) as EditText
        phone = view?.findViewById<EditText>(R.id.editText_phone_register) as EditText
        buttonRegister = view?.findViewById<Button>(R.id.button_register) as Button


        buttonRegister?.setOnClickListener {
            val Name = username?.text.toString()
            val passWord = password?.text.toString()
            val userEmail = email?.text.toString()
            val userAddress = address?.text.toString()
            val userPhone = phone?.text.toString()
            registerPresenter?.sendRegister(Name, passWord, userEmail, userAddress, userPhone)
        }

    }
}