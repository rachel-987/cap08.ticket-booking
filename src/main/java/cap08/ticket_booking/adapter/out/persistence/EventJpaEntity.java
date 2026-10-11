
package cap08.ticket_booking.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class EventJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "total_tickets", nullable = false)
    private int totalTickets;

    @Column(name = "available_tickets", nullable = false)
    private int availableTickets;

    // Constructor mặc định cho JPA
    public EventJpaEntity() {
    }

    // Constructor để tạo Event
    public EventJpaEntity(
            String name,
            int totalTickets,
            int availableTickets) {
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

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }
}
