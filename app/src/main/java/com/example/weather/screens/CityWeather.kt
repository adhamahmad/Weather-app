package com.example.weather.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.weather.R
import com.example.weather.data.City
import com.example.weather.ui.theme.WeatherTheme

data class RowData(val date: String, val imgSrc: Int, val temp: String)

val rowDataList = listOf(
    RowData("Monday, Feb 20", R.drawable.sun, "14°/12°"),
    RowData("Monday, Feb 21", R.drawable.sun, "14°/12°"),
    RowData("Monday, Feb 22", R.drawable.sun, "14°/12°"),
    RowData("Monday, Feb 23", R.drawable.sun, "14°/12°"),
    RowData("Monday, Feb 24", R.drawable.sun, "14°/12°"),
    RowData("Monday, Feb 25", R.drawable.sun, "14°/12°"),
)

const val temperature = "13"
val cityList = listOf(
    City("Tokyo", "06", R.drawable.spark_cloud, listOf(Color(0xFF29FF96), Color(0xFF2AC9B3))),
    City("New York", "19", R.drawable.sun_wind, listOf(Color(0xFFFF7074), Color(0xFFCA03DF))),
    City(
        "London", "13", R.drawable.rain_cloud_sun, listOf(Color(0xFFC661FF), Color(0xFF467BFF))
    ),
    City("Toky", "06", R.drawable.spark_cloud, listOf(Color(0xFF29FF96), Color(0xFF2AC9B3))),
    City("New Yok", "19", R.drawable.sun_wind, listOf(Color(0xFFFF7074), Color(0xFFCA03DF))),
    // Add more cities here if needed
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityWeatherScreen(modifier: Modifier = Modifier, daysData: List<RowData>) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = { /**/ }, modifier = Modifier.align(Alignment.Start)) {
            Icon(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = "",
                modifier = Modifier.size(24.dp)
            )
        }
        Text("Today", Modifier.align(alignment = Alignment.Start), fontSize = 15.sp)
        Text("London", Modifier.align(alignment = Alignment.Start), fontSize = 40.sp)
        Text(
            "19-02-2021",
            Modifier.align(alignment = Alignment.Start),
            fontSize = 15.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.rain_cloud_sun),
                contentDescription = "",
                modifier = Modifier.size(100.dp)
            )
            Text("|", fontSize = 50.sp, color = Color.LightGray)
            Column {
                Text(text = "${temperature.padStart(2, '0')}°", fontSize = 60.sp)
                Text("Rainy shower", color = Color.LightGray, fontSize = 15.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        // wee can enhance it using objets simulating data returned from api
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(48.dp)
        ) {
            // NO Extract icon & text in one Composable
            CurrentWeatherState(iconRes = R.drawable.wind, "19km/h")
            CurrentWeatherState(iconRes = R.drawable.simple_cloud, "75%")
            CurrentWeatherState(iconRes = R.drawable.water_drop, "85%")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text("Today", Modifier.align(alignment = Alignment.Start), fontSize = 20.sp)
        Spacer(modifier = Modifier.height(20.dp))
        CityTimeList(cityList)
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            shape = RoundedCornerShape(
                topStartPercent = 5,
                topEndPercent = 15,
                bottomStartPercent = 15,
                bottomEndPercent = 5,
            ), modifier = Modifier.fillMaxSize()
        ) {
            Text(
                "Next Days",
                Modifier
                    .align(alignment = Alignment.Start)
                    .padding(8.dp),
                fontSize = 20.sp
            )
            NextDayList(nextDays = daysData)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text("Comfort Level", Modifier.align(alignment = Alignment.Start), fontSize = 20.sp)
        ComposeCircularProgressBar(percentage = 0.75f)
        Row(
            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text("Feels like ", color = Color.LightGray)
            Text("10°")
            Spacer(modifier = Modifier.width(48.dp))
            Text("|", fontSize = 30.sp, color = Color.LightGray)
            Spacer(modifier = Modifier.width(48.dp))
            Text("UV index ", color = Color.LightGray)
            Text("0 low")
        }
        Spacer(modifier = Modifier.height(5.dp))
        Divider(
            color = Color.LightGray, thickness = 1.dp, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text("Sunrise and Sunset", Modifier.align(alignment = Alignment.Start), fontSize = 20.sp)
        Spacer(modifier = Modifier.height(5.dp))
        SunsetCircle(sunRise = "06:10", sunSet = "18:45")

        Divider(
            color = Color.LightGray, thickness = 1.dp, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text("Wind", Modifier.align(alignment = Alignment.Start), fontSize = 20.sp)

        Image(
            painter = painterResource(id = R.drawable.turbine),
            contentDescription = "",
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text("Direction ", color = Color.LightGray)
            Text("North")
            Spacer(modifier = Modifier.width(48.dp))
            Text("|", fontSize = 30.sp, color = Color.LightGray)
            Spacer(modifier = Modifier.width(48.dp))
            Text("Speed ", color = Color.LightGray)
            Text("12km/h")
        }

    }


}

@Composable
private fun CurrentWeatherState(@DrawableRes iconRes: Int, desc: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.LightGray, RoundedCornerShape(16.dp))
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(desc, fontSize = 12.sp)
    }
}

