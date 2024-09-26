package com.seat.reserve.repository;

import com.seat.reserve.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByBusNoAndIsReservedFalse(String busNo);
    Seat findByBusNoAndSeatNo(String busNo, int seatNo);
    
    // New method to fetch all seats of a bus
    List<Seat> findByBusNo(String busNo);
}
