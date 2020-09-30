package search

import java.io.File
import kotlin.system.exitProcess


fun main(args: Array<String>) {
    if (args.indexOf("--data") > -1) {
        buildDirectory(args[args.indexOf("--data") + 1])
    } else println("Invalid input")

    showMenu()
}

fun buildDirectory(file: String) = Directory.fromFile(file)

fun showMenu() {
    while (true) {
        println("\n=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit".trimIndent())

        when(readLine()!!) {
            "1" -> selectMatchingStrategy()
            "2" -> printAllPeople()
            "0" -> exit()
            else -> println("Invalid option!")
        }
    }
}

fun selectMatchingStrategy() {
    println("Select a matching strategy: ALL, ANY, NONE")
    when(readLine()!!.toUpperCase()) {
        "ALL" -> searchForAll()
        "ANY" -> searchForAny()
        "NONE" -> searchForNone()
        else -> println("No valid matching strategy!")
    }
}

fun searchForAll() {
    println("Enter a name or email to search all suitable people.")
    val entry = readLine()!!.toLowerCase().replace("> ", "")
    val results = Directory.searchAll(entry)

    if (results.isEmpty()) {
        println("No matching people found.")
    } else {
        println("${results.size} persons found:")
        results.forEach(::println)
    }
}

fun searchForAny() {
    println("Enter a name or email to search all suitable people.")
    val entry = readLine()!!.toLowerCase().replace("> ", "").split(" ")
    val results = mutableListOf<String>()
    entry.forEach {
        results += Directory.searchAny(it)
    }

    if (results.isEmpty()) {
        println("No matching people found.")
    } else {
        println("${results.size} persons found:")
        results.forEach(::println)
    }
}

fun searchForNone() {
    println("Enter a name or email to search all suitable people.")
    val entry = readLine()!!.toLowerCase().replace("> ", "").split(" ")
    val results = Directory.list
    val testResult = mutableListOf<String>()
    entry.forEach {
        testResult += Directory.searchAny(it)
    }

    if (results.isEmpty()) {
        println("No matching people found.")
    } else {
        println("${(results - testResult).size} persons found:")
        (results - testResult).forEach(::println)
    }
}

private fun printAllPeople() {
    println("\n=== List of people ===")
    Directory.list.forEach(::println)
}

private fun exit() {
    println("\nBye!")
    exitProcess(1)
}

object Directory {
    val list = mutableListOf<String>()
    private val index = mutableMapOf<String, MutableList<Int>>()

    fun fromFile(file: String) {
        File(file).forEachLine {
            list.add(it)
            val lineIndex = list.lastIndex
            it.toLowerCase().split(" ").forEach { fragment ->
                if ((index.containsKey(fragment))) {
                    index[fragment]!!.add(lineIndex)
                } else {
                    index[fragment] = mutableListOf(lineIndex)
                }
            }
        }
    }
    fun searchAny(query: String): List<String> {
        return list.filterIndexed { i, _ -> index[query.toLowerCase()]?.contains(i) ?: false }
    }

    fun searchAll(query: String): List<String> {
        return list.filterIndexed {i, _-> index[query.toLowerCase()]?.equals(i) ?: false }
    }
}


