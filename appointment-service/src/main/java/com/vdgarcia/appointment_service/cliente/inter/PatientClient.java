package com.vdgarcia.appointment_service.cliente.inter;

import com.vdgarcia.appointment_service.cliente.dtos.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "patient-service", url = "${application.config.patient-url}")
public interface PatientClient {

    @GetMapping("/{id}")
    PatientDTO obtenerPaciente(@PathVariable Long id);
}
