package br.com.emmanuel.kafka.serializer

import br.com.emmanuel.kafka.dto.BookingNotAvailableDto
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer

class BookingNotAvailableSerializer :
    ObjectMapperDeserializer<BookingNotAvailableDto>(BookingNotAvailableDto::class.java)