package br.com.emmanuel.kafka.dto

data class HotelNotAvailableDto(
    val hotelUuid: String,
    val bookingUuid: String
)