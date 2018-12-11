package com.test.weather.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.test.weather.utils.PrefUtil

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lat = PrefUtil.getLat(this)
        if (lat != 0.0)
            startActivity(Intent(this, WeatherActivity::class.java))
        else startActivity(Intent(this, MainActivity::class.java))
    }
}