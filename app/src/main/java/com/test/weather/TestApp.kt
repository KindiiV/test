package com.test.weather

import android.app.Application
import android.content.Context

class TestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: TestApp
        fun getInstance(): Context {
            return instance
        }
    }
}