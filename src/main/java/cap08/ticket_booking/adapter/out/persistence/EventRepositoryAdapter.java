
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

    // 1. Lưu Event mới vào Database
    @Override
    public Event save(Event event) {

        // Domain Model -> JPA Entity
        EventJpaEntity entity = new EventJpaEntity(
                event.name(),
                event.totalTickets(),
                event.availableTickets()
        );

        // Lưu xuống Database
        EventJpaEntity savedEntity = jpaRepository.save(entity);

        // JPA Entity -> Domain Model
        return toDomain(savedEntity);
    }

    // 2. Tìm Event theo ID
    @Override
    public Optional<Event> findById(Long id) {
        return jpaRepository.findById(id)
                .map(this::toDomain);
    }

    // 3. Lấy danh sách Event
    @Override
    public List<Event> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    // 4. Trừ số lượng vé
    @Override
    public int decreaseAvailableTickets(
            Long eventId,
            int quantity) {

        return jpaRepository.decreaseAvailableTickets(
                eventId,
                quantity
        );
    }

    // 5. Mapping JPA Entity -> Domain Model
    private Event toDomain(EventJpaEntity entity) {

        return new Event(
                entity.getId(),
                entity.getName(),
                entity.getTotalTickets(),
                entity.getAvailableTickets()
        );
    }
}
