package br.com.emmanuel.kafka.publisher

import br.com.emmanuel.entity.BookingCreatedDto
import io.smallrye.mutiny.coroutines.awaitSuspending
import io.smallrye.reactive.messaging.MutinyEmitter
import io.smallrye.reactive.messaging.kafka.Record
import org.eclipse.microprofile.reactive.messaging.Channel
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class BookingCreatedKafkaPublisher(
    @Channel("booking-created")
    private val emitter: MutinyEmitter<Record<String, BookingCreatedDto>>
) {

    suspend fun send(bookingCreatedDto: BookingCreatedDto) {
        emitter.send(Record.of(bookingCreatedDto.hotelUuid, bookingCreatedDto)).awaitSuspending()
    }
}