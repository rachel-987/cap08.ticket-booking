
package cap08.ticket_booking.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class ReservationJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private EventJpaEntity event;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(nullable = false)
    private int quantity;

    // Constructor mặc định cho JPA
    protected ReservationJpaEntity() {
    }

    public ReservationJpaEntity(
            EventJpaEntity event,
            String customerName,
            int quantity) {

        this.event = event;
        this.customerName = customerName;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public EventJpaEntity getEvent() {
        return event;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getQuantity() {
        return quantity;
    }
}
