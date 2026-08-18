package com.vdgarcia.appointment_service.dto;
import com.vdgarcia.appointment_service.model.Status;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class AppointmentDTO {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDate date;
    private LocalTime time;
    private String reason;
    private Status status;
}
