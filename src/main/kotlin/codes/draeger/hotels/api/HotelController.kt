package codes.draeger.hotels.api

import codes.draeger.hotels.model.*
import codes.draeger.hotels.repository.HotelRepository
import codes.draeger.hotels.service.HotelService
import org.springframework.web.bind.annotation.*

@RestController
class HotelController(
    private val hotelRepository: HotelRepository,
    private val hotelService: HotelService,
) {
    @PostMapping("/add")
    fun addData(
        @RequestBody body: HotelRequest
    ) {
        hotelService.add(body.toHotel())
    }

    @GetMapping("/all")
    fun getAllHotels(): List<Hotel> = hotelService.getAll()

    @GetMapping("/{id}")
    fun getOneHotel(@PathVariable id: Int): HotelResponse = hotelService.getOne(id).toHotelResponse()

    @DeleteMapping("/remove/{id}")
    fun removeHotel(@PathVariable id: Int) {
        hotelService.delete(id)
    }
}

fun Hotel.toHotelResponse() = HotelResponse(
    id = id.toString(),
    name = name,
    address = HotelResponse.Address(
        street = address.street,
        number = address.number,
        zipCode = address.zipCode,
        city = address.city
    ),
    reviews = reviews.map { it.toReviewResponse() },
    rooms = emptyList() // TODO
)

data class HotelRequest(
    val name: String,
    val address: Address
) {
    data class Address(
        val street: String,
        val number: String,
        val zipCode: String,
        val city: String
    )

    fun toHotel() = Hotel(
        name = name,
        address = codes.draeger.hotels.model.Address(
            street = address.street,
            number = address.number,
            zipCode = address.zipCode,
            city = address.city
        )
    )
}

data class HotelResponse(
    val id: String,
    val name: String,
    val address: Address,
    var reviews: List<ReviewResponse>,
    var rooms: List<Room>
) {
    data class Address(
        val street: String,
        val number: String,
        val zipCode: String,
        val city: String
    )
}