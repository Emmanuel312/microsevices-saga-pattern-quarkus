package br.com.emmanuel.kafka.dto

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer


class BookingCreatedDtoSerializer : ObjectMapperDeserializer<BookingCreatedDto>(BookingCreatedDto::class.java)