package codes.draeger.hotels.model

import java.util.*

data class Hotel(
    val id: Int = 0,
    val name: String,
    val address: Address,
    var reviews: MutableList<Review> = mutableListOf(),
    var rooms: MutableList<Room> = mutableListOf()
)

data class Address(
    val street: String,
    val number: String,
    val zipCode: String,
    val city: String
)
