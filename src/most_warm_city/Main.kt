package most_warm_city

import java.util.*
import com.google.gson.Gson
import java.net.URL
import java.io.InputStream
import java.io.InputStreamReader
import java.io.FileInputStream


class Weather (val main: Main) {
    override fun toString(): String {
        return "${main.temp}, ${main.feels_like}, ${main.temp_min}, ${main.temp_max}"
    }
}
class Main (val temp: Float,
            val feels_like: Float,
            val temp_min: Float,
            val temp_max: Float) {

//    constructor(temp: Float
//                feels_like: Float
//                temp_min: Float
//                temp_max: Float) {
//        temp
//        this()
//    }
}

fun main() {
    val API_KEY = "0661abd73b6f4f276e7ac7973f1811a1"
    val urlTemplate = "http://api.openweathermap.org/data/2.5/weather?id=%d&appid=$API_KEY"

    val sc = Scanner(FileInputStream("src/most_warm_city/input.txt"))
    val citiesId = arrayListOf<Int>()
    while (sc.hasNextLine()) {
        val line = sc.nextLine()
        if (line != "") {
            citiesId.add(line.toInt())
        }
    }

    val gson = Gson()
    for (id in citiesId) {
        val url = URL(urlTemplate.format(id))
        val stream = url.getContent() as InputStream
        val weather: Weather =
            gson.fromJson(InputStreamReader(stream), Weather::class.java)
        println(weather.toString())
    }
}