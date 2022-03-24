package codes.draeger.hotels.model

data class Room(
    val roomNumber: Int,
    var status: RoomStatus = RoomStatus.FREE
)
enum class RoomStatus {
    FREE,
    NEEDS_CLEANING,
    OCCUPIED
}
