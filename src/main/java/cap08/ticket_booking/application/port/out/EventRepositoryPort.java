
package cap08.ticket_booking.application.port.out;

import cap08.ticket_booking.domain.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepositoryPort {
    // Lưu Event mới vào hệ thống
    Event save(Event event);

    // Tìm sự kiện theo ID
    Optional<Event> findById(Long id);

    // Lấy danh sách tất cả sự kiện
    List<Event> findAll();

    // Trừ vé nếu số lượng còn lại đủ
    int decreaseAvailableTickets(Long eventId, int quantity);
}
