package codes.draeger.hotels.repository.entities

import codes.draeger.hotels.model.Room
import java.util.*
import javax.persistence.*

@Table(name = "room")
@Entity(name = "room")
class roomEntity(
    @Id
    var id: String = UUID.randomUUID().toString(),

    var roomNumber: Int,

    var status: RoomStatus = RoomStatus.FREE
)
enum class RoomStatus {
    FREE,
    NEEDS_CLEANING,
    OCCUPIED
}