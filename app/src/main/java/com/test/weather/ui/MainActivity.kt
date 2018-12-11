package com.test.weather.ui

import android.content.Intent
import android.location.Geocoder
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.test.weather.utils.PrefUtil
import com.test.weather.R
import com.test.weather.utils.AppUtils
import java.util.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        if (!AppUtils.isOnline() && PrefUtil.getIsFirst(this)) {
            showAlert()
            return
        }
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val ukraine = LatLng(49.478446, 31.754834)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ukraine))
        mMap.setOnMapClickListener { latLng -> handleLocation(latLng) }
    }

    private fun handleLocation(latLng: LatLng) {
        PrefUtil.setLat(this, latLng.latitude)
        PrefUtil.setLng(this, latLng.longitude)
        val weatherIntent = Intent(this, WeatherActivity::class.java)
        weatherIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(weatherIntent)
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
