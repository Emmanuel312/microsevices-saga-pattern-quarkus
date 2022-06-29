package br.com.emmanuel.kafka.consumer

import br.com.emmanuel.database.booking.BookingRepository
import br.com.emmanuel.database.booking.BookingStatus.CANCELLED
import br.com.emmanuel.database.booking.BookingStatus.CONFIRMED
import br.com.emmanuel.kafka.dto.BookingAvailableDto
import br.com.emmanuel.kafka.dto.BookingNotAvailableDto
import io.smallrye.reactive.messaging.kafka.Record
import org.eclipse.microprofile.reactive.messaging.Incoming
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class BookingStatusChangeHandler(
    private val bookingRepository: BookingRepository
) {

    @Incoming("booking_available")
    suspend fun changeBookingStatusToConfirmed(bookingAvailableDto: Record<String, BookingAvailableDto>) {
        bookingRepository.changeStatus(bookingAvailableDto.value().bookingUuid, CONFIRMED)
    }

    @Incoming("booking_not_available")
    suspend fun changeBookingStatusToCancelled(bookingNotAvailableDto: Record<String, BookingNotAvailableDto>) {
        bookingRepository.changeStatus(bookingNotAvailableDto.value().bookingUuid, CANCELLED)
    }
}