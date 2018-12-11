package com.test.weather.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "weather")
class WeatherData {
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int = 0
    var rh: Int? = null
    var sunset: String? = ""
    var temp: Double? = null
    var sunrise: String? = ""
}