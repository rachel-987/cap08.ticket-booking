
package cap08.ticket_booking.domain.exception;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(Long eventId) {
        super("Event not found: " + eventId);
    }
}
