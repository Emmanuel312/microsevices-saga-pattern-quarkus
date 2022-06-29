package br.com.emmanuel

import br.com.emmanuel.database.booking.BookingEntity
import br.com.emmanuel.database.booking.BookingRepository
import br.com.emmanuel.entity.BookingCreatedDto
import br.com.emmanuel.kafka.publisher.BookingCreatedKafkaPublisher
import java.time.Duration
import java.time.Instant
import java.util.*
import javax.transaction.Transactional
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/booking")
class BookingController(
    private val bookingRepository: BookingRepository,
    private val bookingCreatedKafkaPublisher: BookingCreatedKafkaPublisher
) {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    suspend fun create(): String {

        val bookingEntity = BookingEntity()

        bookingEntity.bookingUuid = UUID.randomUUID().toString()
        bookingEntity.hotelUuid = UUID.randomUUID().toString()
        bookingEntity.userUuid = UUID.randomUUID().toString()
        bookingEntity.bookingStartDate = Instant.now()
        bookingEntity.bookingEndDate = Instant.now().plus(Duration.ofDays(3))

        bookingRepository.save(bookingEntity)

        val bookingCreatedDto = BookingCreatedDto(
            uuid = bookingEntity.bookingUuid,
            hotelUuid = bookingEntity.hotelUuid,
            userUuid = bookingEntity.userUuid,
            bookingStartDate = bookingEntity.bookingStartDate,
            bookingEndDate = bookingEntity.bookingEndDate
        )

        bookingCreatedKafkaPublisher.send(bookingCreatedDto)
        return "ok"
    }
}