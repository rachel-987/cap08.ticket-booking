package cap08.ticket_booking.adapter.in.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BookingRequest {

    @NotNull(message = "Event ID is required")
    @Positive(message = "Event ID must be positive")
    private Long eventId;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}