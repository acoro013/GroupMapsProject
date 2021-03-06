package com.example.groupmapsproject

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.groupmapsproject.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.function.Consumer

const val TAG = "MapsActivity"
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

            findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener{
                    item ->
                var fragmentToShow: Fragment? = null
                when(item.itemId){
                    /*
                    R.id.map_home ->{
                        Toast.makeText(this, "home", Toast.LENGTH_SHORT).show()
                        fragmentToShow = MapsActivity()
                    }
                     */
                    R.id.map_saved_places ->{
                        Toast.makeText(this,"saved places", Toast.LENGTH_LONG).show()
                        fragmentToShow = ListFragment()
                    }
                }
                true
            }

        }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        getCurrentLocation()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun getCurrentLocation() {
        // Check if app has not been granted ACCESS_FINE_LOCATION, then request for it.
        try {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    101
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getCurrentLocation: $e", )
        }

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // Get current location from GPS_PROVIDER to LocationListener
        locationManager.getCurrentLocation(LocationManager.GPS_PROVIDER, null, this.mainExecutor,
            object: Consumer<Location> {
                override fun accept(location: Location) {
                    displayLocation(location.latitude, location.longitude, "Current Location - Title", "Current Location - Snippet")
                }
        })
    }

    private fun displayLocation(latitude: Double, longitude: Double, title: String, snippet: String) {
        val latLng = LatLng(latitude, longitude)
        mMap.addMarker(
            MarkerOptions().position(latLng)
                .title(title)
                .snippet(snippet))
        moveCamera(latitude, longitude)
    }

    private fun moveCamera(latitude: Double, longitude: Double) {
        val latLng = LatLng(latitude, longitude)
        val zoom = 17f
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom)
        mMap.animateCamera(cameraUpdate)
    }
}