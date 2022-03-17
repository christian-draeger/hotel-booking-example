package codes.draeger.hotels.model

import java.util.*

data class Hotel(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val address: Address,
    var reviews: MutableList<Review> = mutableListOf(),
)

data class Address(
    val street: String,
    val number: String,
    val zipCode: String,
    val city: String
)

data class Review(
    val message: String,
    val stars: Int
) {
    init {
        require(stars in 1..5) { "ratings are only allowed to be in range between 1 and 5" }
    }
}

fun aDummyHotel(
    name: String = "Holiday-Inn",
    street: String = "Vacation street",
    number: String = "35B",
    zipCode: String = "12345",
    city: String = "Berlin"
) = Hotel(
    name = name,
    address = Address(
        street = street,
        number = number,
        zipCode = zipCode,
        city = city
    )
)