package com.example.kinhangpoon.foodordering.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kinhangpoon.foodordering.main.presenter.LoginPresenter
import com.example.kinhangpoon.foodordering.main.register.RegisterPresenter
import com.example.kinhangpoon.foodordering.main.register.RegisterPresenterImpl

/**
 * Created by hefen on 3/16/2018.
 */
class SplashActivity : AppCompatActivity() {
    private var loginPresenter: LoginPresenter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Log.i("mylog", "splash call login")
        loginPresenter = LoginPresenter(this)
        loginPresenter?.sendLogin("5555555556", "1234")

        val launcher = Thread(SplashScreenLauncher())
        launcher.start()
    }
    private inner class SplashScreenLauncher : Runnable {
        override fun run() {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            this@SplashActivity.finish()
        }
    }
}