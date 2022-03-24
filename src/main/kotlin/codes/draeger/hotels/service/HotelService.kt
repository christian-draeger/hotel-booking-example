package codes.draeger.hotels.service

import codes.draeger.hotels.model.Hotel
import codes.draeger.hotels.repository.HotelRepository
import codes.draeger.hotels.repository.ReviewRepository
import codes.draeger.hotels.repository.enties.HotelEntity
import codes.draeger.hotels.repository.enties.toHotel
import codes.draeger.hotels.repository.enties.toHotelEntity
import org.springframework.stereotype.Service

@Service
class HotelService(
    private val hotelRepository: HotelRepository,
    private val reviewRepository: ReviewRepository,
) {
    fun addHotel(hotel: Hotel) {
        hotelRepository.save(hotel.toHotelEntity())
    }

    fun getAll(): List<Hotel> {
        val hotels = hotelRepository.findAll()
        hotels.forEach {
            it.reviews = reviewRepository.findByHotelId(it.id).toMutableSet()
        }
        return hotels.map { it.toHotel() }
    }

    fun delete(id: Int) {
        hotelRepository.deleteById(id)
    }
}