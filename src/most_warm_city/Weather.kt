package most_warm_city

class Weather (val main: MainJSON) {
    val name: String = ""

    fun getTemp(): Float = main.toCelsius(main.temp)
    override fun toString(): String {
        return "${getTemp()}"
    }
}