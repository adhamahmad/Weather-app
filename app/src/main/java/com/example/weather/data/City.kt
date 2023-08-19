package com.example.weather.data

import androidx.compose.ui.graphics.Color

data class City(
    val name: String,
    val temp: String,
    val imgSrc: Int,
    val gradientColors:List<Color>
)