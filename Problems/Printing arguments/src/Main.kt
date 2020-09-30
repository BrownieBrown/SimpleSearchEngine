fun main(args: Array<String>) {
    if (args.size != 3) {
        println("Invalid number of program arguments")
    } else {
        args.forEach { println("Argument ${args.indexOf(it) + 1}: $it. It has ${it.count()} characters") }
    }
}
