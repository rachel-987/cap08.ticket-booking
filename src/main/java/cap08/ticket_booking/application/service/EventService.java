
package cap08.ticket_booking.application.service;

import cap08.ticket_booking.application.port.out.EventRepositoryPort;
import cap08.ticket_booking.domain.model.Event;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    private final EventRepositoryPort eventRepository;

    // Constructor Injection
    public EventService(EventRepositoryPort eventRepository) {
        this.eventRepository = eventRepository;
    }

    // Tạo Event mới
    @Transactional
    public Event createEvent(String name, int totalTickets) {

        // 1. Validate dữ liệu nghiệp vụ
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Event name must not be blank"
            );
        }

        if (totalTickets <= 0) {
            throw new IllegalArgumentException(
                    "Total tickets must be greater than 0"
            );
        }

        // 2. Khởi tạo Domain Model
        Event event = new Event(
                null,
                name,
                totalTickets,
                totalTickets
        );

        // 3. Lưu Event vào Database thông qua Port
        return eventRepository.save(event);
    }
}
