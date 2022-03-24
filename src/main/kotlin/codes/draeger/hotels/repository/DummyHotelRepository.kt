package codes.draeger.hotels.repository

import codes.draeger.hotels.model.Hotel
import org.springframework.stereotype.Repository

@Repository
class DummyHotelRepository {
    private val hotels = mutableListOf<Hotel>()
    fun addHotel(hotel: Hotel) {
        hotels.add(hotel)
    }

    fun updateHotel(hotel: Hotel) {
        deleteHotel(hotel.id)
        addHotel(hotel)
    }

    fun listAll(): MutableList<Hotel> = hotels
    fun deleteHotel(id: String) {
        getHotelById(id)?.run {
            hotels.remove(this)
        }
    }

    fun deleteAll() {
        hotels.clear()
    }

    private fun getHotelById(id: String) = hotels.find { it.id == id }

}
