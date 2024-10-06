import java.util.*
import kotlin.math.max
import kotlin.math.min

fun main() {
    while (true) {
        println("Enter: k x y")
        val scanner = Scanner(System.`in`)
        val k = scanner.nextInt()
        val x = scanner.nextInt()
        val y = scanner.nextInt()

        var answer: Int
        answer = max(x, y)
        while (answer < k || answer - min(x,y) < 2) {
            answer++
        }
        println("Answer: ${answer - max(x, y)}\n")
    }

}