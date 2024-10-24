package most_warm_city

import java.util.*
import com.google.gson.Gson
import java.net.URL
import java.io.InputStream
import java.io.InputStreamReader
import java.io.FileInputStream

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
    val cities = sortedMapOf<Float, String>()
    for (id in citiesId) {
        val url = URL(urlTemplate.format(id))
        val stream = url.getContent() as InputStream
        val weather: Weather = gson.fromJson(
            InputStreamReader(stream),
            Weather::class.java)

        cities[weather.getTemp()] = weather.name
    }

    val answer = mutableListOf<String>()
    cities.map { answer.add("") }

    var i = cities.size - 1
    for (city in cities) {
        answer[i] = "${city.value}: ${city.key}"
        i--
    }

    answer.map { println(it) }
}