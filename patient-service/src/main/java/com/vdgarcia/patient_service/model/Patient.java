package com.vdgarcia.patient_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "paciente")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate birthDate;
}
