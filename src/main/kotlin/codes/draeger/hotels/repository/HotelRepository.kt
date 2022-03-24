package codes.draeger.hotels.repository

import codes.draeger.hotels.repository.enties.HotelEntity
import org.springframework.data.jpa.repository.JpaRepository

interface HotelRepository: JpaRepository<HotelEntity, Int>
