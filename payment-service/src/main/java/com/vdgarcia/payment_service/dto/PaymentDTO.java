package com.vdgarcia.payment_service.dto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PaymentDTO {
    private Long id;
    private Long appointmentId;
    private Long patientId;
    private BigDecimal amount;
    private LocalDate paymentDate;
}
