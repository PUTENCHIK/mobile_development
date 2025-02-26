package com.example.fragmentweatherapp.data

data class Data(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Long,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

data class Clouds(
    val all: Int
)

data class Coord(
    val lat: Float,
    val lon: Float
)

data class Main(
    val feels_like: Float,
//    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
//    val sea_level: Int,
    val temp: Float,
//    val temp_max: Float,
//    val temp_min: Float
)

data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class Wind(
    val deg: Int,
//    val gust: Int,
    val speed: Float
)
