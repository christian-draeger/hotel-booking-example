package codes.draeger.hotels.repository

import codes.draeger.hotels.repository.enties.ReviewEntity
import codes.draeger.hotels.repository.enties.toHotelEntity
import codes.draeger.hotels.testdata.aDummyHotel
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import strikt.api.expectThat
import strikt.assertions.containsExactly

@DataJpaTest
class ReviewRepositoryTest(
    @Autowired private val reviewRepository: ReviewRepository,
    @Autowired private val hotelRepository: HotelRepository,

) {
    @Test
    internal fun `can find review by hotel id`() {
        val hotel = hotelRepository.save(aDummyHotel().toHotelEntity())
        val review = reviewRepository.save(ReviewEntity(
            message = "a test",
            stars = 3,
            hotelId = hotel.id
        ))

        val reviews = reviewRepository.findByHotelId(hotel.id)
        expectThat(reviews).containsExactly(review)
    }
}