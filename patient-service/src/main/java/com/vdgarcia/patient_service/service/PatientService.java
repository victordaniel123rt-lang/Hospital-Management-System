package com.vdgarcia.patient_service.service;

import com.vdgarcia.patient_service.dto.PatientDTO;

import java.util.List;

public interface PatientService {

    List<PatientDTO> obtenerTodos();
    PatientDTO obtenerPorId(Long id);
    PatientDTO crear(PatientDTO dto);
    PatientDTO actualizar(Long id, PatientDTO dto);
    PatientDTO eliminar(Long id);

}
