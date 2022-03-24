package codes.draeger.hotels.repository.enties

import java.util.*
import javax.persistence.*

@Table(name = "reviews")
@Entity(name = "reviews")
class ReviewEntity(
    @Id
    var id: String = UUID.randomUUID().toString(),

    @Column(name = "message", columnDefinition = "TEXT")
    var message: String? = null,

    @Column(name = "stars")
    var stars: Int,

    @Column(name = "hotel_id")
    var hotelId: Int,
){
    @Temporal(TemporalType.TIMESTAMP)
    var created: Date? = null

    @Temporal(TemporalType.TIMESTAMP)
    var updated: Date? = null

    @PrePersist
    fun updateCreated() {
        created = Date()
    }

    @PreUpdate
    fun updateUpdated() {
        updated = Date()
    }
}