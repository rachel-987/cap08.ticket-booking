
package cap08.ticket_booking.domain.exception;

/*
??? Tại sao lại kế thừa từ Runtime
        >> Vì đây là các ngoại lệ không bắt buộc phải khai báo bằng throws.
        >> Khi chúng được ném ra từ BookingService.bookTicket() thì Spring Transaction cũng mặc định rollback transaction.
 */
public class InsufficientTicketsException extends RuntimeException {

    public InsufficientTicketsException() {
        super("Not enough available tickets");
    }
}
