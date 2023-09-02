package com.example.weather.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.ui.theme.WeatherTheme

@Composable
fun MenuScreen() {
    // should use (Navigation Drawer) ModalNavigationDrawer
    // https://m3.material.io/components/navigation-drawer/overview
    // implement normal navigation drawer && you can skip animation
    // try to sync how to write code can be use navigation component systeem
    // https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ModalNavigationDrawer(kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.material3.DrawerState,kotlin.Boolean,androidx.compose.ui.graphics.Color,kotlin.Function0)
    // also you can use see: https://fvilarino.medium.com/animated-drawer-in-jetpack-compose-5e39f659f9c , https://proandroiddev.com/navigation-with-animated-transitions-using-jetpack-compose-daeb00d4fb45
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = Modifier
            .padding(top = 200.dp, start = 16.dp)
            .fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Plus Icon",
                tint = Color.Black // Set the desired color for the icon
            )
            Text("Add City")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = "Setting Icon",
                tint = Color.Black // Set the desired color for the icon
            )
            Text("Setting")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = "Map Icon",
                tint = Color.Black // Set the desired color for the icon
            )
            Text("Map")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 160.dp)
            .rotate(15f) // Adjust the rotation angle as needed
    ) {
        CityWeatherScreen(daysData = rowDataList)
    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    WeatherTheme {
        MenuScreen()
    }
}