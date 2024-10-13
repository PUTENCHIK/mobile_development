package very_important_guests
import java.io.File

fun readFile(): Map<Int, Map<String, Int>> {
    val data = mutableMapOf<
            Int, Map<String, Int>
    >()

    val filePath = "src/very_important_guests/in.txt"
    val file = File(filePath).readLines()

    val t = file[0].toInt()
    for (i in 1..t) {
        val values = file[i].split(' ')
        data.put(i, mutableMapOf("n" to values[0].toInt(), "m" to values[1].toInt()))
    }

    return data
}

fun printMap(dict: Map<Int, Map<String, Int>>): Unit {
    dict.map {
        println("${it.key.toString()}: {n: ${it.value["n"]}, m: ${it.value["m"]}}")
    }
}

fun main() {
    val data = readFile()
    var places: Places
    var guests: IntArray
    var n: Int
    var m: Int

    data.map {
        n = it.value["n"] ?: 1
        m = it.value["m"] ?: 1
        places = Places(n, m)
        guests = IntArray(n*m) { n*m - it }

        var label = 0
        var i = -1
        var j = label+1
        for (guest in guests) {
            while (true) {
                i++
                j--
                if (i >= n || j < 0) {
                    label++
                    i = 0
                    j = label
                }
                if (i < 0 || j >= m)
                    continue
                if (places.isFree(i, j)) {
                    places.setQuest(i, j, guest)
                    break
                }
            }
        }
        println(places)
    }
}