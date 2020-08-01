package com.it.myprojectfinal.view.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat

import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.it.myprojectfinal.R
import java.util.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap
    private val AUTOCOMPLETE_REQUEST_CODE = 1
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    //ประกาศตัวแปรรับ lat long
    var lat = ""
    var long = ""

    companion object{
//        var YOUR_API_KEY = "AIzaSyBRMWnV9CyJOKvaFP906Rai5N_e4460NGI"
//        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }



    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        //เพิ่มเครื่องหมาย zoom
        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMarkerClickListener(this)
        setUpMap()
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }

        //เลือกตำแหน่งปัจุบัน
        map.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this){location ->
            if (location != null){
                lastLocation = location
                //ดึง lat long  ออกมาจากตำแหน่งปัจุบัน กรณีที่ผูู้แจ้งอยู่ตรงงงที่เกิดเหตุ
                lat  = location.latitude.toString()
                long = location.longitude.toString()
                // lat long มันจะถูกเก็บไว้ในตัวแปร ที่นี่เวลาเอาลง database ก็ เอา lat  long ลง
                val currentLatLng = LatLng(location.latitude,location.longitude)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,15f))
            }
        }
        map.setOnMapClickListener(object :GoogleMap.OnMapClickListener{
            override fun onMapClick(p0: LatLng) {
                //กดเลือกปักหมุด
                map.addMarker(MarkerOptions().position(p0).title(
                    p0.latitude.toString()+","+p0.longitude.toString()
                ))
                //ตรงนี้ เด้อ  5555 มันวิธีเดียวกันแต่เขียนไม่เหมือนกัน 555+
                lat  = p0.latitude.toString()
                long = p0.longitude.toString()
                object :GoogleMap.OnMarkerClickListener{
                    override fun onMarkerClick(p0: Marker?): Boolean {
                        p0!!.remove()
                        return true
                    }

                }
            }

        })
        map.setOnMarkerClickListener(object :GoogleMap.OnMarkerClickListener{
            override fun onMarkerClick(p0: Marker?): Boolean {
                p0!!.remove()
                return true
            }
        })
    }

    override fun onMarkerClick(p0: Marker?): Boolean = false

}