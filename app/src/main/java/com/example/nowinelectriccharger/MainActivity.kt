package com.example.nowinelectriccharger

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.core.utils.isLocationPermissionNotGranted
import com.example.feature.chargers.ui.ChargerViewModel
import com.example.theme.NowinelectricchargerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val LOCATION_PERMISSION_REQUEST_CODE = 1001

    private val chargerViewModel: ChargerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        askForLocationPermission();

        enableEdgeToEdge()
        setContent {
            val appState = rememberNowInElectricChargerAppState();
            NowinelectricchargerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NowInElectricChargerApp(
                        appState,
                        viewModel = chargerViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun askForLocationPermission() {
        if (isLocationPermissionNotGranted(this)) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

}