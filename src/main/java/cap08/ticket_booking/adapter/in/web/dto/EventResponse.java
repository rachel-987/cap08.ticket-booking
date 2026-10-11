
package cap08.ticket_booking.adapter.in.web.dto;

public class EventResponse {

    private Long id;
    private String name;
    private int totalTickets;
    private int availableTickets;

    // Constructor
    public EventResponse(
            Long id,
            String name,
            int totalTickets,
            int availableTickets) {

        this.id = id;
        this.name = name;
        this.totalTickets = totalTickets;
        this.availableTickets = availableTickets;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }
}
