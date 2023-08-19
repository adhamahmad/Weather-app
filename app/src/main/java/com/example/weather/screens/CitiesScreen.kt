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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.data.City
import com.example.weather.ui.theme.WeatherTheme

@Composable
fun CitiesScreen(modifier: Modifier = Modifier) {
    val cityList = listOf(
        City("Tokyo", "06", R.drawable.spark_cloud,listOf(Color(0xFF29FF96), Color(0xFF2AC9B3))),
        City("New York", "19", R.drawable.sun_wind,listOf(Color(0xFFFF7074), Color(0xFFCA03DF))),
        City("London", "13", R.drawable.rain_cloud_sun,listOf(Color(0xFFC661FF), Color(0xFF467BFF)))
        // Add more cities here if needed
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Find Location", textAlign = TextAlign.Left,modifier=Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))
        SearchBar()
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Cities", textAlign = TextAlign.Left,modifier=Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))
        CityList(cities = cityList, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(35.dp))
        CityListButton()


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = "",
        placeholder = { Text("Search for your location", color = Color.Gray) },
        onValueChange = {},
        textStyle = TextStyle(color = Color.Black),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        shape = RoundedCornerShape(128.dp),
        keyboardActions = KeyboardActions.Default,
        modifier = modifier
            .background(
                color = Color.White,
            )
            .fillMaxWidth(),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = Color.Gray,
                contentDescription = null
            )
        }
    )
}

@Composable
fun CityListItem(city: City, modifier: Modifier = Modifier) {
    val gradientColors = city.gradientColors
    val roundedCornerShape = RoundedCornerShape(
        topStartPercent = 15,
        topEndPercent = 45,
        bottomStartPercent = 45,
        bottomEndPercent = 15,
    )
    Card(
        elevation = CardDefaults.cardElevation(),
        shape = roundedCornerShape,

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,

            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(gradientColors)
                )
                .fillMaxWidth()
                .padding(20.dp)

                 // Aligns the content at the start of the card
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "${city.temp.padStart(2, '0')}°",
                    color = Color.White,
                    fontSize = 40.sp, // Adjust the text size as needed
                )
                Text(
                    text = city.name,
                    color = Color.White,
                    fontSize = 30.sp, // Adjust the text size as needed
                )
            }
            Image(
                painter = painterResource(city.imgSrc),
                contentDescription = city.name,
                modifier = Modifier
                    .size(90.dp) // Adjust the image size as needed
                    // Aligns the image at the end of the row
            )
        }

    }

}

@Composable
private fun CityList(cities: List<City>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(35.dp),
    ){
        items(
            items = cities,
            key = { city ->
                city.name
            }
        ){city ->
            CityListItem(city = city,)
        }
    }
}

@Composable
fun CityListButton(modifier: Modifier = Modifier) {
    val roundedCornerShape = RoundedCornerShape(
        topStartPercent = 15,
        topEndPercent = 45,
        bottomStartPercent = 45,
        bottomEndPercent = 15,
    )
    Card(
        shape = roundedCornerShape,
        border = BorderStroke(1.dp,Color.Gray),
        ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    color = Color.White,
                )
                .fillMaxWidth()
                .padding(30.dp)

            // Aligns the content at the start of the card
        ) {

            FloatingActionButton(
                containerColor = Color.LightGray,
                modifier = Modifier
                    .size(40.dp),
                shape = CircleShape,
                onClick = {},

            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Custom FAB Image",
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Add city")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun CitiesScreenPreview(){
    WeatherTheme {
        CitiesScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun CitiesItemPreview(){
    WeatherTheme {
        CityListItem(city = City("Tokyo","06",R.drawable.spark_cloud,listOf(Color(0xFF29FF96), Color(0xFF2AC9B3))))
    }
}

@Preview(showBackground = true)
@Composable
fun CityListButtonPreview(){
    WeatherTheme {
        CityListButton()
    }
}