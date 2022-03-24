package codes.draeger.hotels.api

import codes.draeger.hotels.model.RoomStatus
import com.sun.xml.bind.v2.TODO
import org.springframework.web.bind.annotation.*

@RestController
class RoomController(
    // private val roomRepository: RoomRepository,
) {
    @PostMapping("/room/status/{hotelId}")
    fun addRoom(
        @PathVariable hotelId: Int,
        @RequestBody body: RoomStatus
    ) {
        // TODO
    }


    @PostMapping("/room/add/{hotelId}")
    fun addRoom(
        @PathVariable hotelId: Int,
        @RequestBody body: RoomRequest
    ) {
        // TODO
    }

    @PatchMapping("/room/change-status/{roomId}")
    fun changeRoomStatus(
        @PathVariable roomId: String,
        @RequestBody body: RoomRequest
    ) {
        // TODO
    }
}

data class RoomRequest(
    val todo: TODO
)
