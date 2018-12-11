package com.test.weather.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.test.weather.data.WeatherData

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weather: WeatherData)

    @Query("SELECT * FROM weather WHERE id=0")
    fun getWeather(): LiveData<WeatherData>
}