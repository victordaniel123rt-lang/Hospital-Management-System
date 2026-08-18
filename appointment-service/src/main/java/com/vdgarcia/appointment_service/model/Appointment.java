package com.vdgarcia.appointment_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointment")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDate date;
    private LocalTime time;
    private String reason;
    private Status status;


}
