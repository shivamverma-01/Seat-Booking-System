package com.seat.reserve.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busNo;
    private int seatNo;
    private boolean isReserved;
    private boolean isLocked;
    
}
