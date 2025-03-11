package com.example.nowinelectriccharger

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.feature.chargers.ui.ChargerViewModel
import com.example.theme.NowinelectricchargerTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val LOCATION_PERMISSION_REQUEST_CODE = 1001
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val chargerViewModel: ChargerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        checkLocationPermission();

        enableEdgeToEdge()
        setContent {
            NowinelectricchargerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NowInElectricChargerApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkLocationPermission() {
        if (isLocationPermissionGranted()) {
            getLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val cancellationTokenSource = CancellationTokenSource()

        fusedLocationClient.getCurrentLocation(100, cancellationTokenSource.token)
            .addOnSuccessListener { location ->
                println("Location: ${location.latitude} ${location.longitude}")
                chargerViewModel.setCurrentLocation(location)
            }
            .addOnFailureListener { exception ->
                println("Location Oops location failed with exception: $exception")
            }
    }

    private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            false
        } else {
            true
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    com.example.theme.NowinelectricchargerTheme {
        NowInElectricChargerApp()
    }
}