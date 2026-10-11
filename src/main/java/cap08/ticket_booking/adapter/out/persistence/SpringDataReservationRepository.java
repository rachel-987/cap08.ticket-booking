
package cap08.ticket_booking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataReservationRepository extends JpaRepository<ReservationJpaEntity, Long> {
}
