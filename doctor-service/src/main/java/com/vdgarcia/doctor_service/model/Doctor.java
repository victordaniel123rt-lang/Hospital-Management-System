package com.vdgarcia.doctor_service.model;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "doctor")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String speciality;
    private String licenseNumber;
    private Boolean available;

}
