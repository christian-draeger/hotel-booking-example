package codes.draeger.hotels.api

import codes.draeger.hotels.model.Hotel
import codes.draeger.hotels.model.Review
import codes.draeger.hotels.model.*
import codes.draeger.hotels.repository.HotelRepository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

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

    @PostMapping("/add-review/{id}")
    fun addReview(
        @PathVariable id: String,
        @RequestBody body: Review
    ) {
        val hotel = hotelRepository.listAll().find { it.id == id } ?: throw IllegalArgumentException("hotel with id $id not known")
        hotel.reviews.add(body)
        hotelRepository.updateHotel(hotel)
    }

    @PostMapping("/add-room/{id}")
    fun addRoom(
        @PathVariable id: String,
        @RequestBody body: Room
    ) {
        val hotel = hotelRepository.listAll().find { it.id == id }
            ?: throw throw IllegalArgumentException("hotel with id $id not known")
        hotel.rooms.forEach { room ->
         if (room.roomNumber == body.roomNumber) {
            throw Exception("Let's Test the Result")
        }
    }
     hotel.rooms.add(body)
        hotelRepository.updateHotel(hotel)
    }

    @DeleteMapping("/remove/{id}")
    fun removeHotel(
        @PathVariable id: String
    ) {
        hotelRepository.deleteHotel(id)
    }
}
