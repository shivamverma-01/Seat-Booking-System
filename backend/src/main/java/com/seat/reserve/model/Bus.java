package com.seat.reserve.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "buses")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busNo;
    private int totalSeats;
}
