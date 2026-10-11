
package cap08.ticket_booking.application.port.out;

import cap08.ticket_booking.domain.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepositoryPort {

    // Lưu thông tin đặt vé
    Reservation save(Reservation reservation);

    // Tìm lượt đặt vé theo ID ~ Sử dụng Optional vì không phải lúc nào ID được yêu cầu cũng tồn tại trong database.
    Optional<Reservation> findById(Long id);

    // Lấy danh sách đặt vé
    List<Reservation> findAll();
}
