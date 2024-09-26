package com.seat.reserve.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.seat.reserve.model.BookingRequest;
import com.seat.reserve.model.Customer;
import com.seat.reserve.model.Seat;
import com.seat.reserve.repository.CustomerRepository;
import com.seat.reserve.repository.SeatRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@Service
@Validated
public class BookingService {

    private final CustomerRepository customerRepository;
    private final SeatRepository seatRepository;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(200);

    public BookingService(CustomerRepository customerRepository, SeatRepository seatRepository) {
        this.customerRepository = customerRepository;
        this.seatRepository = seatRepository;
    }

    @Transactional
    public Customer bookSeat(@Valid BookingRequest bookingRequest) {
        Seat seat = seatRepository.findByBusNoAndSeatNo(bookingRequest.getBusNo(), bookingRequest.getSeatNo());

        if (seat == null) {
            throw new IllegalStateException("Seat does not exist.");
        }

        // Check if the seat is already reserved
        if (seat.isReserved()) {
            throw new IllegalStateException("Seat is already booked. Please choose a different seat.");
        }

        // Check if the seat is locked
        if (seat.isLocked()) {
            throw new IllegalStateException("Seat is currently locked. Please try again later.");
        }

        // Lock the seat at the beginning of the booking process
        seat.setLocked(true);
        seatRepository.save(seat);

        // Schedule unlocking in case of invalid booking using an ExecutorService
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                Thread.sleep(5000);  // Simulating delay for seat unlock
                seat.setLocked(false);  // Unlock seat after 5 seconds
                seatRepository.save(seat);
                System.out.println("Seat unlocked after 5 seconds: " + seat.getSeatNo());
            } catch (Exception e) {
                System.out.println("Error while unlocking seat: " + e.getMessage());
            }
        });

        // Validate booking request
        if (isInvalidBookingRequest(bookingRequest)) {
            throw new ValidationException("Invalid booking data. Seat is locked temporarily.");
        }

        // Proceed with booking if validation is successful
        seat.setReserved(true);
        seat.setLocked(false);  // Unlock the seat once the booking is successful
        seatRepository.save(seat);

        Customer customer = new Customer();
        customer.setFirstName(bookingRequest.getFirstName());
        customer.setMiddleName(bookingRequest.getMiddleName());
        customer.setLastName(bookingRequest.getLastName());
        customer.setEmail(bookingRequest.getEmail());
        customer.setContactNumber(bookingRequest.getContactNumber());
        customer.setAddress(bookingRequest.getAddress());
        customer.setBusNo(bookingRequest.getBusNo());
        customer.setSeatNo(bookingRequest.getSeatNo());

        return customerRepository.save(customer);
    }




    private boolean isInvalidBookingRequest(BookingRequest request) {
        return request.getFirstName().isEmpty() || 
               request.getLastName().isEmpty() || 
               request.getEmail().isEmpty() || 
               !request.getEmail().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

//    private void scheduleSeatUnlock(Seat seat) {
//        scheduler.schedule(() -> {
//            try {
//                System.out.println("Attempting to unlock seat: " + seat.getSeatNo());
//                Seat seatToUnlock = seatRepository.findByBusNoAndSeatNo(seat.getBusNo(), seat.getSeatNo());
//                if (seatToUnlock != null && seatToUnlock.isLocked()) {
//                    seatToUnlock.setLocked(false);
//                    seatRepository.save(seatToUnlock);  // Save the unlocked seat to the repository
//                    System.out.println("Seat unlocked successfully after 5 seconds: " + seatToUnlock.getSeatNo());
//                } else {
//                    System.out.println("Seat was already unlocked or not found.");
//                }
//            } catch (Exception e) {
//                System.out.println("Error while unlocking seat: " + e.getMessage());
//                e.printStackTrace();
//            }
//        }, 5, TimeUnit.SECONDS);
//    }
    }


