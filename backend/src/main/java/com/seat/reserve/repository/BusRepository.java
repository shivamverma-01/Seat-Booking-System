package com.seat.reserve.repository;

import com.seat.reserve.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Bus findByBusNo(String busNo);
}
