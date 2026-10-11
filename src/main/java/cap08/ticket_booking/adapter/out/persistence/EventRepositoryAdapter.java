
package cap08.ticket_booking.adapter.out.persistence;

import cap08.ticket_booking.application.port.out.EventRepositoryPort;
import cap08.ticket_booking.domain.model.Event;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventRepositoryAdapter implements EventRepositoryPort {

    private final SpringDataEventRepository jpaRepository;

    // Constructor Injection
    public EventRepositoryAdapter(
            SpringDataEventRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Event> findById(Long id) {
        return jpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Event> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public int decreaseAvailableTickets(
            Long eventId,
            int quantity) {

        return jpaRepository.decreaseAvailableTickets(
                eventId,
                quantity
        );
    }

    // Mapping JPA Entity -> Domain Model
    private Event toDomain(EventJpaEntity entity) {

        return new Event(
                entity.getId(),
                entity.getName(),
                entity.getTotalTickets(),
                entity.getAvailableTickets()
        );
    }
}
