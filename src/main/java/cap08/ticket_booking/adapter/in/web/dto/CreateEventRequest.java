
package cap08.ticket_booking.adapter.in.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateEventRequest {

    @NotBlank(message = "Event name must not be blank")
    private String name;

    @Min(value = 1, message = "Total tickets must be at least 1")
    private int totalTickets;

    public CreateEventRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }
}
