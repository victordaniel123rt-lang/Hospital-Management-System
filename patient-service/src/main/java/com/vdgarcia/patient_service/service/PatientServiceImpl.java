package com.vdgarcia.patient_service.service;

import com.vdgarcia.patient_service.dto.PatientDTO;
import com.vdgarcia.patient_service.mapper.Mapper;
import com.vdgarcia.patient_service.model.Patient;
import com.vdgarcia.patient_service.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{

    private final PatientRepository repository;

    @Override
    public List<PatientDTO> obtenerTodos() {
        return repository.findAll().stream().map(Mapper::toPatientDTO).toList();
    }

    @Override
    public PatientDTO obtenerPorId(Long id) {
        Patient paciente = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Patient Not Founded")
        );
        return Mapper.toPatientDTO(paciente);
    }

    @Override
    public PatientDTO crear(PatientDTO dto) {
        Patient patient = Mapper.toPatient(dto);
        Patient creado = repository.save(patient);
        return Mapper.toPatientDTO(creado);
    }

    @Override
    public PatientDTO actualizar(Long id, PatientDTO dto) {
        Patient paciente = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Patient Not Founded")
        );
        Mapper.updatePatiente(dto,paciente);
        Patient actualizado = repository.save(paciente);
        return Mapper.toPatientDTO(actualizado);
    }

    @Override
    public PatientDTO eliminar(Long id) {
        Patient paciente = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Patient Not Founded")
        );
        repository.delete(paciente);
        return Mapper.toPatientDTO(paciente);
    }
}
