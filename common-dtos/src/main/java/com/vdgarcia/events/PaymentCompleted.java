package com.vdgarcia.events;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentCompleted {
    private Long paymentId;
    private Long appointmentId;
    private Status status;
    private LocalDate paymentDate;
}
