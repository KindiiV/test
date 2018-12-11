package com.test.weather.api

import io.reactivex.Observable
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("current")
    fun getWeather(@Query("lat") lat: Double,
                   @Query("lon") lon: Double, @Query("key") key: String): Observable<WeatherResponse>

    companion object Factory {

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.weatherbit.io/v2.0/")
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}