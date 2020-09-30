import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val durationInDays = scanner.nextInt()
    val totalFoodCostPerDay = scanner.nextInt()
    val oneWayFlightCost = scanner.nextInt()
    val costOfOneNightInHotel = scanner.nextInt()

    println(durationInDays * totalFoodCostPerDay + 2 * oneWayFlightCost + ((durationInDays - 1) * costOfOneNightInHotel))
}