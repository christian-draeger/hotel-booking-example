package codes.draeger.hotels.model

data class Hotel(
    val id: Int = 0,
    val name: String,
    val address: Address,
    var reviews: MutableList<Review> = mutableListOf(),
    var rooms: MutableList<Room> = mutableListOf()
)

data class Address(
    val street: String,
    val number: String,
    val zipCode: String,
    val city: String
)

data class Review(
    val message: String,
    val stars: Int
) {
    init {
        require(stars in 1..5) { "ratings are only allowed to be in range between 1 and 5" }
    }
}
data class Room(
    val roomNumber: Int,
    var status: RoomStatus = RoomStatus.FREE
)
enum class RoomStatus {
    FREE,
    NEEDS_CLEANING,
    OCCUPIED
}
fun aDummyHotel(
    name: String = "Holiday-Inn",
    street: String = "Vacation street",
    number: String = "35B",
    zipCode: String = "12345",
    city: String = "Berlin"
) = Hotel(
    name = name,
    address = Address(
        street = street,
        number = number,
        zipCode = zipCode,
        city = city
    )
)
fun aDummyRoom(
    roomNumber: Int = 101,
    status: RoomStatus = RoomStatus.FREE
) = Room(
    roomNumber = roomNumber,
    status = status
)
fun aDummyReview(
    message: String = "great",
    stars: Int = 4
) = Review(
    message = message,
    stars = stars
)
