package br.com.emmanuel.database.booking

import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.smallrye.mutiny.coroutines.awaitSuspending
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class BookingRepository : PanacheRepository<BookingEntity> {

    suspend fun save(bookingEntity: BookingEntity) {
        persistAndFlush(bookingEntity).awaitSuspending()
    }

    suspend fun changeStatus(bookingUuid: String, bookingStatus: BookingStatus) {
        update("status = ?1 where bookinguuid = ?2", bookingStatus, bookingUuid).awaitSuspending()
    }
}