@Composable
fun ComposeCircularProgressBar(
    percentage: Float,
    modifier: Modifier = Modifier,
    fillGradient: List<Color> = listOf(Color(0xFFC661FF), Color(0xFF467BFF)),
    backgroundColor: Color = Color.LightGray,
    strokeWidth: Dp = 12.dp,
) {
    Box(
        modifier = modifier
            .size(175.dp)
            .padding(16.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        Canvas(modifier = Modifier.padding(strokeWidth / 2)) {
            // Background Line
            drawArc(
                color = backgroundColor,
                135f,
                270f,
                false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Butt)
            )
            // Gradient Fill Line
            val fillShader = Brush.linearGradient(
                colors = fillGradient,
                start = Offset(center.x - size.width / 2, center.y),
                end = Offset(center.x + size.width / 2, center.y)
            )
            // Fill Line
            drawArc(
                brush = fillShader,
                135F,
                270f * percentage,
                false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            // Draw the text centered below the arc
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "72%",
                modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
                fontSize = 30.sp,
            )
            Text(
                "Humidity",
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                fontSize = 15.sp,
            )
        }
    }
}


fun AnimateMenu() {
    TODO("Not yet implemented")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityTimeItem(modifier: Modifier = Modifier) {
    var selected = remember {
        mutableStateOf(false)
    }
    var selectedModifier: Modifier
    if (selected.value) {
        selectedModifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color(0xFF467BFF), Color(0xFFC661FF)
                    )
                )
            )
            .padding(16.dp)
    } else {
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
        border = BorderStroke(1.dp, Color.Gray),
        onClick = { selected.value = !selected.value },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = selectedModifier
        ) {
            Text("12:00am", fontSize = 12.sp)
            Image(
                painter = painterResource(id = R.drawable.rain_cloud_sun), contentDescription = ""
            )
            Text("${temperature.padStart(2, '0')}°")
        }
    }
}

@Composable
private fun CityTimeList(
    cities: List<City>, modifier: Modifier = Modifier
) { //TODO only 1 item could be selected
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(items = cities, key = { city ->
            city.name
        }) { city ->
            CityTimeItem()
        }

    }
}


@Composable
fun NextDayItem(nextDay: RowData, modifier: Modifier = Modifier) {
    Divider(
        color = Color.LightGray, thickness = 1.dp, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(70.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(nextDay.date, fontSize = 12.sp)
        Image(
            painter = painterResource(nextDay.imgSrc),
            modifier = Modifier.size(20.dp),
            contentDescription = ""
        )
        Text(nextDay.temp, fontSize = 12.sp)
    }
}

@Composable
private fun NextDayList(nextDays: List<RowData>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp),
    ) {
        nextDays.forEach { nextDay ->
            NextDayItem(nextDay)
        }
    }
}

@Composable
fun SunsetCircle(modifier: Modifier = Modifier, sunRise: String, sunSet: String) {
    val dashPathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    val context = LocalContext.current
    // Calculate the angle for the sun image
    val sunAngle = 100f

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Text(sunRise)
        Box(
            modifier = modifier
                .size(200.dp)
                .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 0.dp)
                .wrapContentSize(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 58.dp)
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sunrise),
                    modifier = Modifier.size(40.dp),
                    contentDescription = ""
                )
                Text("sunrise", color = Color.LightGray, fontSize = 12.sp)
