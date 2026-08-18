package com.vdgarcia.appointment_service.cliente.inter;

import com.vdgarcia.appointment_service.cliente.dtos.DoctorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "doctor-service", url = "${application.config.doctor-url}")
public interface DoctorClient {

    @GetMapping("/{id}")
    DoctorDTO obtenerDoctor(@PathVariable Long id);

}
