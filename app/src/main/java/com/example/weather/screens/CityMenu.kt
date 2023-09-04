package com.example.weather.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.data.City
import com.example.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.launch

// should use (Navigation Drawer) ModalNavigationDrawer
// https://m3.material.io/components/navigation-drawer/overview
// implement normal navigation drawer && you can skip animation
// try to sync how to write code can be use navigation component systeem
// https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ModalNavigationDrawer(kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.material3.DrawerState,kotlin.Boolean,androidx.compose.ui.graphics.Color,kotlin.Function0)
// also you can use see: https://fvilarino.medium.com/animated-drawer-in-jetpack-compose-5e39f659f9c , https://proandroiddev.com/navigation-with-animated-transitions-using-jetpack-compose-daeb00d4fb45

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val iconsAndLabels = listOf(
        Icons.Default.Add to "Add City",
        Icons.Outlined.Settings to "Settings",
        Icons.Outlined.LocationOn to "Map"
    )
    val selectedItem = remember { mutableStateOf(iconsAndLabels[0]) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {

                Column(
                    modifier = Modifier
                        .widthIn(max = 250.dp) // Limit the drawer's width when opened
                        .fillMaxSize()
                        .padding(16.dp),
                ) {
                    Spacer(Modifier.height(200.dp))
                    iconsAndLabels.forEach { (icon, label) ->
                        NavigationDrawerItem(
                            icon = { Icon(icon, contentDescription = null) },
                            label = { Text(label) },
                            selected = icon == selectedItem.value.first,
                            onClick = {
                                scope.launch { drawerState.close() }
                                selectedItem.value = icon to label
                            },
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        },
        content = {
            Scaffold(
//                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    TopAppBar(
                        title = { Text("") },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.menu),
                                    contentDescription = "",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior
                    )
                },
                content = {
                    var tilt = if (drawerState.isClosed) 0f else 25f // Tilt when open
                    CityWeatherScreen(daysData = rowDataList, tilt = tilt)
                }
            )
        }
    )
}





@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    WeatherTheme {
        MenuScreen()
    }
}