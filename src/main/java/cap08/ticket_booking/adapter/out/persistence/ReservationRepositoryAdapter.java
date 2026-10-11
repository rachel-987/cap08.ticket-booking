
package cap08.ticket_booking.adapter.out.persistence;

import cap08.ticket_booking.application.port.out.ReservationRepositoryPort;
import cap08.ticket_booking.domain.model.Reservation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepositoryAdapter implements ReservationRepositoryPort {
    /* Tại sao phải inject 2 repo khác nhau???
         Vì mỗi Repository có nhiệm vụ khác nhau:
            - SpringDataEventRepository: Tìm Event mà khách hàng muốn đặt vé.
            - SpringDataReservationRepository: Lưu thông tin Reservation.
    */
    private final SpringDataReservationRepository reservationJpaRepository;
    private final SpringDataEventRepository eventJpaRepository;

    public ReservationRepositoryAdapter(
            SpringDataReservationRepository reservationJpaRepository,
            SpringDataEventRepository eventJpaRepository) {

        this.reservationJpaRepository = reservationJpaRepository;
        this.eventJpaRepository = eventJpaRepository;
    }

    @Override
    @Transactional
    public Reservation save(Reservation reservation) {

        if (reservation.getId() != null) {
            throw new IllegalArgumentException(
                    "Only new reservations can be created");
        }

        // 1. Tìm Event trong Database
        EventJpaEntity eventEntity = eventJpaRepository
                .findById(reservation.getEventId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Event not found: "
                                        + reservation.getEventId()));

        /*
        Tại sao phải mapping từ Domain sang Entity?
            Sau khi lưu thành công, chúng ta chuyển ReservationJpaEntity trở lại Reservation
            để tầng Application không phải phụ thuộc vào JPA.
         */
        // 2. Domain Model -> JPA Entity
        ReservationJpaEntity entity =
                new ReservationJpaEntity(
                        eventEntity,
                        reservation.getCustomerName(),
                        reservation.getQuantity()
                );

        // 3. Lưu xuống Database
        ReservationJpaEntity savedEntity =
                reservationJpaRepository.save(entity);

        // 4. JPA Entity -> Domain Model
        return toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reservation> findById(Long id) {
        return reservationJpaRepository
                .findById(id)
                .map(this::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> findAll() {
        return reservationJpaRepository
                .findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private Reservation toDomain(
            ReservationJpaEntity entity) {

        return new Reservation(
                entity.getId(),
                entity.getEvent().getId(),
                entity.getCustomerName(),
                entity.getQuantity()
        );
    }
}
