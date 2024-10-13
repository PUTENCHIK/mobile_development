package smoothing_weather_graph
import java.io.File

fun readFile(): DoubleArray {
    val filePath = "src/smoothing_weather_graph/in.txt"
    val file = File(filePath).readLines()

    var data = doubleArrayOf()
    file[1].split(' ').map {
        data += it.toDouble()
    }

    return data
}

fun printArray(array: DoubleArray): Unit {
    array.map {
        print(it.toString() + " ")
    }
    println()
}

fun Double.round(accuracy: Int = 7): Double {
    var multiplier = 1.0
    repeat(accuracy) { multiplier *= 10 }
    return (this * multiplier).toLong() / multiplier
}

fun main() {
    val data = readFile()
    val smoothed = data.copyOf()
    print("In data: ")
    printArray(data)

    for (i in 1..data.size-2) {
        smoothed[i] = (data.slice(i-1..i+1).sum() / 3).round()
    }

    print("Answer: ")
    printArray(smoothed)
}