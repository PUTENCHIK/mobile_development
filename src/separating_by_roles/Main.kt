package separating_by_roles

import java.io.FileInputStream
import java.util.*

fun main() {
    val rolesLabel = "roles:"
    val linesLabel = "textLines:"

    val sc = Scanner(FileInputStream("src/separating_by_roles/input.txt"))
    val roles = arrayListOf<String>()
    val lines = arrayListOf<String>()

    var arr = roles
    while (sc.hasNextLine()) {
        val line = sc.nextLine()
        if (line == rolesLabel) {
            continue
        } else if (line == linesLabel) {
            arr = lines
            continue
        } else {
            if (line != "") {
                arr.add(line)
            }
        }
    }

    //                      Роль => [{0 => Реплика, 1 => Реплика,}]
    val replics = mutableMapOf<String, MutableList<MutableMap<Int, String>>>()
    for (i in 0..lines.size-1) {
        val row = lines[i].split(": ", limit=2)
        if (row.size != 2)
            throw Error("Wrong format of text line ${i+1}")

        val role = row[0]
        val line = row[1]
        if (roles.contains(role).not())
            throw Error("Found role '$role' which isn't in roles")

        if (role in replics) {
            replics[role]?.add(mutableMapOf(i+1 to line))
        } else {
            replics.put(role, mutableListOf(mutableMapOf(i+1 to line)))
        }
    }

    for (role in roles) {
        if ((role in replics.keys).not()) {
            replics[role] = mutableListOf()
        }
    }

    for (role in replics) {
        println("${role.key}:")
        val arr = role.value
        for (line in arr) {
            for (replic in line) {
                println("${replic.key}) ${replic.value}")
            }
        }
        println()
    }
}
