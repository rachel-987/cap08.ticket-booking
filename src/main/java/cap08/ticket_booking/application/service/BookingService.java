
package cap08.ticket_booking.application.service;

import cap08.ticket_booking.application.port.out.EventRepositoryPort;
import cap08.ticket_booking.application.port.out.ReservationRepositoryPort;
import cap08.ticket_booking.domain.exception.EventNotFoundException;
import cap08.ticket_booking.domain.exception.InsufficientTicketsException;
import cap08.ticket_booking.domain.model.Reservation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {

    private final EventRepositoryPort eventRepository;
    private final ReservationRepositoryPort reservationRepository;

    // Constructor Injection
    public BookingService(
            EventRepositoryPort eventRepository,
            ReservationRepositoryPort reservationRepository) {

        this.eventRepository = eventRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public Reservation bookTicket(
            Long eventId,
            String customerName,
            int quantity) {

        // 1. Tạo Reservation, validate nghiệp vụ
        Reservation reservation = new Reservation(
                null,
                eventId,
                customerName,
                quantity
        );

        // 2. Atomic Conditional Update
        int updatedRows = eventRepository
                .decreaseAvailableTickets(eventId, quantity);

        // 3. Kiểm tra kết quả cập nhật
        if (updatedRows == 0) {

            if (eventRepository.findById(eventId).isEmpty()) {
                throw new EventNotFoundException(eventId);
            }

            throw new InsufficientTicketsException();
        }

        // 4. Lưu Reservation vào Database
        return reservationRepository.save(reservation);
    }
}
