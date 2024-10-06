import java.util.*

fun print_array(arr: LongArray) {
    print('[')
    for (r in arr) {
        print(r)
        print(',')
    }
    println(']')
}

fun main() {
    val scanner = Scanner(System.`in`)
    println("Enter M N:")
    val M = scanner.nextInt()
    val N = scanner.nextInt()

    var rabbits = LongArray(M)
    rabbits[0] = 1

    for (n in 2..N) {
        var adult = rabbits.slice(1..M-1).sum()
        for (i in M-1 downTo 1) {
            rabbits[i] = rabbits[i-1]
        }
        rabbits[0] = adult
        print(n)
        print(") Rabbits: ")
        print_array(rabbits)
    }
    print("All rabbits: ")
    println(rabbits.sum())
}