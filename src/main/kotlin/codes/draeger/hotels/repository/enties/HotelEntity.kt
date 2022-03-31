package codes.draeger.hotels.repository.enties

import codes.draeger.hotels.model.Address
import codes.draeger.hotels.model.Hotel
import codes.draeger.hotels.model.Review
import java.util.*
import javax.persistence.*


@Table(name = "hotels")
@Entity(name = "hotels")
class HotelEntity( // HotelDAO
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    var name: String,
    @Embedded
    var address: AddressEntity,

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "hotel_id")
    var reviews: MutableSet<ReviewEntity>,
    
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "hotel_id")
    var rooms: MutableSet<RoomEntity>
)

@Embeddable
class AddressEntity(
    var street: String,
    var number: String,
    var zipCode: String,
    var city: String
)

fun HotelEntity.toHotel(): Hotel {
    return Hotel(
        name = name,
        address = Address(
            street = address.street,
            number = address.number,
            zipCode = address.zipCode,
            city = address.city
        ),
        reviews = reviews.map { it.toReview() }.toMutableList(),
	rooms = rooms.map { it.toRoom() }.toMutableList()
    )
}

fun Hotel.toHotelEntity(): HotelEntity {
    return HotelEntity(
        name = name,
        address = AddressEntity(
            street = address.street,
            number = address.number,
            zipCode = address.zipCode,
            city = address.city
        ),
        reviews = mutableSetOf(),
        rooms = mutableSetOf()
    )
}
