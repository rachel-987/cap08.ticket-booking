
package cap08.ticket_booking.domain.model;

public class Reservation {

    //final đảm bảo thuộc tính chỉ được gán khi khởi tạo đối tượng và không thể gán lại sau đó.
    private final Long id;
    private final Long eventId;
    private final String customerName;
    private final int quantity;

    //Validate ngay trong ctor => đảm bảo data lúc lưu luôn đúng
    public Reservation(
            Long id,
            Long eventId,
            String customerName,
            int quantity) {

        if (eventId == null || eventId <= 0) {
            throw new IllegalArgumentException(
                    "Event ID must be positive");
        }

        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException(
                    "Customer name cannot be empty");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than 0");
        }

        this.id = id;
        this.eventId = eventId;
        this.customerName = customerName;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
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

    //vì chúng ta không cung cấp setter => Domain Model bất biến (Immutable Object)
}
