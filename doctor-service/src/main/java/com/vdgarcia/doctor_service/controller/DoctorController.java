package com.vdgarcia.doctor_service.controller;

import com.vdgarcia.doctor_service.dto.DoctorDTO;
import com.vdgarcia.doctor_service.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService service;

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> obtenerTodos(){
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> crear(@Valid @RequestBody DoctorDTO doctorDTO){
        return ResponseEntity.ok(service.crear(doctorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> actualizar(@PathVariable Long id, @Valid @RequestBody DoctorDTO doctorDTO){
        return ResponseEntity.ok(service.actualizar(id,doctorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorDTO> eliminar(@PathVariable Long id){
        return ResponseEntity.ok(service.eliminar(id));
    }


}
