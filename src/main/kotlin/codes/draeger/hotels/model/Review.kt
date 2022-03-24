package codes.draeger.hotels.model

data class Review(
    val message: String,
    val stars: Int
) {
    init {
        require(stars in 1..5) { "ratings are only allowed to be in range between 1 and 5" }
    }
}