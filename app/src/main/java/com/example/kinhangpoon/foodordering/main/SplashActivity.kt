package com.example.kinhangpoon.foodordering.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by hefen on 3/16/2018.
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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