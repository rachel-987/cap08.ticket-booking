
package cap08.ticket_booking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataEventRepository extends JpaRepository<EventJpaEntity, Long> {

    @Modifying
    @Query("""
        UPDATE EventJpaEntity e
        SET e.availableTickets = e.availableTickets - :quantity
        WHERE e.id = :eventId
          AND e.availableTickets >= :quantity
        """)
    int decreaseAvailableTickets(
            @Param("eventId") Long eventId,
            @Param("quantity") int quantity
    );
}
