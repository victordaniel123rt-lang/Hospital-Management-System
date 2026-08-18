package com.vdgarcia.appointment_service.controller;

import com.vdgarcia.appointment_service.dto.AppointmentDTO;
import com.vdgarcia.appointment_service.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @PostMapping
    public ResponseEntity<AppointmentDTO> crear (@RequestBody AppointmentDTO dto){
        return ResponseEntity.ok(service.crear(dto));
    }

}
