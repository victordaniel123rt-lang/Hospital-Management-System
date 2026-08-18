package com.vdgarcia.patient_service.controller;

import com.vdgarcia.patient_service.dto.PatientDTO;
import com.vdgarcia.patient_service.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService service;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> obtenerTodos(){
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<PatientDTO> crear(@Valid @RequestBody PatientDTO dto){
        return ResponseEntity.ok(service.crear(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> actualizar(@PathVariable Long id,@Valid @RequestBody PatientDTO dto){
        return ResponseEntity.ok(service.actualizar(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientDTO> eliminar(@PathVariable Long id){
        return ResponseEntity.ok(service.eliminar(id));
    }

}
