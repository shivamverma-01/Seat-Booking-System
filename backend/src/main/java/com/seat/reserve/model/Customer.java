package com.seat.reserve.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String address;

    private String busNo;
    private int seatNo;
}
