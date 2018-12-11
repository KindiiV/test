package com.test.weather.repository

import android.arch.lifecycle.LiveData
import android.os.Handler
import com.test.weather.utils.PrefUtil
import com.test.weather.TestApp
import com.test.weather.api.ApiService
import com.test.weather.api.WeatherResponse
import com.test.weather.data.AppDatabase
import com.test.weather.data.WeatherDao
import com.test.weather.data.WeatherData
import com.test.weather.utils.AppUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WeatherRepository {

    private var db = AppDatabase.getInstance(TestApp.getInstance())
    private val weatherDao: WeatherDao = db.weatherDao()
    private val weatherData: LiveData<WeatherData> = weatherDao.getWeather()
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun getWeather(): LiveData<WeatherData> {
        when {
            (AppUtils.isOnline()) -> updateWeather()
        }
        return weatherData
    }

    private fun updateWeather() {
        val apiService = ApiService.create()
        apiService.getWeather(
            PrefUtil.getLat(TestApp.getInstance()),
            PrefUtil.getLng(TestApp.getInstance()), "48481551555a4970838db20acefd26d6"
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t: WeatherResponse? ->
                PrefUtil.setIsFirst(TestApp.getInstance())
                scope.launch(Dispatchers.IO) {
                    weatherDao.insert(t!!.data[0])
                }

            }
    }
}