package br.com.emmanuel.database.booking

import br.com.emmanuel.database.booking.BookingStatus.PENDING
import java.time.Instant
import javax.persistence.*

@Table(name = "booking")
@Entity
class BookingEntity {
    @Id
    @GeneratedValue
    var id: Long? = null
    lateinit var bookingUuid: String
    lateinit var hotelUuid: String
    lateinit var userUuid: String
    lateinit var bookingStartDate: Instant
    lateinit var bookingEndDate: Instant

    @Enumerated(EnumType.STRING)
    var status: BookingStatus = PENDING
}