//                Spacer(modifier = Modifier.height(80.dp))
            }
            Canvas(
                modifier = Modifier.size(200.dp)
            ) {
                val centerX = size.width / 2
                val centerY = size.height / 2
                val radius = size.width / 2

                // Draw the dashed half of the circle
                drawPath(
                    path = Path().apply {
                        moveTo(centerX, centerY)
                        arcTo(
                            Rect(
                                centerX - radius,
                                centerY - radius,
                                centerX + radius,
                                centerY + radius
                            ), 180f, sunAngle, true
                        )
                    },
                    color = Color(android.graphics.Color.parseColor("#FCD038")),
                    style = Stroke(width = 2.dp.toPx(), pathEffect = dashPathEffect)
                )

                // Draw the dashed half of the circle
                drawPath(
                    path = Path().apply {
                        moveTo(centerX, centerY)
                        arcTo(
                            Rect(
                                centerX - radius,
                                centerY - radius,
                                centerX + radius,
                                centerY + radius
                            ), sunAngle, -180f, true
                        )
                    },
                    color = Color.LightGray,
                    style = Stroke(width = 2.dp.toPx(), pathEffect = dashPathEffect)
                )

                // Draw the filled half of the circle
                drawPath(
                    path = Path().apply {
                        moveTo(centerX, centerY)
                        arcTo(
                            Rect(
                                centerX - radius,
                                centerY - radius,
                                centerX + radius,
                                centerY + radius
                            ), 0f, 180f, true
                        )
                    }, color = Color.LightGray, style = Stroke(width = 2.dp.toPx())
                )
                // Calculate the extended line length
                val linePadding = 16.dp.toPx()
                val extendedLineLength = radius * 1.5f - linePadding * 2

                // Draw the extended horizontal line
                drawLine(
                    start = Offset(centerX - extendedLineLength, centerY),
                    end = Offset(centerX + extendedLineLength, centerY),
                    color = Color.Black,
                    strokeWidth = 2.dp.toPx()
                )

                // Calculate the position for the sun image on the arc
                val arcRadius =
                    radius - 20.dp.toPx() // Adjusted radius to account for sun image size
                val arcCenterX = centerX
                val arcCenterY = centerY - arcRadius / 6
                val sunX =
                    arcCenterX - arcRadius * kotlin.math.cos(Math.toRadians(sunAngle.toDouble()))
                        .toFloat()
                val sunY =
                    arcCenterY - arcRadius * kotlin.math.sin(Math.toRadians(sunAngle.toDouble()))
                        .toFloat()

                val drawableResource = R.drawable.yellow_sun
                val drawable = ContextCompat.getDrawable(context, drawableResource)
                val bitmap = drawable?.toBitmap()
                val imageBitmap: ImageBitmap =
                    bitmap?.asImageBitmap() ?: ImageBitmap(1, 1) // Default fallback
                drawImage(
                    image = imageBitmap,
                    topLeft = Offset(sunX - 20.dp.toPx(), sunY - 20.dp.toPx()),
                    alpha = 1.0f,
                )
            }

            Column(
                modifier = Modifier
                    .padding(top = 64.dp)
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("sunset", color = Color.LightGray, fontSize = 12.sp)
                Image(
                    painter = painterResource(id = R.drawable.sunset),
                    modifier = Modifier.size(40.dp),
                    contentDescription = ""
                )
            }
        }
        Text(sunSet)
    }
}


@Preview(showBackground = true)
@Composable
fun SunsetCirclePreview() {
    WeatherTheme {
        SunsetCircle(sunRise = "06:00", sunSet = "12:00")
    }
}

@Preview(showBackground = true)
@Composable
fun CityWeatherScreenPreview() {
    WeatherTheme {
        CityWeatherScreen(daysData = rowDataList)
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
        NextDayItem(RowData("Monday, Feb 20", R.drawable.sun, "14°/12°"))
    }
}

@Preview(showBackground = true)
@Composable
fun ArcProgressPreview() {
    WeatherTheme {
        ComposeCircularProgressBar(percentage = 0.72f)
    }
}