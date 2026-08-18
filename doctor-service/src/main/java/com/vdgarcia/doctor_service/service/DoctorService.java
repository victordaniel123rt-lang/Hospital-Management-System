package com.vdgarcia.doctor_service.service;

import com.vdgarcia.doctor_service.dto.DoctorDTO;

import java.util.List;

public interface DoctorService {
    List<DoctorDTO> obtenerTodos();
    DoctorDTO obtenerPorId(Long id);
    DoctorDTO crear(DoctorDTO doctorDTO);
    DoctorDTO actualizar(Long id, DoctorDTO doctorDTO);
    DoctorDTO eliminar(Long id);

}
