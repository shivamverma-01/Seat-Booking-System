package com.seat.reserve.service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.seat.reserve.model.Seat;
import com.seat.reserve.repository.SeatRepository;

import jakarta.transaction.Transactional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }
    public List<Seat> getAllSeatsStatus(String busNo) {
        return seatRepository.findByBusNo(busNo); // Assuming it fetches all seats regardless of status
    }

    // Lock seat for reservation
    public Seat lockSeat(String busNo, int seatNo) {
        Seat seat = seatRepository.findByBusNoAndSeatNo(busNo, seatNo);
        if (seat == null) {
            throw new IllegalArgumentException("Seat not found");
        }
        if (seat.isLocked()) {
            throw new IllegalStateException("Seat is already locked");
        }
        
        seat.setLocked(true);
        seatRepository.save(seat);

        // Schedule unlock after 5 seconds
        scheduleSeatUnlock(seat);
        
        return seat;
    }

    // Schedule the seat unlock task
    private void scheduleSeatUnlock(Seat seat) {
        scheduler.schedule(() -> {
            try {
                Seat seatToUnlock = seatRepository.findByBusNoAndSeatNo(seat.getBusNo(), seat.getSeatNo());
                if (seatToUnlock != null && seatToUnlock.isLocked()) {
                    seatToUnlock.setLocked(false);
                    seatRepository.save(seatToUnlock);
                    System.out.println("Seat unlocked successfully after 30 seconds: " + seatToUnlock.getSeatNo());
                } else {
                    System.out.println("Seat was already unlocked or not found.");
                }
            } catch (Exception e) {
                System.out.println("Error while unlocking seat: " + e.getMessage());
            }
        }, 30, TimeUnit.SECONDS);  // Unlock seat after 30 seconds
    }

    // Reserve the seat (ensure the seat is reserved after locking)
    @Transactional
    public Seat reserveSeat(String busNo, int seatNo) {
        Seat seat = seatRepository.findByBusNoAndSeatNo(busNo, seatNo);
        if (seat == null) {
            throw new IllegalArgumentException("Seat not found");
        }
        if (!seat.isLocked()) {
            throw new IllegalStateException("Seat must be locked before reserving");
        }
        seat.setReserved(true);
        seat.setLocked(false); // Unlock after reservation
        return seatRepository.save(seat);
    }
    public List<Seat> getAvailableSeats(String busNo) {
        return seatRepository.findByBusNoAndIsReservedFalse(busNo);
    }
}
