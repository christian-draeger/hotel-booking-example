package codes.draeger.hotels.repository

import codes.draeger.hotels.repository.entities.RoomEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<RoomEntity, Int> {
    fun findByHotelId(id: Int): List<RoomEntity>
}
