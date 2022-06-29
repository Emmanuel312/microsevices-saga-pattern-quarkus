package br.com.emmanuel.kafka.serializer

import br.com.emmanuel.kafka.dto.BookingAvailableDto
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer

class BookingAvailableSerializer : ObjectMapperDeserializer<BookingAvailableDto>(BookingAvailableDto::class.java)