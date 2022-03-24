package codes.draeger.hotels.service

import codes.draeger.hotels.model.Hotel
import codes.draeger.hotels.repository.HotelRepository
import codes.draeger.hotels.repository.enties.toHotelEntity
import org.springframework.stereotype.Service

@Service
class HotelService(
    private val hotelRepository: HotelRepository
) {
    fun addHotel(hotel: Hotel) {
        hotelRepository.save(hotel.toHotelEntity())
    }
}