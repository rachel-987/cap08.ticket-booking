
package cap08.ticket_booking.adapter.in.web;

import cap08.ticket_booking.adapter.in.web.dto.CreateEventRequest;
import cap08.ticket_booking.adapter.in.web.dto.EventResponse;
import cap08.ticket_booking.application.service.EventService;
import cap08.ticket_booking.domain.model.Event;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    // Constructor Injection
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // API tạo Event mới
    @PostMapping
    public ResponseEntity<EventResponse> createEvent(
            @Valid @RequestBody CreateEventRequest request) {

        // 1. Gọi Service xử lý nghiệp vụ
        Event event = eventService.createEvent(
                request.getName(),
                request.getTotalTickets()
        );

        // 2. Chuyển Domain Model sang Response DTO
        EventResponse response = new EventResponse(
                event.id(),
                event.name(),
                event.totalTickets(),
                event.availableTickets()
        );

        // 3. Trả HTTP 201 Created
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
