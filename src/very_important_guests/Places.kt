package very_important_guests

class Places(val n: Int, val m: Int) {
    val matrix = createMatrix()

    fun createMatrix(): Array<IntArray> {
        var g: Array<IntArray> = arrayOf()
        for (i in 1..n)
            g += IntArray(m)

        return g
    }

    fun setQuest(i: Int, j: Int, g: Int): Unit {
        matrix[i][j] = g
    }

    fun isFree(i: Int, j: Int): Boolean {
        return matrix[i][j] == 0
    }

    override fun toString(): String {
        var string = ""
        matrix.map {
            for (i in 0..it.size-1) {
                string += "\t" + it[i].toString()
            }
            string += "\n"
        }

        return string
    }
}