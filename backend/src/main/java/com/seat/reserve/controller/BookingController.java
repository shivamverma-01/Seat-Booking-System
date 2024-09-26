package com.seat.reserve.controller;

import com.seat.reserve.model.BookingRequest;
import com.seat.reserve.model.Customer;
import com.seat.reserve.service.BookingService;
import com.seat.reserve.service.SeatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final SeatService seatService;

    @Autowired
    public BookingController(BookingService bookingService, SeatService seatService) {
        this.bookingService = bookingService;
        this.seatService = seatService;
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> bookSeats(@Valid @RequestBody List<BookingRequest> bookingRequests, BindingResult result) {
        if (bookingRequests.size() > 5) {
            return ResponseEntity.badRequest().body("Maximum seats that can be booked is 5.");
        }

        StringBuilder confirmationMessage = new StringBuilder();
        
        for (BookingRequest bookingRequest : bookingRequests) {
            if (result.hasErrors()) {
                seatService.lockSeat(bookingRequest.getBusNo(), bookingRequest.getSeatNo());
                confirmationMessage.append("Booking failed for ").append(bookingRequest.getFirstName()).append(" ").append(bookingRequest.getLastName())
                                   .append(": Invalid details. The seat is locked for 1 minute.\n");
                continue;
            }

            try {
                Customer savedCustomer = bookingService.bookSeat(bookingRequest);
                confirmationMessage.append("Booking confirmed for ").append(savedCustomer.getFirstName()).append(" ").append(savedCustomer.getLastName()).append("\n");
            } catch (ValidationException e) {
                seatService.lockSeat(bookingRequest.getBusNo(), bookingRequest.getSeatNo());
                confirmationMessage.append("Booking failed for ").append(bookingRequest.getFirstName()).append(" ").append(bookingRequest.getLastName())
                                   .append(": Invalid details. The seat is locked for 1 minute.\n");
            } catch (IllegalStateException e) {
                confirmationMessage.append("Booking failed for ").append(bookingRequest.getFirstName()).append(" ").append(bookingRequest.getLastName())
                                   .append(": ").append(e.getMessage()).append("\n");
            }
        }

        return ResponseEntity.ok(confirmationMessage.toString());
    }
}
