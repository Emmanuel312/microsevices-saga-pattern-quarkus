package br.com.emmanuel.kafka.consumer

import br.com.emmanuel.kafka.dto.BookingCreatedDto
import br.com.emmanuel.kafka.dto.HotelAvailableDto
import br.com.emmanuel.kafka.dto.HotelNotAvailableDto
import br.com.emmanuel.kafka.producer.HotelAvailableSender
import br.com.emmanuel.kafka.producer.HotelNotAvailableSender
import io.smallrye.reactive.messaging.kafka.Record
import org.eclipse.microprofile.reactive.messaging.Incoming
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class BookingCreatedListener(
    private val hotelAvailableSender: HotelAvailableSender,
    private val hotelNotAvailableSender: HotelNotAvailableSender
) {

    @Incoming("booking-created")
    suspend fun consume(bookingCreatedDtoRecord: Record<String, BookingCreatedDto>) {
        val bookingCreatedDto = bookingCreatedDtoRecord.value()

        val isAvailable = Random().nextBoolean()

        if (isAvailable) {
            val hotelAvailableDto =
                HotelAvailableDto(hotelUuid = bookingCreatedDto.hotelUuid, bookingUuid = bookingCreatedDto.uuid)
            hotelAvailableSender.send(hotelAvailableDto)
            println("sent to available topic")
            return
        }

        val hotelNotAvailableDto =
            HotelNotAvailableDto(hotelUuid = bookingCreatedDto.hotelUuid, bookingUuid = bookingCreatedDto.uuid)

        hotelNotAvailableSender.send(hotelNotAvailableDto)
        println("sent to not available topic")

    }
}