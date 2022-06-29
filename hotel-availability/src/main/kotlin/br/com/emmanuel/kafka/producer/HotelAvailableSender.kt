package br.com.emmanuel.kafka.producer

import br.com.emmanuel.kafka.dto.HotelAvailableDto
import io.smallrye.mutiny.coroutines.awaitSuspending
import io.smallrye.reactive.messaging.MutinyEmitter
import io.smallrye.reactive.messaging.kafka.Record
import org.eclipse.microprofile.reactive.messaging.Channel
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class HotelAvailableSender(
    @Channel("booking_available")
    private val emitter: MutinyEmitter<Record<String, HotelAvailableDto>>
) {

    suspend fun send(hotelAvailableDto: HotelAvailableDto) {
        emitter.send(Record.of(hotelAvailableDto.hotelUuid, hotelAvailableDto)).awaitSuspending()
    }
}