package cap08.ticket_booking.domain.model;

public record Event(
        Long id,
        String name,
        int totalTickets,
        int availableTickets
) {
}