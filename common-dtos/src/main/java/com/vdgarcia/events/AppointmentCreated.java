package com.vdgarcia.events;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class AppointmentCreated {
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private BigDecimal amount;
    private LocalDate date;


}
