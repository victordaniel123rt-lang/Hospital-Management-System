package com.vdgarcia.payment_service.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payment")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long appointmentId;
    private Long patientId;
    private BigDecimal amount;
    private LocalDate paymentDate;
}
