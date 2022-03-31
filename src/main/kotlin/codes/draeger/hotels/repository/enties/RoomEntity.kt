package codes.draeger.hotels.repository.enties

import codes.draeger.hotels.model.Room
import codes.draeger.hotels.model.RoomStatus
import java.util.*
import javax.persistence.*

@Table(name = "rooms")
@Entity(name = "rooms")
class RoomEntity(
    @Id
    @Column(name = "roomNumber")
    var roomNumber: Int,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: RoomStatus,

    @Column(name = "hotel_id")
    var hotelId: Int,
){
    @Temporal(TemporalType.TIMESTAMP)
    var created: Date? = null

    @Temporal(TemporalType.TIMESTAMP)
    var updated: Date? = null

    @PrePersist
    fun updateCreated() {
        created = Date()
    }

    @PreUpdate
    fun updateUpdated() {
        updated = Date()
    }
}

fun RoomEntity.toRoom() = Room(
    roomNumber = roomNumber,
    status = status
)
