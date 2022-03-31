package codes.draeger.hotels.repository

import codes.draeger.hotels.repository.enties.RoomEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RoomRepository : JpaRepository<RoomEntity, String> {
    fun findByHotelId(id: Int): List<RoomEntity>
}