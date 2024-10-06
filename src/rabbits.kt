import java.util.*

fun main() {
    while (true) {
        println("Enter: m k")
        val scanner = Scanner(System.`in`)
        val m = scanner.nextInt()
        val k = scanner.nextInt()

        var children: Long
        children = 1
        var adults: Long
        adults = 0
        var buffer: Long

        for (i: Int in 2..m) {
            buffer = adults
            adults += children
            children = buffer * k
//        println("${i}) ${children} + ${adults} = ${children + adults}")
        }
        println("Answer: ${adults + children}\n")
    }
}