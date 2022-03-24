package codes.draeger.hotels.repository

import codes.draeger.hotels.repository.enties.ReviewEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<ReviewEntity, String> {
    fun findByHotelId(id: Int): List<ReviewEntity>
}
