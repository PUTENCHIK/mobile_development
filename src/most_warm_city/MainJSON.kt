package most_warm_city

class MainJSON(var temp: Float) {
    fun toCelsius(value: Float): Float = value - 273.15f
}