package com.test.weather.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.test.weather.repository.WeatherRepository
import com.test.weather.data.WeatherData

class WeatherViewModel : ViewModel() {
    private val weatherRepository: WeatherRepository =
        WeatherRepository()

    fun getWeather():LiveData<WeatherData> {
        return  weatherRepository.getWeather()

    }
}