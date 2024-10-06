import java.util.*

fun countVowels(s: String): Int = s.count { it in "aouei" }

fun main() {
    println(countVowels("Hello, teacher!"))
    println(countVowels("No, no, no, mister Danny"))
}