package codes.draeger.hotels.api

import codes.draeger.hotels.model.*
import codes.draeger.hotels.repository.ReviewRepository
import codes.draeger.hotels.repository.entities.ReviewEntity
import org.springframework.web.bind.annotation.*

@RestController
class ReviewController(
    private val reviewRepository: ReviewRepository,
) {

    @PostMapping("/review/add/{hotelId}")
    fun addReview(
        @PathVariable hotelId: Int,
        @RequestBody body: Review
    ) {
        reviewRepository.save(
            ReviewEntity(
                message = body.message,
                stars = body.stars,
                hotelId = hotelId
            )
        )
    }

    @GetMapping("/review/all/{hotelId}")
    fun getAllReviewsOfAHotel(
        @PathVariable hotelId: Int
    ) = reviewRepository.findByHotelId(hotelId)
}

data class ReviewResponse(
    val message: String,
    val stars: Int
)

fun Review.toReviewResponse() = ReviewResponse(
    message = message,
    stars = stars
)