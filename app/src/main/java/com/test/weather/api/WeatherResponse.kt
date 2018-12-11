package com.test.weather.api

import com.test.weather.data.WeatherData

data class WeatherResponse(val data: List<WeatherData>, val count: Int)