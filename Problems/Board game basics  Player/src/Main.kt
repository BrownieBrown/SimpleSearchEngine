data class Player(val id: Int, val name: String, val hp: Int) {
    companion object {
        fun create(name: String): Player {
            val userId = (0..10).random()
            return Player(id = userId, name = name, hp = 100)
        }
    }
}
