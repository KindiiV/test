package com.test.weather.ui

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.test.weather.R
import com.test.weather.viewModel.WeatherViewModel
import com.test.weather.databinding.ActivityWeatherBinding
import com.test.weather.utils.AppUtils
import com.test.weather.utils.PrefUtil

class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherViewModel = WeatherViewModel()
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_weather
        )
        binding.btnChangeCity.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        when {
            (!AppUtils.isOnline() && PrefUtil.getIsFirst(this)) -> {
                showAlert()
                return
            }
            (!AppUtils.isOnline() && !PrefUtil.getIsFirst(this)) -> AppUtils.showMessage("You can`t update data, please check your network")
        }
        weatherViewModel.getWeather().observe(this, Observer { weatherData ->
            if (weatherData != null) {
                binding.tvRelativeHumidity.text = weatherData.rh.toString()
                binding.tvTemperature.text = weatherData.temp.toString()
                binding.tvSunriseTime.text = weatherData.sunrise
                binding.tvSunsetTime.text = weatherData.sunset
            }
        })
    }

    private fun showAlert() {
        val builder: AlertDialog.Builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AlertDialog.Builder(this, R.style.myDialog)
        } else {
            AlertDialog.Builder(this)
        }
        builder.setMessage("You can`t get data, please check your network")
            .setPositiveButton(android.R.string.yes) { dialog, _ ->
                dialog.dismiss()
                System.exit(0)
            }
            .show()
    }
}