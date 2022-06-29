package br.com.emmanuel.kafka.dto

data class BookingNotAvailableDto(
    val hotelUuid: String,
    val bookingUuid: String
)