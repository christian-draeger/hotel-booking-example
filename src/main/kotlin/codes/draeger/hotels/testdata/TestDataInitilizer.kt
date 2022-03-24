package codes.draeger.hotels.testdata

import codes.draeger.hotels.model.aDummyHotel
import codes.draeger.hotels.model.aDummyReview
import codes.draeger.hotels.repository.HotelRepository
import codes.draeger.hotels.repository.ReviewRepository
import codes.draeger.hotels.repository.enties.ReviewEntity
import codes.draeger.hotels.repository.enties.toHotelEntity
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Profile
import org.springframework.context.event.EventListener
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Profile("local")
@Service
class TestDataInitilizer(
    private val hotelRepository: HotelRepository,
    private val reviewRepository: ReviewRepository,
) {

    @Transactional
    fun clearAll() {
        hotelRepository.deleteAll()
    }

    @EventListener
    @Order(Ordered.LOWEST_PRECEDENCE)
    fun initDashboards(event: ApplicationReadyEvent) {
        clearAll()

        val hotel1 = hotelRepository.save(aDummyHotel().toHotelEntity())
        reviewRepository.save(ReviewEntity(hotelId = hotel1.id, message = "was good", stars = 4))
        reviewRepository.save(ReviewEntity(hotelId = hotel1.id, message = "was ok", stars = 3))

        val hotel2 = hotelRepository.save(aDummyHotel(name = "Other Hotel").toHotelEntity())
        reviewRepository.save(ReviewEntity(hotelId = hotel2.id, message = "was bad", stars = 1))
    }
}