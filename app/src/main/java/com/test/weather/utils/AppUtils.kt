package com.test.weather.utils

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.test.weather.TestApp
import android.os.Build
import com.test.weather.R


class AppUtils {
    companion object {
        fun isOnline(): Boolean {
            val connectivityManager =
                TestApp.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

        fun showMessage(message: String) {
            Toast.makeText(TestApp.getInstance(), message, Toast.LENGTH_LONG).show()
        }
    }
}