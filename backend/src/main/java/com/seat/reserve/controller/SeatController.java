package com.seat.reserve.controller;

import com.seat.reserve.model.Seat;
import com.seat.reserve.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/available/{busNo}")
    public ResponseEntity<List<Seat>> getAvailableSeats(@PathVariable String busNo) {
        List<Seat> availableSeats = seatService.getAvailableSeats(busNo);
        return ResponseEntity.ok(availableSeats);
    }

    // New endpoint to get all seats of the bus with reservation and lock status
    @GetMapping("/status/{busNo}")
    public ResponseEntity<List<Seat>> getAllSeatsStatus(@PathVariable String busNo) {
        List<Seat> allSeats = seatService.getAllSeatsStatus(busNo);
        return ResponseEntity.ok(allSeats);
    }

    @PostMapping("/lock")
    public ResponseEntity<Seat> lockSeat(@RequestParam String busNo, @RequestParam int seatNo) {
        Seat lockedSeat = seatService.lockSeat(busNo, seatNo);
        return ResponseEntity.ok(lockedSeat);
    }
}
