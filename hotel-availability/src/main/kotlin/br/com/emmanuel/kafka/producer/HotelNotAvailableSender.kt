package br.com.emmanuel.kafka.producer

import br.com.emmanuel.kafka.dto.HotelNotAvailableDto
import io.smallrye.mutiny.coroutines.awaitSuspending
import io.smallrye.reactive.messaging.MutinyEmitter
import io.smallrye.reactive.messaging.kafka.Record
import org.eclipse.microprofile.reactive.messaging.Channel
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class HotelNotAvailableSender(
    @Channel("booking_not_available")
    private val emitter: MutinyEmitter<Record<String, HotelNotAvailableDto>>
) {

    suspend fun send(hotelAvailableDto: HotelNotAvailableDto) {
        emitter.send(Record.of(hotelAvailableDto.hotelUuid, hotelAvailableDto)).awaitSuspending()
    }
}