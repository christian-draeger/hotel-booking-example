package codes.draeger.hotels.repository

import codes.draeger.hotels.model.RoomStatus
import codes.draeger.hotels.repository.enties.RoomEntity
import codes.draeger.hotels.repository.enties.toHotelEntity
import codes.draeger.hotels.testdata.aDummyHotel
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import strikt.api.expectThat
import strikt.assertions.containsExactly

@DataJpaTest
class RoomRepositoryTest(
    @Autowired private val roomRepository: RoomRepository,
    @Autowired private val hotelRepository: HotelRepository)
{

    @Test
    internal fun `can find room by hotel id`() {
        val hotel = hotelRepository.save(aDummyHotel().toHotelEntity())
        val room = roomRepository.save(
            RoomEntity(
            roomNumber = 200, status = RoomStatus.FREE,
            hotelId = hotel.id
        )
        )

        val rooms = roomRepository.findByHotelId(hotel.id)
        expectThat(rooms).containsExactly(room)
        roomRepository.deleteAll()
	hotelRepository.deleteAll()
        
    }
    @Test
    internal fun `can change status of a hotel room`() {
        val hotel = hotelRepository.save(aDummyHotel().toHotelEntity())
        val room = roomRepository.save(
            RoomEntity(
                roomNumber = 200, status = RoomStatus.FREE,
                hotelId = hotel.id
            )
        )
        val roomEntity = roomRepository.findByHotelId(hotel.id).find { it.roomNumber == room.roomNumber }
        if (roomEntity != null) {
            roomEntity.status = RoomStatus.NEEDS_CLEANING
            roomRepository.save(roomEntity)
            expectThat(roomEntity.status).equals(RoomStatus.NEEDS_CLEANING)
        }
	roomRepository.deleteAll()
        hotelRepository.deleteAll()
        
    }
}