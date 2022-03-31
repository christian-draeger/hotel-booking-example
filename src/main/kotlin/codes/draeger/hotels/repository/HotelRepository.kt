package codes.draeger.hotels.repository

import codes.draeger.hotels.repository.entities.HotelEntity
import org.springframework.data.jpa.repository.JpaRepository

interface HotelRepository: JpaRepository<HotelEntity, Int>
