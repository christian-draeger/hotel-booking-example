package codes.draeger.hotels.api

import codes.draeger.hotels.model.*
import codes.draeger.hotels.repository.HotelRepository
import org.springframework.web.bind.annotation.*

@RestController
class HotelController(
    private val hotelRepository: HotelRepository
) {
    @PostMapping("/add")
    fun addData(
        @RequestBody body: Hotel
    ) {
        hotelRepository.addHotel(body)
    }

    @GetMapping("/all")
    fun getAllHotels() = hotelRepository.listAll()

    @GetMapping("/all/{id}")
    fun getOneHotel(@PathVariable id: String) = hotelRepository.listAll().find { it.id == id }

    @PostMapping("/add-review/{id}")
    fun addReview(
        @PathVariable id: String,
        @RequestBody body: Review
    ) {
        getOneHotel(id)?.run{
            this.reviews.add(body)
            hotelRepository.updateHotel(this)
        } ?: throw IllegalArgumentException("hotel with id $id not known")


    }
    @PostMapping("/add-room/{id}")
    fun addRoom(
        @PathVariable id: String,
        @RequestBody body: Int
    ) {
        getOneHotel(id)?.run{
            this.rooms.add(Room(body))
            hotelRepository.updateHotel(this)
        } ?: throw IllegalArgumentException("hotel with id $id not known")


    }
    @PatchMapping("/all/{hid}/change-status/{rid}")
    fun changeRoomStatus(@PathVariable hid: String,
                         @PathVariable rid: Int,
                         @RequestBody body: RoomStatus){
        getOneHotel(hid)?.run {
            this.rooms.find { it.roomNumber == rid }?.run{
                this.status = body
            } ?: throw IllegalArgumentException("room with number $rid not known")
             hotelRepository.updateHotel(this)
        } ?: throw IllegalArgumentException("hotel with id $hid not known")

    }
    @DeleteMapping("/remove/{id}")
    fun removeHotel(
        @PathVariable id: String
    ) {
        hotelRepository.deleteHotel(id)
    }
}
