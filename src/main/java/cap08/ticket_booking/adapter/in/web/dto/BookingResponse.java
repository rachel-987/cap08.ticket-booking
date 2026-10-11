
package cap08.ticket_booking.adapter.in.web.dto;

public class BookingResponse {

    private Long reservationId;
    private Long eventId;
    private String customerName;
    private int quantity;
    private String message;

    public BookingResponse(
            Long reservationId,
            Long eventId,
            String customerName,
            int quantity,
            String message) {

        this.reservationId = reservationId;
        this.eventId = eventId;
        this.customerName = customerName;
        this.quantity = quantity;
        this.message = message;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public Long getEventId() {
        return eventId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMessage() {
        return message;
    }
}
