package com.example.weather.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.data.City
import com.example.weather.ui.theme.WeatherTheme

data class RowData(val date: String, val imgSrc: Int, val temp: String)

val rowDataList = listOf(
    RowData("Monday, Feb 20",R.drawable.sun,"14°/12°"),
    RowData("Monday, Feb 21",R.drawable.sun,"14°/12°"),
    RowData("Monday, Feb 22",R.drawable.sun,"14°/12°"),
    RowData("Monday, Feb 23",R.drawable.sun,"14°/12°"),
    RowData("Monday, Feb 24",R.drawable.sun,"14°/12°"),
    RowData("Monday, Feb 25",R.drawable.sun,"14°/12°"),
    RowData("Monday, Feb 26",R.drawable.sun,"14°/12°"),
)

const val temperature = "13"

@Composable
fun CityWeather(data: List<RowData>) {

    val splitIndex = data.size / 2
    val firstScreenData = data.subList(0, splitIndex)
    val secondScreenData = data.subList(splitIndex, data.size)

    LazyColumn {
        item {
            // First screen content
            CityWeather1(firstScreenData)
        }
        item {
            // Second screen content
            CityWeather2()
        }
    }
}

@Composable
fun CityWeather2() {
    TODO("Not yet implemented")
}

@Composable
fun CityWeather1(firstScreenData: List<RowData>) {
    val cityList = listOf(
        City("Tokyo", "06", R.drawable.spark_cloud,listOf(Color(0xFF29FF96), Color(0xFF2AC9B3))),
        City("New York", "19", R.drawable.sun_wind,listOf(Color(0xFFFF7074), Color(0xFFCA03DF))),
        City("London", "13", R.drawable.rain_cloud_sun,listOf(Color(0xFFC661FF), Color(0xFF467BFF))), City("Toky", "06", R.drawable.spark_cloud,listOf(Color(0xFF29FF96), Color(0xFF2AC9B3))),
        City("New Yok", "19", R.drawable.sun_wind,listOf(Color(0xFFFF7074), Color(0xFFCA03DF))),
        // Add more cities here if needed
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(0.dp)
        ) {
            Image( //TODO align it better
                painter = painterResource(id = R.drawable.menu),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text("Today",Modifier.align(alignment = Alignment.Start),fontSize = 15.sp)
        Text("London",Modifier.align(alignment = Alignment.Start),fontSize = 40.sp)
        Text("19-02-2021",Modifier.align(alignment = Alignment.Start),fontSize = 15.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.rain_cloud_sun),
                contentDescription = "",
                modifier = Modifier.size(100.dp)
            )
            Text("|", fontSize = 50.sp, color = Color.LightGray)
            Column() {
                Text( text = "${temperature.padStart(2, '0')}°", fontSize = 60.sp)
                Text("Rainy shower", color = Color.LightGray,fontSize = 15.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(48.dp)
        ){
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.LightGray, RoundedCornerShape(16.dp))
                    .padding(12.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.wind),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.LightGray, RoundedCornerShape(16.dp))
                    .padding(12.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.simple_cloud),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.LightGray, RoundedCornerShape(16.dp))
                    .padding(12.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.water_drop),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(67.dp)){
            Text("19km/h", fontSize = 12.sp)
            Text("75%",fontSize = 12.sp)
            Text("85%",fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text("Today",Modifier.align(alignment = Alignment.Start),fontSize = 20.sp)
        Spacer(modifier = Modifier.height(20.dp))
        CityTimeList(cityList)
        Spacer(modifier = Modifier.height(20.dp))
        val roundedCornerShape = RoundedCornerShape(
            topStartPercent = 5,
            topEndPercent = 15,
            bottomStartPercent = 0,
            bottomEndPercent = 0,
        )
        Card(
            shape = roundedCornerShape,
            modifier = Modifier
                .fillMaxSize()
        ){
            Text("Next Days",
                Modifier
                    .align(alignment = Alignment.Start)
                    .padding(8.dp),fontSize = 20.sp)
//            Spacer(modifier = Modifier.height(10.dp))
            NextDayList(nextDays = firstScreenData)
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityTimeItem(modifier: Modifier = Modifier) {
    var selected = remember {
        mutableStateOf( false)
    }
    var selectedModifier:Modifier
    if(selected.value){
        selectedModifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color(0xFF467BFF),
                        Color(0xFFC661FF)
                    )
                )
            )
            .padding(16.dp)
    }else{
        selectedModifier = Modifier
            .background(Color.White)
            .padding(16.dp)
    }
    val roundedCornerShape = RoundedCornerShape(
        topStartPercent = 10,
        topEndPercent = 35,
        bottomStartPercent = 35,
        bottomEndPercent = 10,
    )
    Card(
//        elevation = CardDefaults.cardElevation(),
        shape = roundedCornerShape,
        border = BorderStroke(1.dp,Color.Gray),
        onClick = {selected.value = !selected.value},
        ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = selectedModifier
        ) {
            Text("12:00am", fontSize = 12.sp)
            Image(painter = painterResource(id = R.drawable.rain_cloud_sun) , contentDescription = "")
            Text("${temperature.padStart(2, '0')}°")
        }
    }
}

@Composable
private fun CityTimeList(cities: List<City>, modifier: Modifier = Modifier) { //TODO only 1 item could be selected
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ){
        items(
            items = cities,
            key = { city ->
                city.name
            }
        ){city ->
            CityTimeItem()
        }
    }
}


@Composable
fun NextDayItem(nextDay: RowData,modifier: Modifier = Modifier){
    Divider(
        color = Color.LightGray,
        thickness = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(48.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Text(nextDay.date)
        Image(painter = painterResource(nextDay.imgSrc), modifier = Modifier.size(30.dp), contentDescription ="" )
        Text(nextDay.temp)
    }
}

@Composable
private fun NextDayList(nextDays: List<RowData>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ){
        items(
            items = nextDays,
            key = { nextDay ->
                nextDay.date
            }
        ){nextDay ->
            NextDayItem(nextDay)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CityWeather1Preview() {
    WeatherTheme {
        val splitIndex = rowDataList.size / 2
        val firstScreenData = rowDataList.subList(0, splitIndex)
        CityWeather1(firstScreenData)
    }
}

@Preview(showBackground = true)
@Composable
fun CityTimeItemPreview() {
    WeatherTheme {
        CityTimeItem()
    }
}

@Preview(showBackground = true)
@Composable
fun NextDayItemPreview() {

    WeatherTheme {
        NextDayItem(RowData("Monday, Feb 20",R.drawable.sun,"14°/12°"),)
    }
}