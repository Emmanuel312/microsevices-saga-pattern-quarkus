package br.com.emmanuel.kafka.dto

import java.time.Instant

data class BookingCreatedDto(
    val uuid: String,
    val hotelUuid: String,
    val userUuid: String,
    val bookingStartDate: Instant,
    val bookingEndDate: Instant,
)