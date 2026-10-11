
package cap08.ticket_booking.adapter.in.web;

import cap08.ticket_booking.adapter.in.web.dto.BookingRequest;
import cap08.ticket_booking.adapter.in.web.dto.BookingResponse;
import cap08.ticket_booking.application.service.BookingService;
import cap08.ticket_booking.domain.model.Reservation;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    //Spring tự động tìm Bean BookingService và truyền vào Controller.
    private final BookingService bookingService;

    /* ??? Tại sao Controller gọi Service mà không gọi Repository
            - Bởi vì Controller chỉ nên phụ trách giao tiếp HTTP.
            - Nghiệp vụ kiểm tra vé, trừ vé, lưu Reservation và quản lý transaction thuộc trách nhiệm của BookingService.
     */
    // Constructor Injection
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> bookTicket(
            @Valid @RequestBody BookingRequest request) {

        // 1. Gọi Service xử lý nghiệp vụ
        Reservation reservation = bookingService.bookTicket(
                request.getEventId(),
                request.getCustomerName(),
                request.getQuantity()
        );

        // 2. Chuyển Domain Model sang Response DTO
        BookingResponse response = new BookingResponse(
                reservation.getId(),
                reservation.getEventId(),
                reservation.getCustomerName(),
                reservation.getQuantity(),
                "Booking successful"
        );

        // 3. Trả HTTP Response về Client
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
