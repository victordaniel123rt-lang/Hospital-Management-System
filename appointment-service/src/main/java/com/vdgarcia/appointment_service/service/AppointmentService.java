package com.vdgarcia.appointment_service.service;

import com.vdgarcia.appointment_service.dto.AppointmentDTO;

public interface AppointmentService {

    AppointmentDTO crear(AppointmentDTO dto);
    void cambiarEstado(Long id);

}
