package codes.draeger.hotels.api

import codes.draeger.hotels.model.Room
import codes.draeger.hotels.model.RoomStatus
import codes.draeger.hotels.repository.RoomRepository
import codes.draeger.hotels.repository.enties.RoomEntity
import org.springframework.web.bind.annotation.*

@RestController
class RoomController(
    private val roomRepository: RoomRepository
) {
    @PostMapping("/room/add/{hotelId}")
    fun addRoom(
        @PathVariable hotelId: Int,
        @RequestBody body: Room
    ) {
        roomRepository.save(
            RoomEntity(
                roomNumber = body.roomNumber,
                status = body.status,
                hotelId = hotelId
            )
        )
    }

    @PatchMapping("/room/{hotelId}/change-status/{roomId}")
    fun changeRoomStatus(
        @PathVariable hotelId: Int,
        @PathVariable roomId: Int,
        @RequestBody body: RoomStatus
    ) {
        roomRepository.findByHotelId(hotelId).find { it.roomNumber == roomId }?.run {
            this.status = body
            roomRepository.save(this)
        }
    }
}

data class RoomResponse(
    val roomNumber: Int,
    val status: RoomStatus
)

fun Room.toRoomResponse() = RoomResponse(
    roomNumber = roomNumber,
    status = status
)