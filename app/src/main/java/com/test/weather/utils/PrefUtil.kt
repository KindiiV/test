package com.test.weather.utils

import android.content.Context
import android.preference.PreferenceManager

class PrefUtil {
    companion object {
        private const val LNG_KEY = "lng_key"
        private const val LAT_KEY = "lat_key"
        private const val FIRST_KEY = "first_key"

        fun getLng(context: Context): Double {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getString(LNG_KEY, "0.0").toDouble()
        }

        fun setLng(context: Context, lng: Double) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putString(LNG_KEY, lng.toString())
            editor.apply()
        }

        fun getLat(context: Context): Double {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getString(LAT_KEY, "0.0").toDouble()
        }

        fun setLat(context: Context, lng: Double) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putString(LAT_KEY, lng.toString())
            editor.apply()
        }

        fun getIsFirst(context: Context): Boolean {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getBoolean(FIRST_KEY, true)
        }

        fun setIsFirst(context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putBoolean(FIRST_KEY, false)
            editor.apply()
        }
    }
}